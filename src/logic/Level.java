package logic;

import java.util.ArrayList;
import java.util.List;

import entities.Entity;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * This class is the Model of our MVC patterned game
 * @author Lewis
 *
 */
public class Level {
	
	private int numRooms = 0;
	private List<Room> rooms;
	private Entity player;
	private boolean gotPatrick = false;
	
	public Level(Entity player, int numRooms){
		this.player = player;
		this.numRooms = numRooms;
		this.rooms = new ArrayList<Room>();
		initialise();
	}
	
	/**
	 * create rooms and add to this level
	 */
	private void initialise(){
		throw new NotImplementedException();
	}
	
	/**
	 * Is move by player valid
	 */
	public boolean canPlayerMove(){
		//Room.movePlayer();
		throw new NotImplementedException();
	}
	
	public Entity getPlayer(){
		throw new NotImplementedException();
	}
	
	public boolean gameOver(){
		throw new NotImplementedException();
	}
	
	public boolean gotPatrick(){
//		this.gotPatrick = true;
		throw new NotImplementedException();
	}
	
	
	
}
