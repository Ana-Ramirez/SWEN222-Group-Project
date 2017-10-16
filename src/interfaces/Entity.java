package interfaces;


import java.awt.geom.Rectangle2D;
import java.io.Serializable;

import resources.ImgResources;

/**
 * Overall entity interface which all different entities are children of
 * @author laudernich1
 *
 */
public interface Entity extends Serializable {

	/**
	 * Gets the type assigned to the object
	 * @return
	 * 		the type of the entity
	 */
	public EntityType getType();

	/**
	 * Returns the x coordinate of the entity
	 * @return
	 * 		a double value of the x coordinate
	 */
	public double getX();

	/**
	 * Returns the y coordinate of the entity
	 * @return
	 * 		a double value of the y coordinate
	 */
	public double getY();

	/**
	 * Returns the width of the entity
	 * @return
	 * 		an double value of the entity width
	 */
	public double getWidth();

	/**
	 * Returns the height of the entity
	 * @return
	 * 		an double value of the entity height
	 */
	public double getHeight();

	/**
	 * Sets the new image reference for the entity
	 * @param image
	 * 		the image reference to use
	 */
	public void setImage(ImgResources image);


	/**
	 * Returns the image assigned to the entity
	 * @return
	 * 		the image reference object
	 */
	public ImgResources getImage();


	/**
	 * Returns the bounding box of the entity
	 * @return
	 * 		the rectangle that surrounds the entity
	 */
	public Rectangle2D.Double getBoundingBox();
}
