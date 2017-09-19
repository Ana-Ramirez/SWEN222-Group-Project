package logic;

import java.util.ArrayList;
import java.util.List;

import entities.Entity;
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
	public void moveRoom(Door d){
		throw new NotImplementedException();
	}
	
	/**
	 * Is player's position move valid
	 * Will they bump into anything
	 * @return
	 */
	public boolean movePlayer(){
		throw new NotImplementedException();
	}
	
	public int getMonsterHealth(){
		throw new NotImplementedException();
	}
	
	/**
	 * Add a door to this room
	 * @param d		door to add
	 */
	public void addDoor(Door d){
		//Door door = new Door(this, other);
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
			if(d.getNum().equals(i)){
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
	public boolean doorLocked(){
		throw new NotImplementedException();
	}
	
	
}
