package entities;


public interface Entity {
	public Type getType();
	public float getX();
	public float getY();
	public int getSize();
	public boolean receiveHit(Weapon weapon);
}
