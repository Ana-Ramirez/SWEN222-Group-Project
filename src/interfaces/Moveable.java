package interfaces;

/**
 * Public interface for a movable entity allowing libraries to use this and be independtly testable
 * @author laudernich1
 * 
 */
public interface Moveable extends Entity{
	
	/**
	 * Moves the entity a given amount on each axis
	 * @param x
	 * 		the amount to change x by
	 * @param y
	 * 		the amount to change y by
	 */
	public void moveBy(double x, double y);


	/**
	 * Moves the entity to a given position on each axis
	 * @param x
	 * 		the x value to use
	 * @param y
	 * 		the y value to use
	 */
	public void moveTo(double x, double y);
	
	/**
	 * Executes code designed to by used on every tick of the game, such as attacking
	 */
	public abstract void tick(); 
}
