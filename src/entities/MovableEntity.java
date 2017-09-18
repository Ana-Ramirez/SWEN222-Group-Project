package entities;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public abstract class MovableEntity implements Entity {
	private float x;
	private float y;
	private int size;
	
	public MovableEntity(float x, float y) {
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
	
	protected void setXY(float x, float y) {
		this.x = x; 
		this.y = y;
	}
	
	public int getSize() {
		return size;
	}
	
	public boolean attack(Weapon weapon) {
		throw new NotImplementedException();
	}

}
