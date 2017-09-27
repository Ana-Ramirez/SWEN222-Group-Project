package view;

import game.Game;
import game.StartHandler;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import saveLoad.Load;

/**
 * Main menu shown to the player before they enter the game
 * @author Patrick
 *
 */
public class MainMenu extends Application{
	Scene scene;
	StackPane root;

	public void start(Stage primaryStage) throws Exception{

	}

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

		Button btn = new Button();
		btn.setText("Start New Game");
		btn.setOnAction(new StartHandler<ActionEvent>());
		root.getChildren().add(btn);
	}

	/**
	 * Loads a game from file
	 */

	public Game loadGameButton(String fileName){
		Button btn = new Button();
		btn.setText("Load Game");
		btn.setOnAction(new LoadHandler<ActionEvent>());
		root.getChildren().add(btn);

	}

	/**
	 * Exits the application
	 */
	public void quitGameButton(){
		Button btn = new Button();
		btn.setText("Quit");
		btn.setOnAction(new QuitHandler<ActionEvent>());
		root.getChildren().add(btn);
	}
}
