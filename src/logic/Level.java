package logic;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import entities.Monster;
import entities.Player;
import view.Renderer;

/**
 * This class is the Model of our MVC patterned game
 * Level acts as a container for all the classes handling
 * making up the layout of the game. It ties them all together
 * @author lewismcewan
 *
 */
public class Level implements Serializable{

	private List<Room> rooms;
	private Player player;
	private Room currentRoom;
	private boolean gotPatrick = false;
	private Monster boss;
	private boolean isLeft;

	/**
	 * Create the new level, passing in a new player
	 * @param player
	 * @throws Exception
	 */
	public Level(Player player){
		this.player = player;
		this.rooms = new ArrayList<Room>();
		LevelInitialiser.initialise(this, rooms, player);
		currentRoom = rooms.get(0);

		isLeft = false;
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
	
	/**
	 * Setter for current room, used in testing
	 */
	public void setCurrentRoom(Room room) {
		this.currentRoom = room;
	}
	
	
	public Monster getBoss() {
		return boss;
	}
	
	
	public void setBoss(Monster boss) {
		this.boss = boss;
	}
	
	/**
	 * Sets the isLeft field to the boolean b
	 * @param b
	 */
	public void setLeft(boolean b){
		isLeft = b;
	}
	
	/**
	 * @return if the player is facing left
	 */
	public boolean isLeft(){
		return isLeft;
	}

}
