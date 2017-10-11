package saveLoad;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import entities.Player;
import logic.Level;
import logic.Room;

public class SaveLoadUnitTests {
	/**
	 * Tests that the player position is saved
	 */
	@Test
	public void testGameSavingPlayerPosition() {
		Player player = new Player(50, 50, 23, 23, null);
		List<Level> levels = generateLevels(player);
		Level currentLevel = levels.get(0);
		
		player.moveTo(20, 20);
		
		GameData gd = new GameData(player, levels, currentLevel);
		
		Save save = new Save(gd);
		save.saveGame(new File("Player_move"));
		
		Load load = new Load("Player_move.txt");
		GameData loadedGame = load.loadGame();
		Player newPlayer = loadedGame.getPlayer();
			
		assertEquals((Double)player.getX(), (Double)newPlayer.getX());
		assertEquals((Double)player.getY(), (Double)newPlayer.getY());
	}
	
	/**
	 * Tests that the player position is saved
	 */
	@Test
	public void testGameSavingRoom() {
		Player player = new Player(50, 50, 23, 23, null);
		List<Level> levels = generateLevels(player);
		Level currentLevel = levels.get(0);
		currentLevel.setCurrentRoom(currentLevel.getRoom(3));
		
		GameData gd = new GameData(player, levels, currentLevel);
		
		Save save = new Save(gd);
		save.saveGame(new File("Room_change"));
		
		Load load = new Load("Room_change.txt");
		GameData loadedGame = load.loadGame();
		Level loadedCurrentLevel = loadedGame.getCurrentLevel();
		Room currentRoom = loadedCurrentLevel.getCurrentRoom();
			
		assertEquals(currentLevel.getCurrentRoom().getRoomNum(), currentRoom.getRoomNum());
	}
	
	
	
	/**
	 * Initialises list of levels
	 */
	private List<Level> generateLevels(Player player) {
		List<Level> levels = new ArrayList<Level>();
		Level level1 = null;
		level1 = new Level(player);
		levels.add(level1);
		
		return levels;

	}
	
//	//======================  external tests  ======================
//	/**
//	 * Canceling load
//	 */
//	@Test (expected = NullPointerException.class)
//	public void externalTest1(){
//		Load load = new Load(null);
//		TestClass testout = (TestClass)load.loadGame();
//	}
	
//	/**
//	 * Test old game overwritten when saved over
//	 */
//	@Test
//	public void externalTest2(){
//		String oldInput = "Tim sucks";
//		TestClass testIn = new TestClass(oldInput);
//		Save save = new Save(testIn);
//		save.saveGame(new File("file"));
//		
//		String newInput = "Lewis is kewl";
//		TestClass testInNew = new TestClass(newInput);
//		Save saveNew = new Save(testInNew);
//		save.saveGame(new File("file"));
//		
//		Load load = new Load("file");
//		TestClass testOut = (TestClass)load.loadGame();
//		String output = testOut.getStr();
//		
//		assertEquals(newInput, output);
//	}
//	
	

}

//class TestClass implements Serializable{
//
//	private String str;
//	
//	public TestClass(String str){
//		this.str = str;
//	}
//	
//	public String getStr() {
//		return str;
//	}
//}
