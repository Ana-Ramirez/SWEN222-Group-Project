package game;

import java.io.Serializable;
import java.util.List;

import entities.Player;
import javafx.application.Application;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import logic.Level;
import view.Renderer;

/**
 * Main controller class
 * 
 * @author Tim Gastrell
 *
 */
public class Game extends Application implements Serializable{
	
	/**
	 * View objects
	 */
	private Renderer renderer;
	
	/**
	 * Player object
	 */
	private Player player;
	
	/**
	 * All the levels in the game
	 */
	private List<Level> levels;
	
	
	public void pause() {
		
	}
	
	public void resume() {
		
	}
	
	/**
	 * Initialised list of levels
	 */
	private void generateLevels() {
		//TODO Initialise levels
	}
	
	private void initialiseKeyListener() throws GameException {
		if(renderer == null) throw new GameException("Renderer null Error");
		renderer.getScene().setOnKeyPressed(new KeyPressHandler<KeyEvent>(player));
	}

	@Override
	public void start(Stage stage) throws Exception {
		generateLevels();
		if(levels.isEmpty()) throw new GameException("No level data found");
		this.renderer = new Renderer();
		
		this.player = new Player(0,0);
		
		initialiseKeyListener();		
	}	
}
