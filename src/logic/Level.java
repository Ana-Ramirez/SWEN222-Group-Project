package logic;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import ai.FollowingEnemy;
import entities.Consumable;
import entities.Gun;
import entities.MeleeWeapon;
import entities.Monster;
import entities.Player;
import entities.Type;
import interfaces.Enemies;
import javafx.geometry.BoundingBox;
import resources.ImgResources;
import view.Renderer;

/**
 * This class is the Model of our MVC patterned game
 * Level acts as a container for all the classes handling
 * making up the layout of the game. It ties them all together
 * @author Lewis
 *
 */
public class Level implements Serializable{

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
		room1.addEntity(new Door(room1, room5, null, 1, 0));	//north door
		room2.addEntity(new Door(room2, room5, null, 2, 1));	//south door
		room3.addEntity(new Door(room3, room5, null, 3, 2));	//east door
		room4.addEntity(new Door(room4, room5, null, 4, 3));	//west door
		
		//adding new door objects which are the same but different
		//position as they need to be flipped for the center room
		room5.addEntity(new Door(room1, room5, null, 51, 1));	//south door
		room5.addEntity(new Door(room2, room5, null, 52, 0));	//north door
		room5.addEntity(new Door(room3, room5, null, 53, 3));	//west door
		room5.addEntity(new Door(room4, room5, null, 54, 2));	//east door

		//add rooms to the level
		this.rooms.add(room1);
		this.rooms.add(room2);
		this.rooms.add(room3);
		this.rooms.add(room4);
		this.rooms.add(room5);
		
		//create entities to add
		Consumable live1 = new Consumable(new BoundingBox(20, 40, 32, 32), "Lives 1", ImgResources.LIFE);
		Consumable live2 = new Consumable(new BoundingBox(20, 40, 32, 32), "Lives 1", ImgResources.LIFE);
		Consumable ammo = new Consumable(new BoundingBox(20, 40, 32, 32), "Ammo 20", ImgResources.BULLET);

		Gun gunEarth = new Gun(new BoundingBox(300, 300, 32, 32), Type.EARTH, 10, ImgResources.GUN, ImgResources.BULLET);
		Gun gunFire = new Gun(new BoundingBox(200, 300, 32, 32), Type.FIRE, 20, ImgResources.GUN, ImgResources.BULLET);
		MeleeWeapon melee = new MeleeWeapon(new BoundingBox(100, 100, 32, 32), Type.WATER, 40, ImgResources.CONSOLE1);

		Enemies pattern = new FollowingEnemy(player);

		Monster monsterEasy = new Monster(new BoundingBox(200, 200, 32, 32), Type.EARTH, gunEarth, ImgResources.MONSTER, pattern);
		Monster monsterMedium = new Monster(new BoundingBox(200, 200, 50, 50), Type.FIRE, gunFire, ImgResources.MONSTER, pattern);
		Monster monsterHard = new Monster(new BoundingBox(200, 200, 50, 50), Type.WATER, melee, ImgResources.MONSTER, pattern);

		//add to rooms
		room1.addEntity(player);
		room1.addEntity(gunEarth);

		room2.addEntity(monsterEasy);
		room2.addEntity(live1);

		room3.addEntity(monsterEasy);
		room3.addEntity(monsterMedium);
		room3.addEntity(melee);
		room3.addEntity(gunFire);

		room4.addEntity(live2);
		room4.addEntity(ammo);
		room4.addEntity(monsterHard);

	}

	/**
	 * Return a specific room from this level
	 * @param i		the room number
	 * @return		the room			BoundingBox box = e.getBoundingBox();

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
	 * Called from goThroughDoor() in Room
	 * Changes current room and adds player to that room
	 * @param r		the room to move into
	 */
	protected void gotoRoom(Room r) {
		//TODO: Error checking
		int leavingRoom = currentRoom.getRoomNum();
		currentRoom = r;
		currentRoom.addEntity(player);
		switch (r.getRoomNum()){
			case 1: player.moveTo(player.getX(), player.getY() - (Renderer.ROOM_HEIGHT-Renderer.HUD_HEIGHT));
					break;
			case 2:	player.moveTo(player.getX(), player.getY() + (Renderer.ROOM_HEIGHT-Renderer.HUD_HEIGHT-player.getHeight()));
					break;
			case 3:	player.moveTo(player.getX() + 690, player.getY());
					break;
			case 4: player.moveTo(player.getX() - 690, player.getY());
					break;
			case 5:	if(leavingRoom == 1){ 
						player.moveTo(player.getX(), player.getY() + 350); 
					} else if(leavingRoom == 2){ 
						player.moveTo(player.getX(), player.getY() - 350); 
					} else if(leavingRoom == 3){ 
						player.moveTo(player.getX() - 690, player.getY()); 
					} else if(leavingRoom == 4){ 
						player.moveTo(player.getX() + 690, player.getY()); 
					}
					break;
			
		}
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
	
	/*
	 * Setter for current room, used in testing
	 */
	public void setCurrentRoom(Room room) {
		this.currentRoom = room;
	}

}
