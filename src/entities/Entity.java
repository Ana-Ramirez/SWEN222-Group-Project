package entities;


public abstract class Entity {
	protected float x;
	protected float y;
	protected String name;
	protected Type type;
	
	public Type getType() {
		return type;
	}
	public float getX() {
		return x;
	}
	public float getY() {
		return y;
	}
	public int getSize() {
		return 0;
	}
	
	public String getName() {
		return name;
	}
	
	public abstract boolean receiveHit(Weapon weapon);
}
