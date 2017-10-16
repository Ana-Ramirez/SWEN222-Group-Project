package viewTests;

import org.junit.Test;

import game.Game;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.stage.Stage;
import view.YouWinMenu;

/**
 * Tests for the you win menu class
 * @author Patrick
 * <b>TESTS ADAPTED FROM CODE FOUND AT https://stackoverflow.com/a/18980655</b>
 */
public class YouWinMenuTests {

	/**
	 * Attempts to launch the you win menu for 3 seconds
	 * @throws InterruptedException
	 */
	@Test
	public void launchYouWinMenuTest() throws InterruptedException {
		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				new JFXPanel(); // Initializes the JavaFx Platform
				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						try {
							new YouWinMenu(new Game()).start(new Stage()); //Runs the you win menu
						} 
						catch (Exception e) {
							e.printStackTrace();
						}

					}
				});
			}
		});
		thread.start();// Initialize the thread
		Thread.sleep(3000); // You win menu stays up for 3 seconds
	}
}
