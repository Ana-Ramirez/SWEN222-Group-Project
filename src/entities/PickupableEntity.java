package entities;

import interfaces.EntityType;
import interfaces.Pickupable;

/**
 * Abstract class for a pickupable object
 * @author laudernich1
 *
 */
public abstract class PickupableEntity extends MovableEntity implements Pickupable {
	private static final long serialVersionUID = 1654057472702704263L;

	PickupableEntity(double x, double y, double width, double height, EntityType type) {
		super(x, y, width, height, type);
	}

	@Override
	protected boolean hit(int damage) {
		return false;
	}

}
