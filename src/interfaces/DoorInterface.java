package interfaces;

import logic.Room;

public interface DoorInterface extends Entity{
	
	/**
	 * This is used to set initial position for all rooms.
	 * When being used in initialise() in Level.class, used to flip position
	 * of door from what was added to corresponding room.
	 * This way the doors line up on the map.
	 * @param newPos	The new wall to put this door on
	 */
	public void setDoorPosition(int newPos);
	
	/**
	 * Unlock door if player has correct item
	 * @param item
	 */
	public void unlockDoor(Entity item);
	
	/**
	 * @return the first room this door connects to
	 */
	public Room getRoom1();
	
	/**
	 * @return the second room this door connects to
	 */
	public Room getRoom2();
	
	/**
	 * Set the first room this door connects to
	 * @param the room to set room1 to
	 */
	public void setRoom1(Room room);
	
	/**
	 * Set the second room this door connects to
	 * @param the room to set room2 to
	 */
	public void setRoom2(Room room);
	
	/**
	 * @return the number door this is
	 */
	public int getDoorNum();
	
	/**
	 * Return the unlock item for this door
	 * @return
	 */
	public Entity getUnlockItem();
	
	/**
	 * @return status of door lock
	 */
	public boolean isLocked();
	
	/**
	 * @return the door position
	 */
	public int getDoorPosition();
		
	/**
	 * Using the same door object for the two room objects
	 * it connects, so have to flip door to place
	 * in correct position inside the room being moved into.
	 */
	public void flipDoor();
}
