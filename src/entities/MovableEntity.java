package entities;


public abstract class MovableEntity extends Entity {
		
	protected void setXY(float x, float y) {
		super.x = x; 
		super.y = y;
	}
	
}
