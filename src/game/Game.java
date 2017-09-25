package game;

import java.io.Serializable;
import java.util.List;

import entities.Player;
import logic.Level;
import view.MainMenu;
import view.PauseMenu;
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
	 * Event handling classes for menu buttons
	 */
//	private PauseMenuController pm_Controller = new PauseMenuController();	
//	private MainMenuController sm_Controller = new MainMenuController();
	
	/**
	 * Constructs a new Game object
	 * 
	 * @param mainMenu
	 * @param pauseMenu
	 * @param renderer
	 * @param player
	 */
	public Game(Renderer renderer, Player player) {
		this.renderer = renderer;
		
		this.player = player;
		
		generateLevels();
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
}
