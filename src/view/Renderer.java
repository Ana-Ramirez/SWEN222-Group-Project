package view;

import java.io.Serializable;

import interfaces.Entity;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import logic.Door;
import logic.Level;
import resources.ImgResources;


/**
 * Renders the room and entities inside the room
 * @author Patrick
 *
 */
public class Renderer implements Serializable{
	private GraphicsContext g;
	private Level level;
	private Scene scene;
	public static final int TILE_SIZE = 32;
	public static final int HUD_HEIGHT = 120;
	public static final int FLOOR_WIDTH = 25;
	public static final int FLOOR_HEIGHT = 15;
	public static final int ROOM_WIDTH = 800;
	public static final int ROOM_HEIGHT = 480;

	/**
	 * The constructor for the renderer. Takes a player
	 * @param p
	 */
	public Renderer(Level level){
		this.level = level;
		StackPane root = new StackPane();

		Canvas canvas = new Canvas(800,600);
		this.g = canvas.getGraphicsContext2D();
		root.getChildren().add(canvas);
		this.scene = new Scene(root, 800, 600);
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
		drawEntities();
	}

	/**
	 * Draws the floor of the room that all entities are drawn on top of
	 */
	private void drawFloor(){
		for (int r = 0; r < FLOOR_WIDTH; r++){
			for (int c = 0; c < FLOOR_HEIGHT; c++){
				g.drawImage(ImgResources.FLOOR.img, r*TILE_SIZE, HUD_HEIGHT + c*TILE_SIZE);
			}
		}
	}

	/**
	 * Draws the entities in the room
	 */
	private void drawEntities(){
		/**
		if (this.entities == null) {
			//TODO remove debug code
			System.out.println("List of entities is null!");
			return;
		}**/
		for (Entity e : level.getCurrentRoom().getEntities()){
			g.drawImage(e.getImage().img, e.getX(), HUD_HEIGHT + e.getY(), e.getWidth(), e.getHeight());
		}
	}

	/**
	 * Draws the HUD.
	 */
	private void drawHUD(){
		//Background for the HUD
		g.setFill(Color.BLACK);
		g.fillRect(0, 0, ROOM_WIDTH, HUD_HEIGHT);

		g.setFill(Color.WHITE);
		g.fillText("INVENTORY", 20, 20);
		drawInventory(20, 30);

		g.setFill(Color.WHITE);
		g.fillText("LIVES", 420, 20);
		drawLives(400, 30);
	}

	/**
	 * Draws the items in the inventory of the player at the specified x and y positions
	 * @param x
	 * @param y
	 */
	private void drawInventory(int x, int y){
		//Draws the outlines
		g.drawImage(ImgResources.INVENTORYBOX.img, x, y);
		g.drawImage(ImgResources.INVENTORYBOX.img, x+80, y);

		//Draws the items
		Entity[] inventory = level.getCurrentRoom().getPlayer().getInventory();
		if (inventory[0] != null){
			g.drawImage(inventory[0].getImage().img, x, y);
		}
		if (inventory[1] != null){
			g.drawImage(inventory[1].getImage().img, x+80, y);
		}
	}

	/**
	 * Draws the player's lives remaining at the specified x and y position.
	 * @param x
	 * @param y
	 */
	private void drawLives(int x, int y){
		int lives = level.getCurrentRoom().getPlayer().getLives();
		for (int i = 0; i < lives; i++){
			g.drawImage(ImgResources.LIFE.img, x + i*66, y);
		}
	}

	/**
	 * Repaints the information that could change in a frame
	 */
	public void repaint(){
		drawRoom();
		drawHUD();
	}

	public Scene getScene() {
		return this.scene;
	}
}
