package game;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.stage.Stage;

public class ResumeHandler<T extends Event> implements EventHandler<ActionEvent>{
	
	private Game game;
	private Stage stage;
	
	public ResumeHandler(Game game, Stage stage) {
		this.game = game;
		this.stage = stage;
	}
	@Override
	public void handle(ActionEvent arg0) {
		game.start(stage);
	}
	
}
