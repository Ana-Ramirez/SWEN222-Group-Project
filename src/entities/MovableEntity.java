package entities;


public abstract class MovableEntity implements Entity {
	private float x;
	private float y;
	private int size;
	private Type type;
	
	public MovableEntity(float x, float y, Type type) {
		this.x = x;
		this.y = y;
		this.type = type;
		
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
	
	public boolean receiveHit(Weapon weapon) {
		return false;
	}
	
	public Type getType() {
		return type;
	}

}
