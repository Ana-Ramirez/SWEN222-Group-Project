package game;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import view.GameInstructions;

/**
 * 
 * @author Tim Gastrell
 *
 * @param <T>
 */
public class GameInstructionHandler<T extends Event> implements EventHandler<ActionEvent>{

	private Stage primaryStage;
	
	public GameInstructionHandler(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}
	
	@Override
	public void handle(ActionEvent arg0) {
		GameInstructions gi = new GameInstructions();
		try {
			gi.start(primaryStage);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
