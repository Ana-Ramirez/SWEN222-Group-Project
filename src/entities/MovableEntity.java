package entities;


/**
 * Abstract class for a movable entity
 * @author Nick Lauder
 *
 */
public abstract class MovableEntity extends Entities {
	protected double speed = 1;

	protected MovableEntity(String name, double x, double y, int width, int height, Type type) {
		super(name, x, y, width, height, type);
	}
	
	/**
	 * Moves the entity a given amount on each axis
	 * @param x
	 * 		the amount to change x by
	 * @param y
	 * 		the amount to change y by
	 * @return
	 * 		true if successful, else false
	 */
	public boolean moveBy(double x, double y) {
		this.x += x*2;
		this.y += y*2;
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
	public boolean moveTo(double x, double y) {
		this.x = x;
		this.y = y;
		return true;
		//TODO proper success handling
	}
	
	public double getSpeed() {
		return speed;
	}
	
	public abstract void tick(); 

}
