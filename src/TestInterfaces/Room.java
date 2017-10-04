package TestInterfaces;

public interface Room {
	
	public void goThroughDoor(Door d);
	
	public void movePlayer();
	
	public boolean doorCollision();
	
	public Entity getMonster(Monster monster);
	
	
}
