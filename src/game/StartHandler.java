package game;

import java.awt.Graphics2D;

import entities.Player;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import view.Renderer;

public class StartHandler<T extends Event> implements EventHandler<ActionEvent>{

	@Override
	public void handle(ActionEvent event) {
		Game game = new Game(new Renderer(), new Player(0, 0)); //TODO sort out these parameters
		game.startGame();
	}

}
