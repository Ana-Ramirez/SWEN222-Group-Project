package interfaces;

/**
 * Loads a new game data object from a local file
 * @author Tim Gastrell
 *
 */
public interface LoadInterface {
	
	/**
	 * Loads a Game data object from disk
	 * @return game data object
	 */
	public GameDataInterface loadGame();
	
}
