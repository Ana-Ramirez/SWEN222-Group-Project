package game;

import javafx.event.Event;
import javafx.event.EventHandler;
import saveLoad.Save;

public class PauseMenuController {
	
	private Game game;
	
	public PauseMenuController(Game game) {
		this.game = game;
	}
	
	public class ResumeHandler implements EventHandler{
		
		@Override
		public void handle(Event arg0) {
			game.resume();
		}
		
	}
	
	public class SaveHandler implements EventHandler{

		@Override
		public void handle(Event arg0) {
			Save save = new Save(game);
			save.saveGame();
		}
		
	}
	
	public class QuitToMenuHandler implements EventHandler{

		@Override
		public void handle(Event arg0) {
			
		}
		
	}
	
}
