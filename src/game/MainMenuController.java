package game;


import java.io.File;

import entities.Player;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import saveLoad.Load;
import view.Renderer;

public class MainMenuController {
		
	public MainMenuController() {
		
	}

	public class StartHandler implements EventHandler{

		@Override
		public void handle(Event event) {
			Game game = new Game(new Renderer(), new Player(0, 0, null)); //TODO sort out these parameters
			game.startGame();
		}
		
	}
	
	public class LoadHandler implements EventHandler{

		@Override
		public void handle(Event arg0) {
			FileChooser fc = new FileChooser();
			File file = fc.showOpenDialog(new Stage());//I hope this works?
			
			Load load = new Load(file.getName());
			Game game = load.loadGame();
			
			game.startGame();
		}
		
	}
	
	public class QuitHandler implements EventHandler{

		@Override
		public void handle(Event arg0) {
			//TODO display a save warning
			System.exit(0);
		}
		
	}
	
}
