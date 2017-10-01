package entities;


/**
 * Abstract class of all different character types
 * @author Nick Lauder
 *
 */
public abstract class Character extends MovableEntity {
	private int lives;


	public Character(String name, float x, float y, int width, int height, Type type, int lives) {
		super(name, x, y, width, height, type);
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


	@Override
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
