package viewTests;

import org.junit.Test;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.stage.Stage;
import view.GameInstructions;

/**
 * Tests for the game instructions class
 * @author Patrick
 * <b>TESTS ADAPTED FROM CODE FOUND AT https://stackoverflow.com/a/18980655</b>
 */
public class GameInstructionsTests {

	/**
	 * Attempts to launch the game instructions for 3 seconds
	 * @throws InterruptedException
	 */
	@Test
	public void launchGameInstructionsTest() throws InterruptedException {
		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				new JFXPanel(); // Initializes the JavaFx Platform
				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						try {
							new GameInstructions().start(new Stage()); //Runs the game instructions
						} 
						catch (Exception e) {
							e.printStackTrace();
						}

					}
				});
			}
		});
		thread.start();// Initialize the thread
		Thread.sleep(3000); // Game instructions stay up for 3 seconds
	}
}
