package entities;


/**
 * Abstract class for a movable entity
 * @author Nick Lauder
 *
 */
public abstract class MovableEntity extends Entity {


	/**
	 * Moves the entity a given amount on each axis
	 * @param x
	 * 		the amount to change x by
	 * @param y
	 * 		the amount to change y by
	 * @return
	 * 		true if successful, else false
	 */
	public boolean moveBy(float x, float y) {
		this.x += x;
		this.y += y;
		return true;
		//TODO proper success handling
	}


	/**
	 * Moves the entity to a given position on each axis
	 * @param x
	 * 		the x value to use
	 * @param y
	 * 		the y value to use
	 * @return
	 * 		true if successful, else false
	 */
	public boolean moveTo(float x, float y) {
		this.x = x;
		this.y = y;
		return true;
		//TODO proper success handling
	}

}
