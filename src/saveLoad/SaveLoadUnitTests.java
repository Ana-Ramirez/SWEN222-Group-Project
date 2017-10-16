package saveLoad;

import static org.junit.Assert.assertEquals;

import java.awt.geom.Rectangle2D;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import entities.MeleeWeapon;
import entities.Monster;
import entities.Player;
import interfaces.Entity;
import interfaces.EntityType;
import interfaces.LevelInterface;
import logic.Level;
import logic.Room;

public class SaveLoadUnitTests {
	
	
	
	Rectangle2D.Double boundingBox = new Rectangle2D.Double(50, 50, 23, 23);
	
	/**
	 * Tests that the player position is saved
	 */
	@Test
	public void testGameSavingPlayerPosition() {
		Player player = new Player(boundingBox, null);
		List<LevelInterface> levels = generateLevels(player);
		Level currentLevel = (Level) levels.get(0);
		
		player.moveTo(20, 20);
		
		GameData gd = new GameData(player, levels, currentLevel);
		
		Save save = new Save(gd, new File("Player_move"));
		save.saveGame();
		
		Load load = new Load("Player_move.txt");
		GameData loadedGame = load.loadGame();
		Player newPlayer = loadedGame.getPlayer();
			
		assertEquals((Double)player.getX(), (Double)newPlayer.getX());
		assertEquals((Double)player.getY(), (Double)newPlayer.getY());
	}
	
	/**
	 * Tests that the currentRoom is saved
	 */
	@Test
	public void testGameSavingRoom() {
		Player player = new Player(boundingBox, null);
		List<LevelInterface> levels = generateLevels(player);
		Level currentLevel = (Level) levels.get(0);
		currentLevel.setCurrentRoom(currentLevel.getRoom(3));
		
		GameData gd = new GameData(player, levels, currentLevel);
		
		Save save = new Save(gd, new File("Room_change"));
		save.saveGame();
		
		Load load = new Load("Room_change.txt");
		GameData loadedGame = load.loadGame();
		Level loadedCurrentLevel = loadedGame.getCurrentLevel();
		Room currentRoom = loadedCurrentLevel.getCurrentRoom();
			
		assertEquals(currentLevel.getCurrentRoom().getRoomNum(), currentRoom.getRoomNum());
	}
	
	/**
	 * Tests that the player lives are saved
	 */
	@Test
	public void testGameSavingLives() {
		Player player = new Player(boundingBox, null);
		List<LevelInterface> levels = generateLevels(player);
		Level currentLevel = (Level) levels.get(0);	
		MeleeWeapon melee = new MeleeWeapon(boundingBox, EntityType.WATER, 40, null);
		Monster monster = new Monster(boundingBox, 100, EntityType.WATER, melee, null, null);
		monster.attack(player, 120);
		
		GameData gd = new GameData(player, levels, currentLevel);
		
		Save save = new Save(gd, new File("Lives"));
		save.saveGame();
		
		Load load = new Load("Lives.txt");
		GameData loadedGame = load.loadGame();
		Player loadedPlayer = loadedGame.getPlayer();
			
		assertEquals(player.getLives(),loadedPlayer.getLives());
	}
	
	/**
	 * Tests that the save remembers the number of entites in a room
	 */
	@Test
	public void testGameSavingRoomEntities() {
		Player player = new Player(boundingBox, null);
		List<LevelInterface> levels = generateLevels(player);
		Level currentLevel = (Level) levels.get(0);
		
		List<Entity> entities = currentLevel.getCurrentRoom().getEntities();
		entities.remove(0);
		
		GameData gd = new GameData(player, levels, currentLevel);
		
		Save save = new Save(gd, new File("entities_save"));
		save.saveGame();
		
		Load load = new Load("entities_save.txt");
		GameData loadedGame = load.loadGame();
		List<Entity> newEntities = loadedGame.getCurrentLevel().getCurrentRoom().getEntities();
			
		assertEquals(entities.size(), newEntities.size());
	}
		
	
	/**
	 * Initialises list of levels
	 */
	private List<LevelInterface> generateLevels(Player player) {
		List<LevelInterface> levels = new ArrayList<LevelInterface>();
		Level level1 = null;
		level1 = new Level(player);
		levels.add(level1);
		
		return levels;

	}
	
	//======================  external tests  ======================
	/**
	 * Canceling load
	 */
	@Test (expected = NullPointerException.class)
	public void checkNullSave(){
		Load load = new Load(null);
		load.loadGame();
	}
	
	/**
	 * Test old game overwritten when saved over
	 */
	@Test
	public void testGameOverwrite() {
		//1st save
		Player player = new Player(boundingBox, null);
		List<LevelInterface> levels = generateLevels(player);
		Level currentLevel = (Level) levels.get(0);
		
		player.moveTo(20, 20);
		
		GameData gd = new GameData(player, levels, currentLevel);
		
		Save save = new Save(gd, new File("Player_move"));
		save.saveGame();
		
		//Second save over first
		Player newPlayer = new Player(boundingBox, null);
		List<LevelInterface> newLevels = generateLevels(player);
		Level newCurrentLevel = (Level) levels.get(0);
		
		newPlayer.moveTo(25, 25);
		
		GameData newGd = new GameData(newPlayer, newLevels, newCurrentLevel);
		
		Save newSave = new Save(newGd, new File("Player_move"));
		newSave.saveGame();
		
		//load data 
		Load load = new Load("Player_move.txt");
		GameData loadedGame = load.loadGame();
		Player loadedPlayer = loadedGame.getPlayer();
			
		assertEquals((Double)newPlayer.getX(), (Double)loadedPlayer.getX());
		assertEquals((Double)newPlayer.getY(), (Double)loadedPlayer.getY());
	}	
	
}
