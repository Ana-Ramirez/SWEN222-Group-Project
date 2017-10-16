package view;

import java.io.Serializable;

import entities.MeleeWeapon;
import entities.Monster;
import entities.Pickupable;
import entities.Player;
import interfaces.Entity;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import logic.Level;
import resources.ImgResources;


/**
 * Renders the room and entities inside the room
 * @author Patrick
 *
 */
public class Renderer implements Serializable, interfaces.Renderer{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8988365628295662802L;
	private GraphicsContext g;
	private Level level;
	private Scene scene;
	public static final int TILE_SIZE = 32;
	public static final int HUD_HEIGHT = 120;
	public static final int FLOOR_WIDTH = 25;
	public static final int FLOOR_HEIGHT = 15;
	public static final int ROOM_WIDTH = 800;
	public static final int ROOM_HEIGHT = 480;
	
	private int animationFrame;

	/**
	 * The constructor for the renderer. Takes a level object
	 * @param level
	 */
	public Renderer(Level level){
		this.level = level;
		StackPane root = new StackPane();

		Canvas canvas = new Canvas(800,600);
		this.g = canvas.getGraphicsContext2D();
		root.getChildren().add(canvas);
		this.scene = new Scene(root, 800, 600);
		
		animationFrame = 0;
	}

	@Override
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
		
		g.setStroke(Color.YELLOW);
		drawItemInfo(200, 65);
	}
	
	/**
	 * Draws the information for the item the player is currently holding
	 * @param x
	 * @param y
	 */
	private void drawItemInfo(int x, int y) {
		Pickupable e = level.getPlayer().getHand();
		if(e != null) {
			g.strokeText(e.getInfo().toUpperCase(), x, y);
		}
	}

	/**
	 * Draws the items in the inventory of the player at the specified x and y positions
	 * @param x
	 * @param y
	 */
	private void drawInventory(int x, int y){
		//Draws the background for the inventory
		g.setFill(Color.WHITE);
		g.fillRect(x, y, 64, 64);
		g.fillRect(x+80, y, 64, 64);
		
		Player player = level.getPlayer();

		//Draws the items
		Entity[] inventory = player.getBackpack();
		if (inventory[0] != null){
			g.drawImage(inventory[0].getImage().img, x + 4, y + 4, 56, 56);
		}
		if (inventory[1] != null){
			g.drawImage(inventory[1].getImage().img, x + 84, y + 4, 56, 56);
		}
		
		//Draw item in the player's hand
		Pickupable e = player.getHand();
		if (e != null) {
			ImgResources image = e.getImage();
			if (!level.isLeft()){ //Player is facing right
				if (e instanceof MeleeWeapon){
					animateSword(false);
					g.drawImage(image.img, player.getX() + player.getWidth() + 40, player.getY() + HUD_HEIGHT - 12, -48, 48);
				} else {
					g.drawImage(image.img, player.getX() + player.getWidth() + 17, player.getY() + player.getHeight() / 4d + HUD_HEIGHT, -24, 24);
				}
			}
			else { //player is facing left
				if (e instanceof MeleeWeapon){
					animateSword(false);
					g.drawImage(image.img, player.getX() - 40, player.getY() + HUD_HEIGHT - 12, 48, 48);
				} else { 
					g.drawImage(image.img, player.getX() - 17, player.getY() + player.getHeight() / 4d + HUD_HEIGHT, 24, 24);
				}
			}
		}
	}
	
	@Override
	public void animateSword(boolean fromGame){
		Player player = level.getPlayer();
		Pickupable e = player.getHand();
		if (e instanceof MeleeWeapon){
			if (!fromGame && animationFrame == 0){
				e.setImage(ImgResources.SWORDLEFTUP);
			}
			else if (fromGame && animationFrame == 0){
				animationFrame++;
			}
			if (animationFrame == 1){
				e.setImage(ImgResources.SWORDLEFTDIAG);
				animationFrame++;
			}
			else if (animationFrame == 2){
				e.setImage(ImgResources.SWORDLEFTDOWN);
				animationFrame = 0;
			}
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

	@Override
	public void repaint(){
		drawRoom();
		drawHUD();
	}

	@Override
	public Scene getScene() {
		return this.scene;
	}
}
