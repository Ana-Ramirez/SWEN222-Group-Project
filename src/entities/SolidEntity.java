package entities;

public abstract class SolidEntity implements Entity {
	private float x;
	private float y;
	
	public SolidEntity(float x, float y) {
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

}
