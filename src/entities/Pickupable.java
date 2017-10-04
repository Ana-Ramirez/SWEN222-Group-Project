package entities;

import javafx.scene.image.Image;

/**
 * Abstract class for a pickupable object
 * @author Nick Lauder
 *
 */
public abstract class Pickupable extends MovableEntity {

	protected Pickupable(String name, float x, float y, int width, int height, Type type) {
		super(name, x, y, width, height, type);
	}

	@Override
	protected boolean hit() {
		return false;
	}

	@Override
	protected boolean hit(int damage) {
		return false;
	}

	protected abstract String use();
}
