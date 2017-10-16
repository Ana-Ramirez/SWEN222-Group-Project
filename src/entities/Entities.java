package entities;


import java.awt.geom.Rectangle2D;

import interfaces.Entity;
import interfaces.EntityType;
import resources.ImgResources;

/**
 * An abstract class that is the super for all entities.
 * This class includes functionality shared by all other entities
 * @author laudernich1
 *
 */
public abstract class Entities implements Entity {
	private static final long serialVersionUID = 2833150026302394689L;
	private double width;
	private double height;
	protected double x;
	protected double y;
	private EntityType type;
	private ImgResources image;

	/**
	 * Protected constructor for entities
	 * @param x
	 * 		the x coordinate to use
	 * @param y
	 * 		the y coordinate to use
	 * @param width
	 * 		the width to use
	 * @param height
	 * 		the height to use
	 * @param type
	 * 		the type to use
	 */
	protected Entities (double x, double y, double width, double height, EntityType type) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.type = type;
	}
	
	/**
	 * Public constructor for entities, including an image reference
	 * @param x
	 * 		the x coordinate to use
	 * @param y
	 * 		the y coordinate to use
	 * @param width
	 * 		the width to use
	 * @param height
	 * 		the height to use
	 * @param type
	 * 		the type to use
	 * @param img
	 * 		the image reference to use
	 */
	public Entities (double x, double y, double width, double height, EntityType type, ImgResources img) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.type = type;
		this.image = img;
	}

	public EntityType getType() {
		return type;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public double getWidth() {
		return width;
	}

	public double getHeight() {
		return height;
	}

	public void setImage(ImgResources image) {
		this.image = image;
	}

	public ImgResources getImage() {
		return image;
	}

	public Rectangle2D.Double getBoundingBox() {
		return new Rectangle2D.Double(getX(), getY(), getWidth(), getHeight());
	}

	/**
	 * Handles hitting an entity with a given amount of damage
	 * @param damage
	 * 		the damage to apply
	 * @return
	 * 		true if the hit dealt damage
	 */
	protected abstract boolean hit(int damage);
}
