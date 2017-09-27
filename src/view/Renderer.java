package view;

import java.util.List;

import entities.*;

import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
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

	public Renderer(GraphicsContext g, List<Entity> entities, Room room, Scene scene){
		this.g = g;
		this.entities = entities;
		this.room = room;
		this.scene = scene;

		setEntityImages();
	}

	/**
	 * Draws the entities in the room
	 */
	public void drawEntities(){
		for (Entity e : entities){
			g.drawImage(e.getImage(), (double)e.getX(), (double)e.getY());
		}
	}

	/**
	 * Draws each a room and its doors that lead to other rooms
	 */
	public void drawRoom(){
		//TODO draw images for the floor
		
		
		for (Door d : room.getDoors()){
			//TODO not all images implemented yet
			//g.drawImage(ImgResources.DOOR.img, , , , );
		}
	}

	/**
	 * Assigns images to the entities
	 */
	private void setEntityImages() {
		for (Entity e : this.entities) {
			//e.setImage(ImgResources.MAINMENUBG);
		}
	}

	public Scene getScene() {
		return this.scene;
	}
}
