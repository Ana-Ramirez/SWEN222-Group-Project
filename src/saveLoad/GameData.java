package saveLoad;

import java.io.Serializable;
import java.util.List;

import entities.Player;
import game.Game;
import logic.Level;

/**
 * Retrieves the Levels and Player objects from a Game object so that they may be saved to disk
 * 
 * @author Tim Gastrell
 */
public class GameData implements Serializable{
	
	/**
	 * List of Levels
	 */
	private List<Level> levels;
	
	/**
	 * Current level
	 */
	private Level currentLevel;
	
	/**
	 * Player Object
	 */
	private Player player;
	
	/**
	 * 
	 * @param Current game state
	 */
	public GameData(Game game) {
		levels = game.getLevels();
		player = game.getPlayer();
		currentLevel = game.getCurrentLevel();
	}
	
	/**
	 * 
	 * @return List of Levels
	 */
	public List<Level> getLevels() {
		return levels;
	}
	
	/**
	 * 
	 * @return Player object
	 */
	public Player getPlayer() {
		return player;
	}
	
	/**
	 * @return Current Level object
	 */
	public Level getCurrentLevel() {
		return currentLevel;
	}
	
}
