package saveLoad;

import static org.junit.Assert.assertEquals;

import java.io.Serializable;

import org.junit.Test;

import game.Game;

public class SaveLoadUnitTests {
	
	/**
	 * Tests that a TestClass object can be saved and loaded without error
	 */
	@Test
	public void testSaveLoadTestClass() {
		String input = "Lewis sucks (:";
		TestClass testIn = new TestClass(input);
		Save save = new Save(testIn);
		save.saveGame();
		Load load = new Load("game_save");
		TestClass testOut = (TestClass)load.loadGame();
		String output = testOut.getStr();
		
		assertEquals(input, output);
	}		
}

class TestClass implements Serializable{

	private String str;
	
	public TestClass(String str){
		this.str = str;
	}
	
	public String getStr() {
		return str;
	}
}
