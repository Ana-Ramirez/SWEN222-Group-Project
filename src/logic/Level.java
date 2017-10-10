package logic;

import java.util.ArrayList;
import java.util.List;

import entities.Consumable;
import entities.Gun;
import entities.MeleeWeapon;
import entities.Monster;
import entities.Player;
import entities.Type;
import resources.ImgResources;

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
	private Room currentRoom;
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
		currentRoom = rooms.get(0);
		
	}
	
	/**
	 * create rooms and add to this level
	 * @throws Exception 
	 */
	private void initialise(){
		//create rooms and doors
		Room room1 = new Room(1, this);
		Room room2 = new Room(2, this);
		Room room3 = new Room(3, this);
		Room room4 = new Room(4, this);
		Room room5 = new Room(5, this);
		
		//create doors for rooms
		Door door1 = new Door(room1, room5, null, 1, 0);	//north door
		Door door2 = new Door(room2, room5, null, 2, 1);	//south door
		Door door3 = new Door(room3, room5, null, 3, 2);	//east door
		Door door4 = new Door(room4, room5, null, 4, 3);	//west door
		
		//add doors to rooms
		room1.addEntity(door1);
		room2.addEntity(door2);
		room3.addEntity(door3);
		room4.addEntity(door4);
		
		//adding doors to center room; also flipping door
		//position so they line up on the map
		room5.addEntity(door1);
		room5.addEntity(door2);
		room5.addEntity(door3);
		room5.addEntity(door4);
		room5.getDoor(1).setDoorPosition(1);	//now south position
		room5.getDoor(2).setDoorPosition(0);	//now north position
		room5.getDoor(3).setDoorPosition(3);	//now east position
		room5.getDoor(4).setDoorPosition(2);	//now east position
		
		//add rooms to the level
		this.rooms.add(room1);
		this.rooms.add(room2);
		this.rooms.add(room3);
		this.rooms.add(room4);
		this.rooms.add(room5);
		
		//create entities to add
		Consumable consumable = new Consumable("food", 20, 40, 1, 1, "Lives 1", ImgResources.LIFE.img);
		
		Gun gunEarth = new Gun("gunEarth", 300, 300, 1, 1, Type.EARTH, 10, ImgResources.GUN.img, ImgResources.CONSOLE1.img);
		Gun gunFire = new Gun("gunFire", 200, 300, 1, 1, Type.FIRE, 20, ImgResources.GUN.img, ImgResources.CONSOLE1.img);
		MeleeWeapon melee = new MeleeWeapon("knife", 100, 100, 1, 1, Type.WATER, 40, ImgResources.CONSOLE1.img);
		
		Monster monsterEasy = new Monster("monsterEasy", 200, 200, 1, 1, Type.EARTH, gunEarth, ImgResources.MONSTER.img);
		Monster monsterMedium = new Monster("monsterMedium", 200, 200, 2, 2, Type.FIRE, gunFire, ImgResources.MONSTER.img);
		Monster monsterHard = new Monster("monsterHard", 200, 200, 2, 2, Type.WATER, melee, ImgResources.MONSTER.img);

		
		//add to rooms
		room1.addEntity(player);
		room1.addEntity(gunEarth);

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
	
	protected void gotoRoom(Room r) {
		//TODO: Error checking
		currentRoom = r;
		currentRoom.addEntity(player);
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
	public Player getPlayer(){
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
		return currentRoom;
	}
	
}
