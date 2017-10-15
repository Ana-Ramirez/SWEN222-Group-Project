package interfaces;

/**
 * 
 * @author Tim Gastrell
 *
 */
public interface Character extends MoveableEntity{
	
	public boolean hit(int damage);
	
	/**
	 * Checks if the player has more than 0 lives
	 * @return
	 * 		true if the player is alive
	 */
	public boolean isAlive();
	
	/**
	 * Returns the amount of health the character has left
	 * @return
	 * 		the int of the characters remaining lives
	 */
	public int getLives();
	
}
