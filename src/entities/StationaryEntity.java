package entities;

import javafx.scene.image.Image;
import resources.ImgResources;

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
	public StationaryEntity(String name, double x, double y, int width, int height, ImgResources img) {
		super(name, x, y, width, height, null);
		setImage(img);
	}

	@Override
	protected boolean hit(int damage) {
		return false;
	}

	/**
	 * Sets the x and y coordinates
	 * @param x
	 * 		the double of the x
	 * @param y
	 * 		the double of the y
	 */
	public void setXY(double x, double y) {
		this.x = x;
		this.y = y;
	}
}
