package logic;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class Door {
	
	private Room startRoom, endRoom;
	private boolean doorLocked = true;
	private Entity unlockItem;
	
	public Door(Room rm1, Room rm2, Entity item){
		this.startRoom = rm1;
		this.endRoom = rm2;
		this.unlockItem = item;
	}
	
	/**
	 * Unlock door if player has correct item
	 * @param item
	 */
	public void unlockDoor(Entity item){
		throw new NotImplementedException();
	}
	
	public Room getRoom1(){
		throw new NotImplementedException();
	}
	
	public Room getRoom2(){
		throw new NotImplementedException();
	}
	
	public void setRoom1(){
		throw new NotImplementedException();
	}
	
	public void setRoom2(){
		throw new NotImplementedException();
	}
}
