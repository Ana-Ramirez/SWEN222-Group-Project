package view;

import java.util.List;

import entities.*;

import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
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
	private Canvas canvas;

	public Renderer(){
		StackPane root = new StackPane();
		this.scene = new Scene(root, 800, 600);

		this.canvas = new Canvas(800,600);
		this.g = canvas.getGraphicsContext2D();

		setEntityImages();
	}

	/**
	 *
	 * @param entities
	 * @param room
	 */
	public void renderRoom(List<Entity> entities, Room room) {
		//TODO check for nulls
		this.entities = entities;
		this.room = room;
	}

	/**
	 * Draws the entities in the room
	 */
	public void drawEntities(){
		//TODO check for nulls
		for (Entity e : entities){
			g.drawImage(e.getImage(), (double)e.getX(), (double)e.getY());
		}
	}

	/**
	 * Draws each a room and its doors that lead to other rooms
	 */
	public void drawRoom(){
		//TODO draw images for the floor
		//check for nulls


		for (Door d : room.getDoors()){
			//TODO not all images implemented yet
			//check for nulls
			//g.drawImage(ImgResources.DOOR.img, , , , );
		}
	}

	/**
	 * Assigns images to the entities
	 */
	private void setEntityImages() {
		//TODO check for nulls
		for (Entity e : this.entities) {
			//TODO implement this
			//e.setImage(ImgResources.MAINMENUBG);
		}
	}

	public Scene getScene() {
		return this.scene;
	}
}
