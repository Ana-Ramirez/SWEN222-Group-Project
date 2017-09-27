package entities;

public abstract class StationaryEntity implements Entity {
	private float x;
	private float y;
	
	protected StationaryEntity(float x, float y) {
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
	
	public boolean receiveHit(Weapon weapon) {
		return false;
	}

}
