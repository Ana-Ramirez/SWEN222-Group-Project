package game;


import java.io.File;

import entities.Player;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import saveLoad.Load;
import view.Renderer;

public class MainMenuController {
		
	public MainMenuController() {
		
	}

	
	
}
class StartHandler<T extends Event> implements EventHandler<ActionEvent>{

	@Override
	public void handle(ActionEvent event) {
		Game game = new Game(new Renderer(), new Player(0, 0)); //TODO sort out these parameters
		game.startGame();
	}
	
}

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

class QuitHandler<T extends Event> implements EventHandler<ActionEvent>{

	@Override
	public void handle(ActionEvent arg0) {
		//TODO display a save warning
		System.exit(0);
	}
	
}
