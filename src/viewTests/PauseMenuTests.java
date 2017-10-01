package viewTests;

import org.junit.Test;

import view.PauseMenu;

/**
 * Tests for the PauseMenu class
 * @author Patrick
 *
 */
public class PauseMenuTests {

	/**
	 * Checks if the toggleDisplay method functions without errors
	 */
	@Test
	public void toggleDisplayTest(){
		PauseMenu pm = new PauseMenu();
		pm.toggleDisplay();
	}
	
	/**
	 * Checks if a resume button can be added without error
	 */
	@Test
	public void resumeButtonTest(){
		PauseMenu pm = new PauseMenu();
		pm.resumeGameButton(null);
	}
	
	/**
	 * Checks if a save button can be added without error
	 */
	@Test
	public void saveButtonTest(){
		PauseMenu pm = new PauseMenu();
		pm.saveGameButton(null);
	}
	
	/**
	 * Checks if a quit button can be added without error
	 */
	@Test
	public void quitButtonTest(){
		PauseMenu pm = new PauseMenu();
		pm.quitToMenuButton();
	}
}
