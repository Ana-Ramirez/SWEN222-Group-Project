package logic;

import java.util.ArrayList;
import java.util.List;

/**
 * The Room class will be the basis for every room in the game
 * showing what objects are stored in a room and what doors
 * the room has.
 * @author LewisMcEwan
 * @param <T>
 *
 */
public class Room<T> {
	
	private int roomNum;
	private List<T> roomItems;
	private List<Door> doors;
	
	/**
	 * Constructs a new room
	 * @param num	room number
	 */
	public Room(int num){
		this.roomNum = num;
		this.roomItems = new ArrayList<T>();
		this.doors = new ArrayList<Door>();
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
	public void addItem(T item){
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
	public T getItem(String s){
		for(T item : this.roomItems){
			if(item.getName().equals(s)){
				return item;
			}
		}
		return null;
	}
	
	
}