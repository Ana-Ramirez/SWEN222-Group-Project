package logic;

import java.util.ArrayList;
import java.util.List;

import entities.Consumable;
import entities.Gun;
import entities.MeleeWeapon;
import interfaces.Entity;
import entities.Monster;
import entities.MovableEntity;
import entities.Pickupable;
import entities.Player;
import entities.Projectile;
import entities.Weapon;

/**
 * The Room class will be the basis for every room in the game
 * showing what objects are stored in a room and what doors
 * the room has.
 * @author LewisMcEwan
 * @param <T>
 *
 */
public class Room {
	
	private int roomNum;
	private List<Entity> roomEntities;
	private Wall[][] walls = new Wall[500][500];
	private Player player = null;
	
	/**
	 * Constructs a new room
	 * @param num	room number
	 */
	public Room(int num){
		this.roomNum = num;
		this.roomEntities = new ArrayList<Entity>();
		generateWalls();
	}
	
	/**
	 * Handles a tick in the game
	 */
	public void tick(float x, float y) {
		player.moveBy(x, y);
		
		for(Entity e : this.roomEntities){
			if(e instanceof MovableEntity) {
				((MovableEntity) e).tick();
			} 
		}
	}
	
	/**
	 * Fills the walls array with wall objects where there should be walls
	 */
	private void generateWalls(){
		String pos = null;
		for(int i = 0; i < this.walls.length; i++){
			for(int j = 0; j < this.walls[i].length; j++){
				if(i == 0 || i == this.walls.length || j == 0 || j == this.walls[i].length){
					if(i == 0){ pos = "top"; }
					else if(i == this.walls.length){ pos = "bottom"; }
					else if(j == 0){ pos = "left"; }
					else if(j == this.walls[i].length){ pos = "right"; }
					walls[i][j] = new Wall(pos);
				}
			}
		}
	}
	
	/**
	 * When player wants to move room through a door
	 * @param d
	 */
	public void goThroughDoor(Door d){
		for(Entity e : this.roomEntities){
			if(e instanceof Door){
				if(e == d){
					Room toMoveInto = (this == d.getRoom1()) ? d.getRoom2() : d.getRoom1();
					toMoveInto.setPlayer(this.player);
					this.player = null;
				}
			}
		}
	}
	
	/**
	 * Checks if the player has collided with anything in the room
	 * Different outcomes depending on what the player has 
	 * collided with
	 */
	public void movePlayer(){
		for(Entity e : this.roomEntities){
			if(e.getBoundingBox().intersects(this.player.getBoundingBox())){
				if(e instanceof Door){
					goThroughDoor( (Door)e );
				} else if(e instanceof Monster){
					
					((Monster)e).attack(this.player);
				} else if(e instanceof Pickupable){
					this.player.pickup( (Pickupable)e );
				} 
			}
		}
	}
	
	/**
	 * This searches the room for a pickupable item
	 * which the player is colliding with
	 */
	public void pickupItem(){
		for(Entity e : this.roomEntities){
			if(e.getBoundingBox().intersects(this.player.getBoundingBox())){
				if(e instanceof Pickupable){
					this.player.pickup( (Pickupable)e );
					this.roomEntities.remove(e);
				}
			}
		}
	}
	
	
	
	//What is the point in this method?????? YOu give it a monster object, it returns the same object as an entity, maybe pass in a name instead?
	/**
	 * 
	 * @param monster
	 * @return
	 */
	public Entity getMonster(Monster monster){		
		if(monster == null){ return null; }
		for(Entity e : this.roomEntities){
			if(e instanceof Monster){	//if it's the same entity
				return e;
			}
		}
		return null;
	}
	
	/**
	 * Add a door to this room
	 * @param d		door to add
	 */
	public void addDoor(Door d){
		this.roomEntities.add(d);
	}
	
	/**
	 * Add an item to this room
	 * @param item		item to add
	 */
	public void addEntity(Entity item){
		this.roomEntities.add(item);
	}
	
	/**
	 * Removes an item from the 
	 * @param s
	 * @return
	 */
	public boolean removeItem(String s){
		for(int i = 0; i < this.roomEntities.size(); i++){
			if(this.roomEntities.get(i).getName().equals(s)){
				this.roomEntities.remove(i);
				return true;
			}
		}
		return false;
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
	 * Get an item from this room
	 * @param s		item name
	 * @return
	 */
	public Entity getItem(String s){
		for(Entity item : this.roomEntities){
			if(item.getName().equals(s)){
				return item;
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
	 * Used to make a player enter into a room through  door.
	 * Can also be used to make the player enter the game initially
	 * and be put into one of the rooms/starting room.
	 * @param player
	 */
	public void setPlayer(Player player){
		this.player = player;
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
		return this.player;
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
	public boolean attack(float x, float y) {
		boolean validAttack = false;
		Pickupable hand = player.getHand();
		if (hand instanceof MeleeWeapon) {
			for(Entity e : this.roomEntities){
				if(e.getBoundingBox().intersects(this.player.getExtendedBoundingBox())){
					if(e instanceof Monster){
						validAttack |= ((MeleeWeapon) hand).attack(e);
					} 
				}
			}
		} else if (hand instanceof Gun) {
			roomEntities.add(((Gun) hand).createProjectile(x, y));
		}
		return validAttack;
	}
	
}
