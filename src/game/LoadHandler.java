package game;

import java.io.File;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import saveLoad.Load;

public class LoadHandler<T extends Event> implements EventHandler<ActionEvent>{

	private Stage stage;
	
	public LoadHandler(Stage stage) {
		this.stage = stage;
	}
	
	@SuppressWarnings("unused")
	@Override
	public void handle(ActionEvent event) {
		FileChooser fc = new FileChooser();
		
		File file = fc.showOpenDialog(new Stage());//I hope this works?
		
		if(file == null) {
			return;
		}
		Load load = new Load(file.getAbsolutePath(), stage);
		load.loadGame();
	}
	
}
