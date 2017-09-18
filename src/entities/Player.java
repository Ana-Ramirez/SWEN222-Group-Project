package entities;

public class Player extends MovableEntity {
	
	public Player(float x, float y) {
		super(x, y);
	}
	
	public void setCoor(float x, float y) {
		setXY(x, y);
	}
}
