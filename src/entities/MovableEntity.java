package entities;


/**
 * Abstract class for a movable entity
 * @author Nick Lauder
 *
 */
public abstract class MovableEntity extends Entities {
	private static final long serialVersionUID = -8759622030110383707L;
	protected double speed = 1;

	protected MovableEntity(double x, double y, double width, double height, Type type) {
		super(x, y, width, height, type);
	}
	
	/**
	 * Moves the entity a given amount on each axis
	 * @param x
	 * 		the amount to change x by
	 * @param y
	 * 		the amount to change y by
	 */
	public void moveBy(double x, double y) {
		this.x += x*speed;
		this.y += y*speed;
	}


	/**
	 * Moves the entity to a given position on each axis
	 * @param x
	 * 		the x value to use
	 * @param y
	 * 		the y value to use
	 */
	public void moveTo(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public abstract void tick(); 

}
