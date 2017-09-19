package logic;

import java.util.ArrayList;
import java.util.List;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class Level {
	
	int numRooms = 0;
	List<Room> rooms;
	
	public Level(int numRooms){
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
	
	
}
