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
import entities.Type;
import interfaces.Entity;
import interfaces.MoveableEntity;
import interfaces.Character;
import logic.Level;
import logic.Room;
import resources.ImgResources;

public class SaveLoadUnitTestsIndependent {
	
	
	
	Rectangle2D.Double boundingBox = new Rectangle2D.Double(50, 50, 23, 23);
	
	/**
	 * Tests that the player position is saved
	 */
	@Test
	public void testGameSavingPlayerPosition() {
		TestCharacter player = new TestCharacter(50, 50, 3);
		List<Level> levels = generateLevels(player);
		Level currentLevel = levels.get(0);
		
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
		TestCharacter player = new TestCharacter(50, 50, 3);
		List<Level> levels = generateLevels(player);
		Level currentLevel = levels.get(0);
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
		TestCharacter player = new TestCharacter(50, 50, 3);
		List<Level> levels = generateLevels(player);
		Level currentLevel = levels.get(0);	
		player.hit(2);
		
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
		TestCharacter player = new TestCharacter(50, 50, 3);
		List<Level> levels = generateLevels(player);
		Level currentLevel = levels.get(0);
		
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
	private List<Level> generateLevels(Player player) {
		List<Level> levels = new ArrayList<Level>();
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
		TestCharacter player = new TestCharacter(50, 50, 3);
		List<Level> levels = generateLevels(player);
		Level currentLevel = levels.get(0);
		
		player.moveTo(20, 20);
		
		GameData gd = new GameData(player, levels, currentLevel);
		
		Save save = new Save(gd, new File("Player_move"));
		save.saveGame();
		
		//Second save over first
		TestCharacter newPlayer = new TestCharacter(50, 50, 3);
		List<Level> newLevels = generateLevels(player);
		Level newCurrentLevel = levels.get(0);
		
		newPlayer.moveTo(25, 25);
		
		GameData newGd = new GameData(newPlayer, newLevels, newCurrentLevel);
		
		Save newSave = new Save(gd, new File("Player_move"));
		newSave.saveGame();
		
		//load data 
		Load load = new Load("Player_move.txt");
		GameData loadedGame = load.loadGame();
		Player loadedPlayer = loadedGame.getPlayer();
			
		assertEquals((Double)newPlayer.getX(), (Double)loadedPlayer.getX());
		assertEquals((Double)newPlayer.getY(), (Double)loadedPlayer.getY());
	}	
	
	private class TestCharacter implements Character{
		
		private double x, y;
		private int lives;
		
		public TestCharacter(int x, int y, int lives) {
			this.x = x;
			this.y = y;
			this.lives = lives;
		}

		@Override
		public void moveBy(double x, double y) {}

		@Override
		public void moveTo(double x, double y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public void tick() {}

		@Override
		public Type getType() {return null;}

		@Override
		public double getX() {
			return x;
		}

		@Override
		public double getY() {
			return y;
		}

		@Override
		public double getWidth() {return 0;}

		@Override
		public double getHeight() {return 0;}

		@Override
		public void setImage(ImgResources image) {}

		@Override
		public ImgResources getImage() {return null;}

		@Override
		public java.awt.geom.Rectangle2D.Double getBoundingBox() {return null;}

		@Override
		public boolean hit(int damage) {
			if (lives > 0) {
				lives-=damage;
				return true;
			}
			return false;
		}

		@Override
		public boolean isAlive() {return false;}

		@Override
		public int getLives() {
			return lives;
		}
		
	}
	
}
