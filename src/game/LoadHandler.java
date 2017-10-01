package game;

import java.io.File;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import saveLoad.Load;

public class LoadHandler<T extends Event> implements EventHandler<ActionEvent>{

	@SuppressWarnings("unused")
	@Override
	public void handle(ActionEvent event) {
		FileChooser fc = new FileChooser();
		if(fc == null) {
			return;
		}
		File file = fc.showOpenDialog(new Stage());//I hope this works?
		
		Load load = new Load(file.getName());
		Game game = (Game) load.loadGame();
		
		try {
			game.start(new Stage());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
