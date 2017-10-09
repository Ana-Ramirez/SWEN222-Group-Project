package view;

import java.util.ArrayList;
import java.util.List;

import entities.*;
import interfaces.Entity;

import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import resources.ImgResources;
import logic.*;


/**
 * Renders the room and entities inside the room
 * @author Patrick
 *
 */
public class Renderer{
	private GraphicsContext g;
	private List<Entity> entities;
	private Room room;
	private Scene scene;
	private Player player;
	
	private static final int TILE_SIZE = 32;
	private static final int HUD_HEIGHT = 80;
	private static final int ROOM_WIDTH = 25;
	private static final int ROOM_HEIGHT = 10;

	/**
	 * The constructor for the renderer. Takes a player
	 * @param p
	 */
	public Renderer(){
		StackPane root = new StackPane();

		Canvas canvas = new Canvas(800,600);
		this.g = canvas.getGraphicsContext2D();
		this.entities = new ArrayList<Entity>();
		
		root.getChildren().add(canvas);
		this.scene = new Scene(root, 800, 600);
	}
	
	/**
	 * Sets the player. Should be called before drawing anything.
	 * @param p
	 */
	public void setPlayer(Player p){
		this.player = p;
	}
	
	/**
	 * Set up the renderer for a new room
	 * @param room
	 */
	public void newRoom(Room room) {
		if (room == null) {
			//TODO remove debug code
			System.out.println("Null room!");
			return;
		}

		this.entities = room.getEntities();
		this.room = room;
		
		drawRoom();
	}
	
	/**
	 * Actually draws the room and HUD. Should only need to be called once at the start, 
	 * after the renderer has been initialised and it has been given its first room from 
	 * newRoom.
	 */
	public void initialDraw(){
		drawRoom();
		drawHUD();
	}
	
	/**
	 * Draws each a room, its doors and its entities
	 */
	private void drawRoom(){
		drawFloor();
		drawDoors();
		drawEntities();
	}
	
	/**
	 * Draws the floor of the room that all entities are drawn on top of
	 */
	private void drawFloor(){
		for (int r = 0; r <= ROOM_WIDTH; r++){
			for (int c = 0; c <= ROOM_HEIGHT; c++){
				g.drawImage(ImgResources.FLOOR.img, HUD_HEIGHT + r*TILE_SIZE, c*TILE_SIZE);
			}
		}
	}


	/**
	 * Draws each door in the scene
	 */
	private void drawDoors(){
		if (room.getDoors() == null){
			//TODO remove debug code
			System.out.println("room.getDoors() returned null!");
			return;
		}
		for (Door d : room.getDoors()){
			if (d.getDoorPosition() == 0){ //		NORTH
				g.drawImage(ImgResources.STAIRSTOP.img, ROOM_WIDTH/2 - TILE_SIZE/2, HUD_HEIGHT);
			}
			else if (d.getDoorPosition() == 1){ //	SOUTH
				g.drawImage(ImgResources.STAIRSBOT.img, ROOM_WIDTH/2 - TILE_SIZE/2, HUD_HEIGHT + ROOM_HEIGHT - TILE_SIZE);
			}
			else if(d.getDoorPosition() == 2){ //	EAST
				g.drawImage(ImgResources.STAIRSTOP.img, ROOM_WIDTH - TILE_SIZE, HUD_HEIGHT + ROOM_HEIGHT/2 - TILE_SIZE/2);
			}
			else { //								WEST
				g.drawImage(ImgResources.STAIRSTOP.img, 0, HUD_HEIGHT + ROOM_HEIGHT/2 - TILE_SIZE/2);
			}
		}
	}
	

	/**
	 * Draws the entities in the room
	 */
	private void drawEntities(){
		if (this.entities == null) {
			//TODO remove debug code
			System.out.println("List of entities is null!");
			return;
		}

		for (Entity e : entities){
			g.drawImage(e.getImage(), (double)e.getX(), (double)e.getY());
		}
	}
	
	/**
	 * Draws the HUD.
	 */
	private void drawHUD(){
		if (player == null){
			//TODO remove debug code
			System.out.println("Player is null!");
			return;
		}
		
		//Background for the HUD
		g.setFill(Color.BLACK);
		g.fillRect(0,0,ROOM_WIDTH,HUD_HEIGHT);

		g.setFill(Color.WHITE);
		g.fillText("INVENTORY", 20, 2);
		drawInventory(10, 16);
		
		g.setFill(Color.WHITE);
		g.fillText("LIVES", 400, 2);
		drawLives(400, 16);
	}
	
	/**
	 * Draws the items in the inventory of the player at the specified x and y positions
	 * @param x
	 * @param y
	 */
	private void drawInventory(int x, int y){
		if (player.getInventory() == null){
			//TODO remove debug code
			System.out.println("Player inventory is null!");
			return;
		}
		
		//Draws the outlines
		g.drawImage(ImgResources.INVENTORYBOX.img, x, y);
		g.drawImage(ImgResources.INVENTORYBOX.img, x+66, y);
		
		//Draws the items
		Entity[] inventory = player.getInventory();
		g.drawImage(inventory[0].getImage(), x, y);
		g.drawImage(inventory[0].getImage(), x+66, y);
	}
	
	/**
	 * Draws the player's lives remaining at the specified x and y position.
	 * @param x
	 * @param y
	 */
	private void drawLives(int x, int y){
		int lives = player.getLives();
		
		for (int i = 0; i <= lives; i++){
			g.drawImage(ImgResources.LIFE.img, x + i*66, y);
		}
	}
	
	/**
	 * Repaints the information that could change in a frame
	 */
	public void repaint(){
		drawEntities();
		drawHUD();
	}

	public Scene getScene() {
		return this.scene;
	}
}
