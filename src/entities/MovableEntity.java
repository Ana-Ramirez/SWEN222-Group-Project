package entities;

public abstract class MovableEntity implements Entity {
	private float x;
	private float y;
	
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

}
