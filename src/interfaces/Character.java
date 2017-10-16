package interfaces;

/**
 * 
 * @author Tim Gastrell
 * @author laudernich1
 *
 */
public interface Character extends Moveable {
	
	/**
	 * Checks if the character has more than 0 lives
	 * @return
	 * 		true if the character is alive
	 */
	public boolean isAlive();
	
	/**
	 * Returns the amount of health the character has left
	 * @return
	 * 		the int of the characters remaining lives
	 */
	public int getLives();
	
}
