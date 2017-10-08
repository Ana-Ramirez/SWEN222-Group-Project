package game;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import view.MainMenu;

public class QuitToMenuHandler<T extends Event> implements EventHandler<ActionEvent> {

	@Override
	public void handle(ActionEvent event) {
		// TODO Auto-generated method stub
		MainMenu menu = new MainMenu();
		try {
			menu.stop();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
