package game;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;

public class QuitHandler<T extends Event> implements EventHandler<ActionEvent>{

	@Override
	public void handle(ActionEvent arg0) {
		//TODO display a save warning
		System.exit(0);
	}
	
}
