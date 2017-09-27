package game;

import entities.Player;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import view.Renderer;

public class StartHandler<T extends Event> implements EventHandler<ActionEvent>{

	@Override
	public void handle(ActionEvent event) {
		Game game = new Game(new Renderer(), new Player(0, 0, null)); //TODO sort out these parameters
		game.startGame();
	}

}
