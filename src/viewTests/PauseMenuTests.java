package viewTests;

import org.junit.Test;

import game.Game;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.stage.Stage;
import view.PauseMenu;

/**
 * Tests for the PauseMenu class
 * @author Patrick
 * <b>TESTS ADAPTED FROM CODE FOUND AT https://stackoverflow.com/a/18980655</b>
 */
public class PauseMenuTests {

	/**
	 * Attempts to launch the pause menu for 3 seconds
	 * @throws InterruptedException
	 */
	@Test
	public void launchPauseMenuTest() throws InterruptedException {
		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				new JFXPanel(); // Initializes the JavaFx Platform
				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						try {
							new PauseMenu(new Game()).start(new Stage()); //Runs the pause menu
						} 
						catch (Exception e) {
							e.printStackTrace();
						}

					}
				});
			}
		});
		thread.start();// Initialize the thread
		Thread.sleep(3000); // Pause menu stays up for 3 seconds
	}

}
