package interfaces;

import java.io.Serializable;
import java.util.List;

import interfaces.Player;

/**
 * Stores the data required to reset a game to the current state on load.
 * 
 * @author Tim Gastrell
 *
 */
public interface GameDataInterface extends Serializable {
	
	/**
	 * Returns list of levels containing game data
	 * @return List of levels
	 */
	public List<LevelInterface> getLevels();
	
	/**
	 * Returns the level the player is currently in
	 * @return The level the player is currently in
	 */
	public LevelInterface getCurrentLevel();
	
	/**
	 * Returns the player
	 * @return The player
	 */
	public Player getPlayer();
}
