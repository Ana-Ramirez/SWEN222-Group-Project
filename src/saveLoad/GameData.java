package saveLoad;

import java.io.Serializable;
import java.util.List;

import interfaces.Player;
import game.Game;
import interfaces.GameDataInterface;
import interfaces.LevelInterface;
import logic.Level;

/**
 * Retrieves the Levels and Player objects from a Game object so that they may be saved to disk
 * 
 * @author Tim Gastrell
 */
public class GameData implements GameDataInterface{
	
	/**
	 * Serial ID
	 */
	private static final long serialVersionUID = 8731379856738382788L;

	/**
	 * List of Levels
	 */
	private List<LevelInterface> levels;
	
	/**
	 * Current level
	 */
	private Level currentLevel;
	
	/**
	 * Player Object
	 */
	private Player player;
	
	/**
	 * Main constructor, always use this unless testing
	 * 
	 * @param Current game state
	 */
	public GameData(Game game) {
		this.levels = game.getLevels();
		this.player = game.getPlayer();
		this.currentLevel = game.getCurrentLevel();
	}
	
	/**
	 * Constructor for TESTING ONLY 
	 * 
	 * @param player object
	 * @param levels list
	 * @param currentLevel level
	 */
	public GameData(Player player, List<LevelInterface> levels, Level currentLevel) {
		this.levels = levels;
		this.player = player;
		this.currentLevel = currentLevel;
	}
	
	/**
	 * Returns list of levels containing game data
	 * @return List of Levels
	 */
	public List<LevelInterface> getLevels() {
		return levels;
	}
	
	/**
	 * Returns the player
	 * @return Player object
	 */
	public Player getPlayer() {
		return player;
	}
	
	/**
	 * Returns the level the player is currently in
	 * @return Current Level object
	 */
	public Level getCurrentLevel() {
		return currentLevel;
	}
	
}
