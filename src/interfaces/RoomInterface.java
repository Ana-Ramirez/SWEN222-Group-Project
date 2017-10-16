package interfaces;

import java.util.List;

import entities.Player;
import logic.Door;

public interface RoomInterface {
	
	public void tick(float x, float y, int tickNo);
	
	public void goThroughDoor(Door d);
	
	public void pickupItem();
	
	public void dropItem();
	
	public void addEntity(Entity item);
	
	public Door getDoor(int i);
	
	public boolean doorLocked(Door d);
	
	public List<Door> getDoors();
	
	public int getRoomNum();
	
	public Player getPlayer();
	
	public List<Entity> getEntities();
	
	public void use(float x, float y);
}
