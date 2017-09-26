package game;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;

public class ResumeHandler<T extends Event> implements EventHandler<ActionEvent>{
	
	private Game game;
	
	public ResumeHandler(Game game) {
		this.game = game;
	}
	@Override
	public void handle(ActionEvent arg0) {
		game.resume();
	}
	
}
