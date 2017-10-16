package interfaces;

import logic.Room;

public interface DoorInterface extends Entity{
	
	public void setDoorPosition(int newPos);
	
	public void unlockDoor(Entity item);
	
	public Room getRoom1();
	
	public Room getRoom2();
	
	public void setRoom1(Room room);
	
	public void setRoom2(Room room);
	
	public int getDoorNum();
	
	public Entity getUnlockItem();
	
	public boolean isLocked();
	
	public int getDoorPosition();
	
//	protected boolean hit(int damage);
	
	public void flipDoor();
}
