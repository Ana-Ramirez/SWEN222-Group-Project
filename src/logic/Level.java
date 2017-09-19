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
	 * create a room for the amount specified
	 */
	private void initialise(){
//		for(int i = 0; i < this.numRooms; i++){
//			this.rooms.add(room);
//		}
		throw new NotImplementedException();
	}
}
