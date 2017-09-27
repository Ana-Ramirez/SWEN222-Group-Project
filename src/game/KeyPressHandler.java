package game;

import entities.Player;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

public class KeyPressHandler<T extends Event> implements EventHandler<KeyEvent> {
	
	private Player player;
	
	public KeyPressHandler(Player player) {
		this.player = player;
	}
	
	@Override
	public void handle(KeyEvent event) {
		switch(event.getCode()) {
		case W :

			break;
		case A :

			break;
		case S :

			break;
		case D :

			break;
		default:
			break;
		}
	}

}
