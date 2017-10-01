package game;

import java.io.Serializable;
import java.util.List;

import entities.Player;
import javafx.application.Application;
import javafx.scene.input.KeyEvent;
import logic.Level;
import view.Renderer;

/**
 * Main controller class
 * 
 * @author Tim Gastrell
 *
 */
public class Game implements Serializable{
	
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
	
	/**
	 * Constructs a new Game object
	 * 
	 * @param mainMenu
	 * @param pauseMenu
	 * @param renderer
	 * @param player
	 */
	public Game() {
		generateLevels();
		
		if(levels.isEmpty()) throw new GameException("No level data found");
		this.renderer = new Renderer();
		
		this.player = new Player(0,0);
		
	}
	
	public void startGame() {
		//TODO start game loop
	}
	
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
	
	private void initialiseKeyListener() {
		renderer.getScene().onKeyPressed(new KeyPressHandler<KeyEvent>(player));
	}
}
