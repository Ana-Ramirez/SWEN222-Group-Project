package view;

import game.Game;
import saveLoad.Save;
import javafx.*;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
		Button btn = new Button();
		btn.setText("Resume Game");
		btn.setOnAction(new ResumeHandler<ActionEvent>());
		root.getChildren().add(btn);
	}
	
	/**
	 * Saves the game
	 * @param game
	 */
	public void saveGameButton(Game game){
		Button btn = new Button();
		btn.setText("Save Game");
		btn.setOnAction(new SaveHandler<ActionEvent>());
		root.getChildren().add(btn);
	}
	
	/**
	 * Quits to the main menu
	 */
	public void quitToMenuButton(){
		Button btn = new Button();
		btn.setText("Start New Game");
		btn.setOnAction(new QuitToMenuHandler<ActionEvent>());
		root.getChildren().add(btn);
	}

}