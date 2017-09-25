package entities;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public abstract class Pickupable extends MovableEntity {
	
	public boolean pickup() {
		throw new NotImplementedException();
	}

}
