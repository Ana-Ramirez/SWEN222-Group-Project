package view;

import game.Game;
import game.LoadHandler;
import game.QuitHandler;
import game.StartHandler;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * Main menu shown to the player before they enter the game
 * @author Patrick
 *
 */
public class MainMenu extends Application{
	Scene scene;
	StackPane root;
	Stage primaryStage;

	/**
	 * Start method. Required by JavaFX applications.
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		this.primaryStage = primaryStage;
		primaryStage.setTitle("Trouble in ChinaTown");

		root = new StackPane();

		scene = new Scene(root, 800, 600);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public MainMenu(){
		
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

	public void loadGameButton(){
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
