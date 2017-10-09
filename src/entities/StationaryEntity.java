package entities;

import javafx.scene.image.Image;

/**
 * Abstract class for an entity that cannot move/be moved
 * @author Nick Lauder
 *
 */
public class StationaryEntity extends Entities {

	/**
	 * Creates a new StationaryEntity
	 * @param name
	 * 		the string name to use
	 * @param x
	 * 		the float x coordinate to use
	 * @param y
	 * 		the float y coordinate to use
	 * @param width
	 * 		the int width to use
	 * @param height
	 * 		the int height to use
	 */
	public StationaryEntity(String name, int x, int y, int width, int height, Image img) {
		super(name, x, y, width, height, null);
		setImage(img);
	}

	@Override
	protected boolean hit(int damage) {
		return false;
	}
}
