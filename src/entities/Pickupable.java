package entities;


/**
 * Abstract class for a pickupable object
 * @author Nick Lauder
 *
 */
public abstract class Pickupable extends MovableEntity {

	protected Pickupable(String name, double x, double y, int width, int height, Type type) {
		super(name, x, y, width, height, type);
	}

	@Override
	protected boolean hit(int damage) {
		return false;
	}
}
