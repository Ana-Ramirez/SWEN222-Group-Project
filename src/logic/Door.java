package logic;

import entities.Entity;
import entities.Pickupable;
import entities.StationaryEntity;
import entities.Type;
import game.GameException;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * Door will have position on a wall at N, S, E or W,
 * specified by 0 - 3
 * @author lewismcewan
 *
 */
public class Door extends StationaryEntity{
	
	private Room room1, room2;
	private boolean doorLocked = true;
	private Pickupable unlockItem;
	private int doorNum;
	private int position;
	
	/**
	 * This creates a new door, linking the two rooms for which this door is a passage for.
	 * @param rm1			first room linked to
	 * @param rm2			second room linked to
	 * @param item			item needed to unlock this door
	 * @param doorNum		which number door in the room this is
	 * @param position		the wall this door sits on
	 */
	public Door(Room rm1, Room rm2, Pickupable item, int doorNum, int position) throws Exception{
		super(Integer.toString(doorNum), 10, 10, 10, 10);
		if(rm1 == null || rm2 == null) throw new GameException("A room cannot be null for a door");
		this.room1 = rm1;
		this.room2 = rm2;
		this.unlockItem = item;
		this.doorNum = doorNum;
		this.position = position;
	}
	
	/**
	 * Unlock door if player has correct item
	 * @param item
	 */
	public void unlockDoor(Entity item){
		if(item == this.unlockItem){
			this.doorLocked = false;
		}
	}
	
	/**
	 * @return the first room this door connects to
	 */
	public Room getRoom1(){
		return this.room1;
	}
	
	/**
	 * @return the second room this door connects to
	 */
	public Room getRoom2(){
		return this.room2;
	}
	
	/**
	 * Set the first room this door connects to
	 * @param the room to set room1 to
	 */
	public void setRoom1(Room room){
		this.room1 = room;
	}
	
	/**
	 * Set the second room this door connects to
	 * @param the room to set room2 to
	 */
	public void setRoom2(Room room){
		this.room2 = room;
	}
	
	/**
	 * @return the number door this is
	 */
	public int getDoorNum(){
		return this.doorNum;
	}
	
	/**
	 * Return the unlock item for this door
	 * @return
	 */
	public Entity getUnlockItem(){
		return this.unlockItem;
	}
	
	/**
	 * @return status of door lock
	 */
	public boolean isLocked(){
		return this.doorLocked;
	}
	
	/**
	 * @return the door position
	 */
	public int getDoorPosition(){
		return this.position;
	}
}
