package view;

import game.Game;
import saveLoad.Save;
import javafx.*;

/**
 * Menu shown while game is paused
 * @author Patrick
 *
 */
public class PauseMenu {
	
	public PauseMenu(){
		
	}
	
	public void resumeGameButton(){
		
	}
	
	/**
	 * 
	 * @param game
	 */
	public void saveGameButton(Game game){
		Save save = new Save(game);
		save.saveGame();
	}
	
	public void quitToMenuButton(){
		
	}

}
