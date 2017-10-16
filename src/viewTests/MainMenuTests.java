package viewTests;

import org.junit.Test;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.stage.Stage;
import view.MainMenu;

/**
 * Tests for the main menu class
 * @author Patrick
 * <b>TESTS ADAPTED FROM CODE FOUND AT https://stackoverflow.com/a/18980655</b>
 */
public class MainMenuTests {

	/**
	 * Attempts to launch the main menu for 3 seconds
	 * @throws InterruptedException
	 */
	@Test
	public void launchMainMenuTest() throws InterruptedException {
		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				new JFXPanel(); // Initializes the JavaFx Platform
				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						try {
							new MainMenu().start(new Stage()); //Runs the main menu
						} 
						catch (Exception e) {
							e.printStackTrace();
						}

					}
				});
			}
		});
		thread.start();// Initialize the thread
		Thread.sleep(3000); // Main menu stays up for 3 seconds
	}
}
