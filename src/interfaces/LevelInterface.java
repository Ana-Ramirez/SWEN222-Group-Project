package interfaces;

import java.util.List;

import interfaces.Monster;
import interfaces.Player;
import logic.Room;

public interface LevelInterface {
	
	/**
	 * Return a specific room from this level
	 * @param i		the room number
	 * @return		the room			
	 */
	public Room getRoom(int i);
	
//	protected void gotoRoom(Room r);
	
	/**
	 * @return the rooms in this level
	 */
	public List<Room> getRooms();
	
	/**
	 * @return the player in the game
	 */
	public Player getPlayer();
	
	/**
	 * @return the room the player is currently in
	 */
	public Room getCurrentRoom();
	
	/**
	 * Setter for current room, used in testing
	 */
	public void setCurrentRoom(Room room);
	
	/**
	 * @return the boss
	 */
	public Monster getBoss();
	
	/**
	 * Set the boss for the level
	 */
	public void setBoss(Monster boss);
	
	/**
	 * Sets the isLeft field to the boolean b
	 * @param b
	 */
	public void setLeft(boolean b);
	
	/**
	 * @return if the player is facing left
	 */
	public boolean isLeft();	
}
