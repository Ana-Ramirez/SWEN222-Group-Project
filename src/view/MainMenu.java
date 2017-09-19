package view;

import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import saveLoad.Load;

/**
 * Main menu shown to the player before they enter the game
 * @author Patrick
 *
 */
public class MainMenu {
	Scene scene;
	StackPane root;
	
	public MainMenu(){
		root = new StackPane();
		this.scene = new Scene(root, 300, 250);
	}
	
	/**
	 * Displays/hides the main menu
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
	 * Starts a new game
	 */
	public void startGameButton(){
		game.start();
	}
	
	/**
	 * Loads a game from file
	 */
	public Game loadGameButton(String fileName){
		Load load = new Load(fileName);
		return load.loadGame();
	}
	
	/**
	 * Exits the application
	 */
	public void quitGameButton(){

	}
}
