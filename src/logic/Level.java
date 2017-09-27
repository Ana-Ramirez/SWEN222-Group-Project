package logic;

import java.util.ArrayList;
import java.util.List;

import entities.Entity;
import entities.Player;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * This class is the Model of our MVC patterned game
 * @author Lewis
 *
 */
public class Level {
	
	private int numRooms = 0;
	private List<Room> rooms;
	private Player player;
	private boolean gotPatrick = false;
	
	public Level(Player player, int numRooms){
		this.player = player;
		this.numRooms = numRooms;
		this.rooms = new ArrayList<Room>();
		initialise();
	}
	
	/**
	 * create rooms and add to this level
	 */
	private void initialise(){
		Room room1 = new Room(1);
		Room room2 = new Room(2);
		Room room3 = new Room(3);
		Room room4 = new Room(4);
		Room room5 = new Room(5);
		
		Door door1 = new Door(room1, room5, null, 1);
		Door door2 = new Door(room2, room5, null, 2);
		Door door3 = new Door(room3, room5, null, 3);
		Door door4 = new Door(room4, room5, null, 4);
		
		room1.addDoor(door1);
		room2.addDoor(door2);
		room3.addDoor(door3);
		room4.addDoor(door4);
		
		room5.addDoor(door1);
		room5.addDoor(door2);
		room5.addDoor(door3);
		room5.addDoor(door4);
		
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
	
	/**
	 * To check if the game has been won yet or not
	 * @return	 the state of patrick's rescue
	 */
	public boolean gotPatrick(){
		return this.gotPatrick;
	}
	
	
	
}
