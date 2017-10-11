package game;

import java.io.File;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import saveLoad.Save;
/**
 * Handles user interaction wiht the Save button
 * 
 * @author Tim Gastrell
 *
 * @param <T>
 */
public class SaveHandler<T extends Event> implements EventHandler<ActionEvent>{
	
	/**
	 * Game to be saved
	 */
	private Game game;
	
	/**
	 * Constructs a new SaveHandler
	 * @param Game instance to be saved
	 */
	public SaveHandler(Game game) {
		this.game = game;
	}
	
	/**
	 * Saves game to disk using the Save class
	 */
	@Override
	public void handle(ActionEvent arg0) {
		Save save = new Save(game);
		FileChooser fc = new FileChooser();
		File file = fc.showSaveDialog(new Stage());
		
		if(file == null) {
			return;
		}
		save.saveGame(file);
	}
}
