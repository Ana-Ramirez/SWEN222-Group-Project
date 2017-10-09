package interfaces;

import entities.Type;
import javafx.geometry.BoundingBox;
import javafx.scene.image.Image;

public interface Entity {

	/**
	 * Gets the type assigned to the object
	 * @return
	 */
	public Type getType();

	/**
	 * Returns the x coordinate of the entity
	 * @return
	 * 		a float value of the x coordinate
	 */
	public double getX();

	/**
	 * Returns the y coordinate of the entity
	 * @return
	 * 		a float value of the y coordinate
	 */
	public double getY();

	/**
	 * Returns the width of the entity
	 * @return
	 * 		an int value of the entity dimensions
	 */
	public int getWidth();

	/**
	 * Returns the width of the entity
	 * @return
	 * 		an int value of the entity dimensions
	 */
	public int getHeight();

	/**
	 * Returns the name assigned to the entity
	 * @return
	 * 		a string name of the entity
	 */
	public String getName();


	/**
	 * Sets the new image for the entity
	 * @param image
	 * 		the image to use
	 */
	public void setImage(Image image);


	/**
	 * Returns the image assigned to the entity
	 * @return
	 * 		the image object
	 */
	public Image getImage();


	/**
	 * Returns the image assigned to the entity
	 * @return
	 * 		the image object
	 */
	public BoundingBox getBoundingBox();

}
