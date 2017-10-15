package ai;

import java.io.Serializable;

public class Goal implements Serializable{

	private static final long serialVersionUID = 7785586013801434243L;
	private final float x;
	private final float y;

	public Goal(float x, float y) {
		this.x = x;
		this.y = y;
	}

	protected float getX() {
		return x;
	}

	protected float getY() {
		return y;
	}
}