package game;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.stage.Stage;

public class StartHandler<T extends Event> implements EventHandler<ActionEvent>{
	
	private Stage stage;
	
	public StartHandler(Stage stage) {
		this.stage = stage;
	}
	
	@Override
	public void handle(ActionEvent event) {
		Game game;
		try {
			game = new Game();
			game.start(stage);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
