package logic;

import java.awt.geom.Rectangle2D;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import entities.Consumable;
import entities.Gun;
import entities.MeleeWeapon;
import entities.Monster;
import entities.Pickupable;
import entities.Player;
import entities.Projectile;
import interfaces.Character;
import interfaces.Entity;
import interfaces.MoveableEntity;
import resources.ImgResources;
import view.Renderer;

/**
 * The Room class will be the basis for every room in the game
 * showing what objects are stored in a room and what doors
 * the room has.
 * @author LewisMcEwan
 * @author laudernich1
 *
 */
public class Room implements Serializable{

	private static final long serialVersionUID = -5452360281885911070L;
	private int roomNum;
	private List<Entity> roomEntities;
	private Level level;

	/**
	 * Constructs a new room
	 * @param num	room number
	 */
	public Room(int num, Level level){
		this.roomNum = num;
		this.roomEntities = new ArrayList<>();
		generateWalls();
		this.level = level;
	}

	/**
	 * Handles a tick in the game
	 * @param x 		mouse x pos
	 * @param y 		mouse y pos
	 * @param tickNo	number of ticks in game
	 * 
	 */
	public void tick(float x, float y, int tickNo) {
		playerTick(x, y);
		
		ArrayList<Entity> toRemove = new ArrayList<>();
		ArrayList<Entity> toAdd = new ArrayList<>();
		for(Entity e : roomEntities){
			if (e instanceof Projectile) {
				toRemove.addAll(projectileTick((Projectile)e));
			} else if(e instanceof MoveableEntity) {
				((MoveableEntity) e).tick();
			} if (e == level.getBoss() && tickNo % 120 == 0) {
				toAdd.addAll(bossAttack((Monster)e));
			} else if(e instanceof Monster && e.getBoundingBox().intersects(getPlayer().getBoundingBox())){
				((Monster)e).attack(getPlayer(), tickNo);
			} 
			if (e instanceof Monster && ((Monster)e).getLives() <= ((Monster)e).getFullLives()/2) {
				if (e == level.getBoss()) {
					e.setImage(ImgResources.MAOINJURED);
				} else {
					e.setImage(ImgResources.MONSTERINJURED);
				}
			}
		}
		roomEntities.removeAll(toRemove);
		roomEntities.addAll(toAdd);
	}
	
	private List<Entity> bossAttack(Monster m) {
		ArrayList<Entity> toAdd = new ArrayList<>();
		if (m.getHand() instanceof Gun) {
			toAdd.add(((Gun) m.getHand()).createProjectile(ImgResources.VLASER, m.getX()+m.getWidth()/2d, m.getY()+m.getHeight()/2d+10d));
			toAdd.add(((Gun) m.getHand()).createProjectile(ImgResources.VLASER, m.getX()+m.getWidth()/2d, m.getY()+m.getHeight()/2d-10d));
			toAdd.add(((Gun) m.getHand()).createProjectile(ImgResources.HLASER, m.getX()+m.getWidth()/2d+10d, m.getY()+m.getHeight()/2d));
			toAdd.add(((Gun) m.getHand()).createProjectile(ImgResources.HLASER, m.getX()+m.getWidth()/2d-10d, m.getY()+m.getHeight()/2d));
		}
		return toAdd;
	}
	
	/**
	 * Decides which direction to move the player
	 * @param x
	 * @param y
	 */
	private void playerTick(float x, float y) {
		getPlayer().tick();
		if (x != 0 || y != 0) {
			int answer = movePlayer(x, y);
			if (answer % 3 == 0 && answer % 2 == 0) {
				return;
			} else if (answer % 3 == 0) {
				getPlayer().moveBy(0, y);
			} else if (answer % 2 == 0) {
				getPlayer().moveBy(x, 0);
			} else {
				getPlayer().moveBy(x, y);
			}
		}
		if (getPlayer().getLives() <= 1) {
			getPlayer().setImage(ImgResources.PLAYERDOWNINJURED);
		} else {
			getPlayer().setImage(ImgResources.PLAYERDOWN);
		}
	}
	
	/**
	 * Calls actions on what should happen when a projectile hits 
	 * different objects (i.e. bullet disappears on wall, but hurts monster)
	 * @param e		the projectile
	 * @return
	 */
	private ArrayList<Entity> projectileTick(Projectile e) {
		ArrayList<Entity> toRemove = new ArrayList<>();
		e.tick();
		for (Entity b : roomEntities) {
			if (e.getBoundingBox().intersects(b.getBoundingBox())) {
				if (e.getOwner() == b || b instanceof Projectile) {
					continue;
				}
				toRemove.add(e);
				if (b instanceof Character) {
					e.attack(b);
					if (!((Character) b).isAlive()) {
						toRemove.add(b);
					}
				}
			}
		}
		return toRemove;
	}


	/**
	 * creates wall entities and adds them to the list of entities
	 */
	private void generateWalls(){
		for(int i = 0; i < Renderer.FLOOR_WIDTH; i++){
			for(int j = 0; j < Renderer.FLOOR_HEIGHT; j++){
				if(i == 0 || i == Renderer.FLOOR_WIDTH-1 || j == 0 || j == Renderer.FLOOR_HEIGHT-1){
					roomEntities.add(new Wall(i*Renderer.TILE_SIZE, j*Renderer.TILE_SIZE, 32, 32));
				}
			}
		}
	}

	/**
	 * When player wants to move room through a door
	 * @param d
	 */
	public void goThroughDoor(Door d){
		if (d.isLocked() && (d.getUnlockItem() != getPlayer().getHand() || getPlayer().getHand() == null)) {
			return;
		}
		
		if (d.getUnlockItem() == getPlayer().getHand()) {
			d.unlockDoor(getPlayer().getHand());
			getPlayer().drop();
		}
		
		ArrayList<Projectile> toRemove = new ArrayList<>();
		for (Entity e : roomEntities) {
			if (e instanceof Projectile) {
				toRemove.add((Projectile)e);
			}
		}
		roomEntities.remove(getPlayer());
		roomEntities.removeAll(toRemove);
		Room gotoRoom = (this == d.getRoom1()) ? d.getRoom2() : d.getRoom1();
		for (Entity e : level.getRoom(5).roomEntities) {
			if (e instanceof Door) {
				((Door) e).flipDoor();
			}
		}
		level.gotoRoom(gotoRoom);
	}


	/**
	 * Checks if the player has collided with anything in the room
	 * Different outcomes depending on what the player has
	 * collided with
	 */
	private int movePlayer(float x, float y){
		Rectangle2D.Double boxX = new Rectangle2D.Double(getPlayer().getX()+x*2, getPlayer().getY(), 
														getPlayer().getWidth(), getPlayer().getHeight());
		Rectangle2D.Double boxY = new Rectangle2D.Double(getPlayer().getX(), getPlayer().getY()+y*2, 
														getPlayer().getWidth(), getPlayer().getHeight());

		int collision = 1;

		for(Entity e : roomEntities){
			if((e.getBoundingBox().intersects(boxX) || e.getBoundingBox().intersects(boxY)) && e instanceof Door){
				goThroughDoor((Door)e);
				break;
			}
					
			if (e instanceof Wall) {
				if(e.getBoundingBox().intersects(boxX)){
					collision *= 3;
				} if (e.getBoundingBox().intersects(boxY)){
					collision *= 2;
				}
			}
		}
		
		return collision;
		
		
	}

	/**
	 * This searches the room for a pickupable item
	 * which the player is colliding with
	 */
	public void pickupItem(){
		Pickupable toRemove = null;
		Pickupable toAdd = null;
		for(Entity e : this.roomEntities){
			if(e.getBoundingBox().intersects(getPlayer().getBoundingBox()) 
					&& e instanceof Pickupable){
				toRemove = (Pickupable)e;
				toAdd = (Pickupable)e;
			}
		}
		if (toRemove != null) {
			roomEntities.remove(toRemove);
		} if (toAdd != null) {
			toAdd = getPlayer().pickup(toAdd);
			if (toAdd != null) {
				roomEntities.add(toAdd);
			}
		}
	}
	
	/**
	 * 
	 */
	public void dropItem() {
		Pickupable toAdd = getPlayer().drop();
		if (toAdd != null) {
			roomEntities.add(toAdd);
		}
	}


	/**
	 * Add an item to this room
	 * @param item		item to add
	 */
	public void addEntity(Entity item){
		this.roomEntities.add(item);
	}


	/**
	 * Get a door from this room
	 * @param i		door number
	 * @return
	 */
	public Door getDoor(int i){
		for(Entity e : this.roomEntities){
			if(e instanceof Door && ((Door)e).getDoorNum() == i){
				return (Door)e;
			}
		}
		return null;
	}


	/**
	 * check if a door is locked
	 * @return
	 */
	public boolean doorLocked(Door d){
		return d.isLocked();
	}


	/**
	 * @return the list of doors for this room
	 */
	public List<Door> getDoors(){
		List<Door> doors = new ArrayList<>();
		for(Entity e : this.roomEntities){
			if(e instanceof Door){
				doors.add( (Door)e );
			}
		}
		return doors;
	}

	/**
	 * @return room num
	 */
	public int getRoomNum(){
		return this.roomNum;
	}

	/**
	 * Get player in room
	 */
	public Player getPlayer(){
		return level.getPlayer();
	}

	/**
	 * @return list of all room entities
	 */
	public List<Entity> getEntities(){
		return this.roomEntities;
	}
	
	private boolean attackMonster(Monster m, float x, float y) {
		//TODO: implement directional hitting
		((MeleeWeapon) getPlayer().getHand()).attack(m);
		return ((m).isAlive()) ? false : true;
	}

	/**
	 * Scans the extended surrounding player box for a monster and attacks it
	 * @return
	 * 		true if an attack was successfully carried out
	 */
	public void use(float x, float y) {
		Pickupable hand = getPlayer().getHand();
		if (hand instanceof MeleeWeapon) {
			ArrayList<Entity> toRemove = new ArrayList<>();
			for(Entity e : this.roomEntities){
				if(e.getBoundingBox().intersects(this.getPlayer().getExtendedBoundingBox()) 
						&& e instanceof Monster 
						&& attackMonster((Monster)e, x, y)){
					toRemove.add(e);
				}
			} //End of entities iteration
			roomEntities.removeAll(toRemove);
		} else if (hand instanceof Gun) {
			Projectile bullet = ((Gun) hand).createProjectile(x, y-Renderer.HUD_HEIGHT);
			if (bullet != null) {
				roomEntities.add(bullet); 
			}
		} else if (hand instanceof Consumable) {
			level.getPlayer().use();
		}
	}

}
