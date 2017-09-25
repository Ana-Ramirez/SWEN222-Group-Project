package entities;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * Abstract class for a pickupable object
 * @author Nick Lauder
 *
 */
public abstract class Pickupable extends MovableEntity {
	
	/**
	 * Picks up the object
	 * @return
	 * 		true if successful, else false
	 */
	public boolean pickup() {
		throw new NotImplementedException();
	}

}
