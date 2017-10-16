package viewTests;

import org.junit.Test;

import game.Game;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.stage.Stage;
import view.GameOverMenu;

/**
 * Tests for the game over menu class
 * @author Patrick
 * <b>TESTS ADAPTED FROM CODE FOUND AT https://stackoverflow.com/a/18980655</b>
 */
public class GameOverMenuTests {

	/**
	 * Attempts to launch the game over menu for 3 seconds
	 * @throws InterruptedException
	 */
	@Test
	public void launchGameOverMenuTest() throws InterruptedException {
		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				new JFXPanel(); // Initializes the JavaFx Platform
				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						try {
							new GameOverMenu(new Game()).start(new Stage()); //Runs the game over menu
						} 
						catch (Exception e) {
							e.printStackTrace();
						}

					}
				});
			}
		});
		thread.start();// Initialize the thread
		Thread.sleep(3000); // Game over menu stays up for 3 seconds
	}
}
