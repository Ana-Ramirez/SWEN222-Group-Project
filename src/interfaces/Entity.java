package interfaces;


import java.awt.geom.Rectangle2D;
import java.io.Serializable;

import resources.ImgResources;

public interface Entity extends Serializable {

	/**
	 * Gets the type assigned to the object
	 * @return
	 */
	public EntityType getType();

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
	public double getWidth();

	/**
	 * Returns the width of the entity
	 * @return
	 * 		an int value of the entity dimensions
	 */
	public double getHeight();

	/**
	 * Returns the name assigned to the entity
	 * @return
	 * 		a string name of the entity
	 
	public String getName();
*/

	/**
	 * Sets the new image for the entity
	 * @param image
	 * 		the image to use
	 */
	public void setImage(ImgResources image);


	/**
	 * Returns the image assigned to the entity
	 * @return
	 * 		the image object
	 */
	public ImgResources getImage();


	/**
	 * Returns the image assigned to the entity
	 * @return
	 * 		the image object
	 */
	public Rectangle2D.Double getBoundingBox();
}
