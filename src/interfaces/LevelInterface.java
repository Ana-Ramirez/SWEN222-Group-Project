package interfaces;

import java.util.List;

import entities.Monster;
import entities.Player;
import logic.Room;

public interface LevelInterface {
	
	public Room getRoom(int i);
	
//	protected void gotoRoom(Room r);
	
	public List<Room> getRooms();
	
	public Player getPlayer();
	
	public Room getCurrentRoom();
	
	public void setCurrentRoom(Room room);
	
	public Monster getBoss();
	
	public void setBoss(Monster boss);
	
	public void setLeft(boolean b);
	
	public boolean isLeft();	
}
