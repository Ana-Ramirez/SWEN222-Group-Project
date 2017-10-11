package saveLoad;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.Serializable;

import org.junit.Test;

import entities.Player;
import game.Game;
import resources.ImgResources;

public class SaveLoadUnitTests {
	
	/**
	 * Tests that a TestClass object can be saved and loaded without error
	 */
	@Test
	public void testSaveLoadTestClass() {
		String input = "Lewis sucks (:";
		TestClass testIn = new TestClass(input);
		Save save = new Save(testIn);
		save.saveGame(new File("game_save"));
		Load load = new Load("game_save");
		TestClass testOut = (TestClass)load.loadGame();
		String output = testOut.getStr();
		
		assertEquals(input, output);

	}
	
	/**
	 * 
	 */
	@Test
	public void testGameSavingPlayerPosition() {
		ImgResources.values();
		Game game = new Game();
		Player player = game.getPlayer();
		player.moveTo(20, 20);
		
		Save save = new Save(game);
		save.saveGame(new File("Player_move"));
		
		Load load = new Load("Player_move");
		Game loadedGame = (Game) load.loadGame();
		Player newPlayer = loadedGame.getPlayer();
		
		
		assertEquals((Double)player.getX(), (Double)newPlayer.getX());
		assertEquals((Double)player.getY(), (Double)newPlayer.getY());
	}
	
	//======================  external tests  ======================
	/**
	 * Canceling load
	 */
	@Test (expected = NullPointerException.class)
	public void externalTest1(){
		Load load = new Load(null);
		TestClass testout = (TestClass)load.loadGame();
	}
	
	/**
	 * Test old game overwritten when saved over
	 */
	@Test
	public void externalTest2(){
		String oldInput = "Tim sucks";
		TestClass testIn = new TestClass(oldInput);
		Save save = new Save(testIn);
		save.saveGame(new File("file"));
		
		String newInput = "Lewis is kewl";
		TestClass testInNew = new TestClass(newInput);
		Save saveNew = new Save(testInNew);
		save.saveGame(new File("file"));
		
		Load load = new Load("file");
		TestClass testOut = (TestClass)load.loadGame();
		String output = testOut.getStr();
		
		assertEquals(newInput, output);
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
