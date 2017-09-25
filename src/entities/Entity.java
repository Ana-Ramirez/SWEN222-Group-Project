package entities;

/**
 * An abstract class that is the super for all entities.
 * This class includes functionality shared by all other entities
 * @author Nick Lauder
 *
 */
public abstract class Entity {
	protected float x;
	protected float y;
	protected String name;
	protected Type type;
	
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
		return 0;
	}
	
	/**
	 * Returns the name assigned to the entity
	 * @return
	 * 		a string name of the entity
	 */
	public String getName() {
		return name;
	}
	
}
