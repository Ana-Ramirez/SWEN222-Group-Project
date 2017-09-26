package game;

import java.io.File;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import saveLoad.Load;

class LoadHandler<T extends Event> implements EventHandler<ActionEvent>{

	@Override
	public void handle(ActionEvent event) {
		FileChooser fc = new FileChooser();
		File file = fc.showOpenDialog(new Stage());//I hope this works?
		
		Load load = new Load(file.getName());
		Game game = load.loadGame();
		
		game.startGame();
	}
	
}
