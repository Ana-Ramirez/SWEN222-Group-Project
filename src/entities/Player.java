package entities;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * Player object, the entity that is controlled by the user
 * @author Nick Lauder
 *
 */
public class Player extends MovableEntity {
	private Pickupable[] inventory;
	private Pickupable hand;
	private int health;
	
	/**
	 * Creates a new playable character
	 * @param x
	 * 		the float of the starting x coordinate
	 * @param y
	 * 		the float of the starting y coordinate
	 */
	public Player(float x, float y) {
		this.x = x;
		this.y = y;
		health = 100;
	}
	
	/**
	 * Returns the amount of health the player has left
	 * @return
	 * 		the int of the players remaining health
	 */
	public int getHealth() {
		return health;
	}
	
	/**
	 * Sets the new coordinates of the player
	 * @param x
	 * 		the float of the new x coordinate
	 * @param y
	 * 		the float of the new y coordinate
	 */
	public void setCoor(float x, float y) {
		throw new NotImplementedException();
	}
	
	/**
	 * Changes the players help item to 
	 * the next item in the inventory
	 * @return
	 * 		true if successful, else false
	 */
	public boolean changeHand() {
		throw new NotImplementedException();
	}
	
	@Override
	public String getName() {
		return "Tim";
	}
	
	
}
