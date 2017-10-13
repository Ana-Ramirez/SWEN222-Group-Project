package logic;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import entities.Consumable;
import entities.Gun;
import entities.MeleeWeapon;
import entities.Monster;
import entities.MovableEntity;
import entities.Pickupable;
import entities.Player;
import entities.Projectile;
import interfaces.Entity;
import javafx.geometry.BoundingBox;
import view.Renderer;

/**
 * The Room class will be the basis for every room in the game
 * showing what objects are stored in a room and what doors
 * the room has.
 * @author LewisMcEwan
 * @param <T>
 *
 */
public class Room implements Serializable{

	private int roomNum;
	private List<Entity> roomEntities;
	private Level level;

	/**
	 * Constructs a new room
	 * @param num	room number
	 */
	public Room(int num, Level level){
		this.roomNum = num;
		this.roomEntities = new ArrayList<Entity>();
		generateWalls();
		this.level = level;
	}

	/**
	 * Handles a tick in the game
	 * @param x 	mouse x pos
	 * @param y 	mouse y pos
	 */
	public void tick(float x, float y, int tickNo) {
		if (x != 0 || y != 0) {
			switch (movePlayer(x, y)) {
			case 0:
				getPlayer().moveBy(x, y);
				break;
			case 1:
				getPlayer().moveBy(0, y);
				break;
			case 2:
				getPlayer().moveBy(x, 0);
				break;
			case 3:
				break;
			}
		}
		ArrayList<Entity> toRemove = new ArrayList<Entity>();
		for(Entity e : roomEntities){
			if (e instanceof Projectile) {
				((Projectile) e).tick();
				for (Entity b : roomEntities) {
					if (e.getBoundingBox().intersects(b.getBoundingBox())) {
						if(b instanceof Wall) {
							toRemove.add(e);
							break;
						} else if (b instanceof Monster) {
							((Projectile) e).attack(b);
							toRemove.add(e);
							if (!((Monster) b).isAlive()) {
								toRemove.add(b);
							}
							break;
						}
					}
				}
			} else if(e instanceof MovableEntity) {
				((MovableEntity) e).tick();
			}
		}
		roomEntities.removeAll(toRemove);
	}


	/**
	 * Fills the walls array with wall objects where there should be walls
	 */
	private void generateWalls(){
		String pos = null;
		for(int i = 0; i < Renderer.FLOOR_WIDTH; i++){
			for(int j = 0; j < Renderer.FLOOR_HEIGHT; j++){
				if(i == 0 || i == Renderer.FLOOR_WIDTH-1 || j == 0 || j == Renderer.FLOOR_HEIGHT-1){
					if(i == 0) {
						pos = "top";
					} if (i == Renderer.FLOOR_WIDTH-1) {
						pos = "right";
					} if (j == 0) {
						pos = "left";
					} if (j == Renderer.FLOOR_HEIGHT-1) {
						pos = "bottom";
					}

					roomEntities.add(new Wall(pos, i*Renderer.TILE_SIZE, j*Renderer.TILE_SIZE, 32, 32));
				}
			}
		}
	}

	/**
	 * When player wants to move room through a door
	 * @param d
	 */
	public void goThroughDoor(Door d){
		Room gotoRoom = (this == d.getRoom1()) ? d.getRoom2() : d.getRoom1();
		roomEntities.remove(getPlayer());
		level.gotoRoom(gotoRoom);
	}


	/**
	 * Checks if the player has collided with anything in the room
	 * Different outcomes depending on what the player has
	 * collided with
	 */
	private int movePlayer(float x, float y){
		BoundingBox box = getPlayer().getBoundingBox();
		BoundingBox boxX = new BoundingBox(box.getMinX()+x*2, box.getMinY(), box.getWidth(), box.getHeight());
		BoundingBox boxY = new BoundingBox(box.getMinX(), box.getMinY()+y*2, box.getWidth(), box.getHeight());

		boolean xCollision = false;
		boolean yCollision = false;

		for(Entity e : this.roomEntities){
			if (e instanceof Player) {
				continue;
			}
			if(e.getBoundingBox().intersects(boxX) || e.getBoundingBox().intersects(boxY)){
				if(e instanceof Door){
					goThroughDoor( (Door)e );
					break;
				} else if(e instanceof Monster){
					((Monster)e).attack(this.getPlayer());
				}
			}

			if (e instanceof Wall) {
				if(e.getBoundingBox().intersects(boxX)){
					xCollision = true;
				} if (e.getBoundingBox().intersects(boxY)){
					yCollision = true;
				}
			}

		}

		if (xCollision && yCollision) {
			return 3;
		} else if (xCollision) {
			return 1;
		} else if (yCollision) {
			return 2;
		} else {
			return 0;
		}
	}

	/**
	 * This searches the room for a pickupable item
	 * which the player is colliding with
	 */
	public void pickupItem(){
		Entity toRemove = null;
		for(Entity e : this.roomEntities){
			if(e.getBoundingBox().intersects(this.getPlayer().getBoundingBox())){
				if(e instanceof Pickupable){
					this.getPlayer().pickup( (Pickupable)e );
					toRemove = e;
				}
			}
		}
		roomEntities.remove(toRemove);
	}
	
	/**
	 * 
	 */
	public void dropItem() {
		Pickupable toAdd = this.getPlayer().drop();
		if(toAdd != null) {
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
			if(e instanceof Door){
				if( ((Door)e).getDoorNum() == i ){
					return (Door)e;
				}
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
		List<Door> doors = new ArrayList<Door>();
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

	/**
	 * Scans the extended surrounding player box for a monster and attacks it
	 * @return
	 * 		true if an attack was successfully carried out
	 */
	public void use(float x, float y) {
		Pickupable hand = getPlayer().getHand();
		if (hand instanceof MeleeWeapon) {
			for(Entity e : this.roomEntities){
				if(e.getBoundingBox().intersects(this.getPlayer().getExtendedBoundingBox())){
					if(e instanceof Monster){
						((MeleeWeapon) hand).attack(e);
					}
				}
			} //End of entities iteration
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
