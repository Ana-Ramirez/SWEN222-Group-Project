package game;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import saveLoad.Save;

public class SaveHandler<T extends Event> implements EventHandler<ActionEvent>{
	
	private Game game;
	
	public SaveHandler(Game game) {
		this.game = game;
	}
	
	@Override
	public void handle(ActionEvent arg0) {
		Save<Game> save = new Save<Game>(game);
		save.saveGame();
	}
	
}
