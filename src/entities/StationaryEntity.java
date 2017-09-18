package entities;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public abstract class StationaryEntity implements Entity {
	private float x;
	private float y;
	
	public StationaryEntity(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	@Override
	public float getX() {
		return x;
	}

	@Override
	public float getY() {
		return y;
	}
	
	public boolean attack(Weapon weapon) {
		throw new NotImplementedException();
	}

}
