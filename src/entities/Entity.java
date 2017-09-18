package entities;


public interface Entity {
	public float getX();
	public float getY();
	public int getSize();
	public boolean attack(Weapon weapon);
}
