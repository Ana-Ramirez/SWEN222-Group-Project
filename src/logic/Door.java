package logic;

import interfaces.Entity;
import entities.Entities;
import entities.Pickupable;
import resources.ImgResources;
import view.Renderer;

/**
 * Door will have position on a wall at N, S, E or W,
 * specified by 0 - 3
 * @author lewismcewan
 *
 */
public class Door extends Entities {

	private static final long serialVersionUID = 1434461830741351179L;
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
	public Door(Room rm1, Room rm2, Pickupable item, int doorNum, int position) {
		//here the x, y, width, height are just placed in as fillers in the super. They are actually set below
		super(Renderer.ROOM_WIDTH, Renderer.ROOM_HEIGHT, 32, 32, null, null);

		setDoorPosition(position);

		this.room1 = rm1;
		this.room2 = rm2;
		this.unlockItem = item;
		this.doorNum = doorNum;
		this.position = position;
	}

	/**
	 * This is used to set initial position for all rooms.
	 * When being used in initialise() in Level.class, used to flip position
	 * of door from what was added to corresponding room.
	 * This way the doors line up on the map.
	 * @param newPos	The new wall to put this door on
	 */
	public void setDoorPosition(int newPos){
		String image = null;
		this.position = newPos;
		double x = 0;
		double y = 0;
		if(newPos == 0){ 			//NORTH
			image = "STAIRSTOP";
			x = Renderer.ROOM_WIDTH/2 - Renderer.TILE_SIZE/2;
			y = 0;
		}else if(newPos == 1){ 		//SOUTH
			image = "STAIRSBOT";
			x = Renderer.ROOM_WIDTH/2 - Renderer.TILE_SIZE/2;
			y = Renderer.ROOM_HEIGHT - Renderer.TILE_SIZE;
		}else if(newPos == 2){ 		//EAST
			image = "STAIRSRIGHT";
			x = Renderer.ROOM_WIDTH - Renderer.TILE_SIZE;
			y = Renderer.ROOM_HEIGHT/2 - Renderer.TILE_SIZE/2;
		}else if(newPos == 3){ 		//WEST
			image = "STAIRSLEFT";
			x = 0;
			y = Renderer.ROOM_HEIGHT/2 - Renderer.TILE_SIZE/2;
		}
		ImgResources imageEnum = ImgResources.valueOf(image);
		setImage(imageEnum);
		this.x = x;
		this.y = y;
		
	}

	/**
	 * Unlock door if player has correct item
	 * @param item
	 */
	public void unlockDoor(Entity item){
		if(item == this.unlockItem && item != null){
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

	@Override
	protected boolean hit(int damage) {
		return false;
	}
	
	/**
	 * Using the same door object for the two room objects
	 * it connects, so have to flip door to place
	 * in correct position inside the room being moved into.
	 */
	public void flipDoor() {
		switch (position) {
			case 0:
				setDoorPosition(1);
				break;
			case 1:
				setDoorPosition(0);
				break;
			case 2:
				setDoorPosition(3);
				break;
			case 3:
				setDoorPosition(2);
				break;
		}
	}

}
