package view;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.List;

import entities.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import logic.*;
import resources.ImgResources;

/**
 * Renders the room and entities inside the room
 * @author Patrick
 *
 */
public class Renderer extends Application{
	private GraphicsContext g;
	private List<Entity> entities;
	private Room room;
	private Stage primaryStage;
	private Scene scene;

	public Renderer(GraphicsContext g, List<Entity> entities, Room room, Game game){
		this.g = g;
		this.entities = entities;
		this.room = room;


		setEntityImages();
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		this.primaryStage = primaryStage;
		primaryStage.setTitle("Trouble in ChinaTown");

		StackPane layout = new StackPane();

		scene = new Scene(layout, 800, 600);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	/**
	 * Draws the entities in the room
	 */
	public void drawEntities(){
		for (Entity e : entities){
			g.drawImage(e.getImage(), e.getX(), e.getY());
		}
	}

	/**
	 * Draws each door that leads to another room
	 */
	public void drawRoom(){

		for (Door d : room.getDoors()){
			//g.drawImage(ImgResources.DOOR.img, , , , );
		}
	}

	/**
	 * Assigns images to the entities
	 */
	private void setEntityImages() {
		for (Entity e : this.entities) {
			e.setImage(ImgResources.e.getName());
		}
	}

	public Scene getScene() {
		return this.scene;
	}
}
