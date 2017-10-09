package game;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import view.MainMenu;

public class QuitToMenuHandler<T extends Event> implements EventHandler<ActionEvent> {
	
	private Stage primaryStage;
	
	private Game game;
	
	public QuitToMenuHandler(Stage primaryStage, Game game) {
		this.primaryStage = primaryStage;
		this.game = game;
	}
	
	@Override
	public void handle(ActionEvent event) {
		MainMenu menu = new MainMenu();
		try {
			game.stop();
			menu.start(primaryStage);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
