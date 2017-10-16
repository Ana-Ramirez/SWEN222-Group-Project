package interfaces;

import java.util.List;

import entities.Player;
import logic.Door;

public interface RoomInterface {
	
	/**
	 * Handles a tick in the game
	 * @param x 		mouse x pos
	 * @param y 		mouse y pos
	 * @param tickNo	number of ticks in game
	 * 
	 */
	public void tick(float x, float y, int tickNo);
	
	/**
	 * When player wants to move room through a door
	 * @param d		the door to move through
	 */
	public void goThroughDoor(Door d);
	
	/**
	 * This searches the room for a pickupable item
	 * which the player is colliding with
	 */
	public void pickupItem();
	
	/**
	 * Adds item the player just dropped to the room
	 */
	public void dropItem();
	
	/**
	 * Add an item to this room
	 * @param item		item to add
	 */
	public void addEntity(Entity item);
	
	/**
	 * Get a door from this room
	 * @param i		door number
	 * @return
	 */
	public Door getDoor(int i);
	
	/**
	 * check if a door is locked
	 * @return
	 */
	public boolean doorLocked(Door d);
	
	/**
	 * @return the list of doors for this room
	 */
	public List<Door> getDoors();
	
	/**
	 * @return room num
	 */
	public int getRoomNum();
	
	/**
	 * Get player in room
	 */
	public Player getPlayer();
	
	/**
	 * @return list of all room entities
	 */
	public List<Entity> getEntities();
	
	/**
	 * Scans the extended surrounding player box for a monster and attacks it
	 * @return
	 * 		true if an attack was successfully carried out
	 */
	public void use(float x, float y);
}
