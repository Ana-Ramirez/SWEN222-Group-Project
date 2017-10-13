package entities;

import resources.ImgResources;

/**
 * Abstract class for an entity that cannot move/be moved
 * @author Nick Lauder
 *
 */
public class StationaryEntity extends Entities {
	private static final long serialVersionUID = -8746477209656357198L;

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
	public StationaryEntity(double x, double y, int width, int height, ImgResources img) {
		super(x, y, width, height, null);
		setImage(img);
	}

	@Override
	protected boolean hit(int damage) {
		return false;
	}
}
