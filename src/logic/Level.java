package logic;

import java.util.ArrayList;
import java.util.List;

import entities.Consumable;
import interfaces.Entity;
import entities.Gun;
import entities.MeleeWeapon;
import entities.Monster;
import entities.Player;
import entities.Type;
import resources.ImgResources;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * This class is the Model of our MVC patterned game
 * Level acts as a container for all the classes handling
 * making up the layout of the game. It ties them all together
 * @author Lewis
 *
 */
public class Level {
	
	private List<Room> rooms;
	private Player player;
	private boolean gotPatrick = false;
	
	/**
	 * Create the new level, passing in a new player 
	 * @param player
	 * @throws Exception 
	 */
	public Level(Player player){
		this.player = player;
		this.rooms = new ArrayList<Room>();
		initialise();
	}
	
	/**
	 * create rooms and add to this level
	 * @throws Exception 
	 */
	private void initialise(){
		//create rooms and doors
		Room room1 = new Room(1);
		Room room2 = new Room(2);
		Room room3 = new Room(3);
		Room room4 = new Room(4);
		Room room5 = new Room(5);
		
		Door door1 = new Door(room1, room5, null, 1, 0);
		Door door2 = new Door(room2, room5, null, 2, 1);
		Door door3 = new Door(room3, room5, null, 3, 2);
		Door door4 = new Door(room4, room5, null, 4, 3);
		
		//add doors to rooms
		room1.addEntity(door1);
		room2.addEntity(door2);
		room3.addEntity(door3);
		room4.addEntity(door4);
		
		room5.addEntity(door1);
		room5.addEntity(door2);
		room5.addEntity(door3);
		room5.addEntity(door4);
		
		//add rooms to the level
		this.rooms.add(room1);
		this.rooms.add(room2);
		this.rooms.add(room3);
		this.rooms.add(room4);
		this.rooms.add(room5);
		
		//create entities to add
		Consumable consumable = new Consumable("food", 20, 40, 20, 20, "eat", ImgResources.LIFE.img);
		Gun gun = new Gun("gun", 300, 300, 20, 20, Type.EARTH, 10, ImgResources.GUN.img, ImgResources.CONSOLE1.img);
		MeleeWeapon melee = new MeleeWeapon("knife", 100, 100, 20, 20, Type.WATER, 40, ImgResources.CONSOLE1.img);
		Monster monster = new Monster("monster", 200, 200, 50, 50, Type.EARTH, gun, ImgResources.CONSOLE1.img);
		
		//add to rooms
		room1.addEntity(gun);
		
		room2.addEntity(monster);
		room2.addEntity(consumable);
		
		room3.addEntity(monster);
		room3.addEntity(monster);
		room3.addEntity(melee);
		
		room4.addEntity(consumable);
		room4.addEntity(consumable);
		
		
	}
	
	/**
	 * Return a specific room from this level
	 * @param i		the room number
	 * @return		the room
	 */
	public Room getRoom(int i){
		for(Room r : this.rooms){
			if(r.getRoomNum() == i){
				return r;
			}
		}
		return null;
	}
	
	/**
	 * @return the rooms in this level
	 */
	public List<Room> getRooms(){
		return this.rooms;
	}
	
	/**
	 * @return the player in the game
	 */
	public Entity getPlayer(){
		return this.player;
	}
	
	/**
	 * @return if Patrick has been reached and the game is over
	 */
	public boolean gameOver(){
		return this.gotPatrick;
	}
	
	/**
	 * @return the room the player is currently in
	 */
	public Room getCurrentRoom(){
		for(Room r : this.rooms){
			if(r.getPlayer() != null){
				return r;
			}
		}
		return null;
	}
	
}
