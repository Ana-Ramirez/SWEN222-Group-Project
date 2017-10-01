package logic;

import java.util.ArrayList;
import java.util.List;

import entities.Entity;
import entities.Monster;
import entities.Pickupable;
import entities.Player;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

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
	private List<Entity> roomItems;
	private List<Door> doors;
	private Player player = null;
	
	/**
	 * Constructs a new room
	 * @param num	room number
	 */
	public Room(int num){
		this.roomNum = num;
		this.roomItems = new ArrayList<Entity>();
		this.doors = new ArrayList<Door>();
	}
	
	/**
	 * When player wants to move room through a door
	 * @param d
	 */
	public void goThroughDoor(Door d){
		for(Door door : this.doors){
			if(d == door){
				Room toMoveInto = (this == d.getRoom1()) ? d.getRoom2() : d.getRoom1();
				toMoveInto.setPlayer(this.player);
				this.player = null;
			}
		}
	}
	
	/**
	 * Is player's position move valid
	 * Will they bump into anything
	 * If bumping into a monster, cannot move there
	 * If bumping into a pickupable, it can still move there
	 * @return
	 */
	public boolean movePlayer(float x, float y){
		for(Entity e : this.roomItems){
			if(e.getBoundingBox().intersects(player.getBoundingBox())){
				if(!(e instanceof Pickupable)){
					return false;
				}
			}
		}
		return true;	//they can move
	}
	
	/**
	 * Check if player's bounding box collides with a door
	 * @return
	 */
	public boolean doorCollision(){
		for(Door d : this.doors){
			if(this.player.getBoundingBox().intersects(d.getBoundingBox())){
				return true;
			}
		}
		return false;	//no collision
	}
	
	/**
	 * 
	 * @param monster
	 * @return
	 */
	public Entity getMonster(Monster monster){
		for(Entity e : this.roomItems){
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
		this.doors.add(d);
	}
	
	/**
	 * Add an item to this room
	 * @param item		item to add
	 */
	public void addItem(Entity item){
		this.roomItems.add(item);
	}
	
	/**
	 * Removes an item from the 
	 * @param s
	 * @return
	 */
	public boolean removeItem(String s){
		for(int i = 0; i < this.roomItems.size(); i++){
			if(this.roomItems.get(i).getName().equals(s)){
				this.roomItems.remove(i);
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
		for(Door d : this.doors){
			if(d.getDoorNum() == i){
				return d;
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
		for(Entity item : this.roomItems){
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
		return this.doors;
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
	
}
