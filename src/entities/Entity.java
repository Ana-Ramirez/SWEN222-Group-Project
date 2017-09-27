package entities;

import java.awt.Image;

/**
 * An abstract class that is the super for all entities.
 * This class includes functionality shared by all other entities
 * @author Nick Lauder
 *
 */
public abstract class Entity {
	protected float x;
	protected float y;
	protected int size;
	protected String name;
	protected Type type;
	private Image image;

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
	 * Returns the width/height of the entity
	 * @return
	 * 		an int value of the entity dimensions
	 */
	public int getSize() {
		return size;
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
	 * Hits the entity
	 * @return
	 * 		true if damage taken
	 */
	protected abstract boolean hit();

}
