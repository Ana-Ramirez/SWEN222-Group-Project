package entities;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public abstract class Pickupable extends MovableEntity {

	protected Pickupable(float x, float y, Type type) {
		super(x, y, type);
	}
	
	public boolean pickup() {
		throw new NotImplementedException();
	}

}
