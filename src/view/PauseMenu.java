package view;

import game.Game;
import saveLoad.Save;
import javafx.*;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;

/**
 * Menu shown while game is paused
 * @author Patrick
 *
 */
public class PauseMenu {
	Scene scene;
	StackPane root;
	
	public PauseMenu(){
		root = new StackPane();
		this.scene = new Scene(root, 300, 250);
	}
	
	/**
	 * Displays/hides the pause menu
	 */
	public void toggleDisplay(){
		if (root.isVisible()){
			root.setVisible(false);
		}
		else{
			root.setVisible(true);
		}
	}
	
	/**
	 * Resumes the game
	 */
	public void resumeGameButton(Game game){
		toggleDisplay();
		game.togglePause();
	}
	
	/**
	 * Saves the game
	 * @param game
	 */
	public void saveGameButton(Game game){
		Save save = new Save(game);
		save.saveGame();
	}
	
	/**
	 * Quits to the main menu
	 */
	public void quitToMenuButton(){
		game.exit();
		MainMenu mm = new MainMenu();
		mm.toggleDisplay();
	}

}
