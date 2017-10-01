package entities;


/**
 * Abstract class of all different character types
 * @author Nick Lauder
 *
 */
public abstract class Character extends MovableEntity {
	private int lives;

	protected Character(int lives) {
		this.lives = lives;
	}


	@Override
	protected boolean hit() {
		if (lives > 0) {
			lives--;
			return true;
		}
		return false;
	}


	/**
	 * Removes a specific amount of lives from a caracter
	 * @param damage
	 * 		the amount of lives to remove
	 * @return
	 * 		true if damage given
	 */
	protected boolean hit(int damage) {
		if (lives > 0) {
			lives--;
			return true;
		}
		return false;
	}


	/**
	 * Checks if the player has more than 0 lives
	 * @return
	 * 		true if the player is alive
	 */
	public boolean isAlive() {
		return lives > 0;
	}


	/**
	 * Sets the new coordinates of the player
	 * @param x
	 * 		the float of the new x coordinate
	 * @param y
	 * 		the float of the new y coordinate
	 */
	public void setCoor(float x, float y) {
		this.x = x;
		this.y = y;
	}


	/**
	 * Returns the amount of health the character has left
	 * @return
	 * 		the int of the characters remaining lives
	 */
	public int getLives() {
		return lives;
	}
}
