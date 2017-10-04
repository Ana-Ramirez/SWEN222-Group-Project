package entities;

import javafx.geometry.BoundingBox;
import javafx.scene.image.Image;

/**
 * An abstract class that is the super for all entities.
 * This class includes functionality shared by all other entities
 * @author Nick Lauder
 *
 */
public abstract class Entity {
	protected float x;
	protected float y;
	private int width;
	private int height;
	private String name;
	private Type type;
	private Image image;

	protected Entity (String name, float x, float y, int width, int height, Type type) {
		this.name = name;
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
	public float getX() {
		return x;
	}

	/**
	 * Returns the y coordinate of the entity
	 * @return
	 * 		a float value of the y coordinate
	 */
	public float getY() {
		return y;
	}

	/**
	 * Returns the width of the entity
	 * @return
	 * 		an int value of the entity dimensions
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * Returns the width of the entity
	 * @return
	 * 		an int value of the entity dimensions
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * Returns the name assigned to the entity
	 * @return
	 * 		a string name of the entity
	 */
	public String getName() {
		return name;
	}


	/**
	 * Sets the new image for the entity
	 * @param image
	 * 		the image to use
	 */
	public void setImage(Image image) {
		this.image = image;
	}


	/**
	 * Returns the image assigned to the entity
	 * @return
	 * 		the image object
	 */
	public Image getImage() {
		return image;
	}


	/**
	 * Returns the image assigned to the entity
	 * @return
	 * 		the image object
	 */
	public BoundingBox getBoundingBox() {
		return new BoundingBox(x, y, width, height);
	}


	/**
	 * Removes a specific amount of lives from a caracter
	 * @param damage
	 * 		the amount of lives to remove
	 * @return
	 * 		true if damage given
	 */
	protected abstract boolean hit(int damage);


	/**
	 * Hits the entity
	 * @return
	 * 		true if damage taken
	 */
	protected abstract boolean hit();

}
