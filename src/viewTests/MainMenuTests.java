package viewTests;

import org.junit.Test;

import view.MainMenu;

/**
 * Tests for the main menu class
 * @author Patrick
 *
 */
public class MainMenuTests {

	/**
	 * Checks if the toggleDisplay method functions without errors
	 */
	@Test
	public void toggleDisplayTest(){
		MainMenu mm = new MainMenu();
		mm.toggleDisplay();
	}
	
	/**
	 * Checks if a start button can be added without error
	 */
	@Test
	public void startButtonTest(){
		MainMenu mm = new MainMenu();
		mm.startGameButton();
	}
	
	/**
	 * Checks if a load button can be added without error
	 */
	@Test
	public void loadButtonTest(){
		MainMenu mm = new MainMenu();
		mm.loadGameButton();
	}
	
	/**
	 * Checks if a quit button can be added without error
	 */
	@Test
	public void quitButtonTest(){
		MainMenu mm = new MainMenu();
		mm.quitGameButton();
	}
}
