package game;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import saveLoad.Save;

public abstract class PauseMenuController {
	
	private Game game;
	
	public PauseMenuController(Game game) {
		this.game = game;
	}
	
	public class ResumeHandler<T extends Event> implements EventHandler<ActionEvent>{
		
		@Override
		public void handle(ActionEvent arg0) {
			game.resume();
		}
		
	}
	
	public class SaveHandler<T extends Event> implements EventHandler<ActionEvent>{

		@Override
		public void handle(ActionEvent arg0) {
			Save save = new Save(game);
			save.saveGame();
		}
		
	}
	
	public class QuitToMenuHandler<T extends Event> implements EventHandler<ActionEvent>{

		@Override
		public void handle(ActionEvent arg0) {
			
		}
		
	}
	
}
