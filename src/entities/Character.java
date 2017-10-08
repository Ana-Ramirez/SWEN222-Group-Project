package entities;


/**
 * Abstract class of all different character types
 * @author Nick Lauder
 *
 */
public abstract class Character extends MovableEntity {
	protected int lives;


	protected Character(String name, float x, float y, int width, int height, Type type, int lives) {
		super(name, x, y, width, height, type);
		this.lives = lives;

	}

	@Override
	protected boolean hit(int damage) {
		if (lives > 0) {
			lives-=damage;
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
	 * Returns the amount of health the character has left
	 * @return
	 * 		the int of the characters remaining lives
	 */
	public int getLives() {
		return lives;
	}
}
