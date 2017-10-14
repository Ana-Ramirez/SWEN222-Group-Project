package entities;


import interfaces.Entity;
import javafx.geometry.BoundingBox;
import resources.ImgResources;

/**
 * An abstract class that is the super for all entities.
 * This class includes functionality shared by all other entities
 * @author Nick Lauder
 *
 */
public abstract class Entities implements Entity {
	private static final long serialVersionUID = 2833150026302394689L;
	private double width;
	private double height;
	protected double x;
	protected double y;
	private Type type;
	private ImgResources image;

	protected Entities (double x, double y, double width, double height, Type type) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.type = type;
	}

	/**
	 * Gets the type assigned to the object
	 * @return
	 */
	public Type getType() {
		return type;
	}

	/**
	 * Returns the x coordinate of the entity
	 * @return
	 * 		a float value of the x coordinate
	 */
	public double getX() {
		return x;
	}

	/**
	 * Returns the y coordinate of the entity
	 * @return
	 * 		a float value of the y coordinate
	 */
	public double getY() {
		return y;
	}

	/**
	 * Returns the width of the entity
	 * @return
	 * 		an int value of the entity dimensions
	 */
	public double getWidth() {
		return width;
	}

	/**
	 * Returns the width of the entity
	 * @return
	 * 		an int value of the entity dimensions
	 */
	public double getHeight() {
		return height;
	}


	/**
	 * Sets the new image for the entity
	 * @param image
	 * 		the image to use
	 */
	public void setImage(ImgResources image) {
		this.image = image;
	}


	/**
	 * Returns the image assigned to the entity
	 * @return
	 * 		the image object
	 */
	public ImgResources getImage() {
		return image;
	}


	/**
	 * Returns the image assigned to the entity
	 * @return
	 * 		the image object
	 */
	public BoundingBox getBoundingBox() {
		return new BoundingBox(getX(), getY(), getWidth(), getHeight());
	}


	protected abstract boolean hit(int damage);
}
