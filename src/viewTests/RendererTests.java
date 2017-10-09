package viewTests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import entities.Entity;
import entities.Player;
import game.Game;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import logic.Room;
import resources.ImgResources;
import view.Renderer;

/**
 * Tests for the renderer class
 * @author Patrick
 *
 */
public class RendererTests {

	/**
	 * Makes sure the constructor can be called without error
	 */
	@Test
	public void constructorTest(){
		Player p = new Player(0.0f, 0.0f, 10, 10, ImgResources.PLAYERDOWN.img);
		TestApplication.launch();
		Renderer r = new Renderer(p);
	}
	
	/**
	 * Calls the drawEntities method to make sure it works
	 */
	@Test
	public void drawEntitiesTest(){
		GraphicsContext g = null;
		List<Entity> entities = new ArrayList<>();
		Renderer r = new Renderer();
		r.renderRoom(entities, null);
			
		r.drawEntities();
	}
	
	/**
	 * Calls the drawRoom method to make sure it works
	 */
	@Test
	public void drawRoomTest(){
		GraphicsContext g = null;
		List<Entity> entities = new ArrayList<>();
		Room room = new Room(0);
		Scene scene= new Scene(new StackPane());
		Renderer r = new Renderer();
			
		r.drawRoom();
	}
}
