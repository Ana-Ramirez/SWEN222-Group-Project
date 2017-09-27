package entities;


/**
 * Abstract class for a pickupable object
 * @author Nick Lauder
 *
 */
public abstract class Pickupable extends MovableEntity {

	@Override
	protected boolean hit() {
		return false;
	}
}
