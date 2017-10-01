package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import entities.Player;
import logic.Level;
import logic.Room;

public class LevelTests {

	/**
	 * Correct amount of rooms in Level
	 */
	@Test
	public void initialise1() {
		Player player = new Player(100, 100);
		Level level = new Level(player);
		assertEquals(5, level.getRooms().size());
	}
	
	/**
	 * Get a room from the level
	 */
	@Test
	public void initialise2() {
		Player player = new Player(100, 100);
		Level level = new Level(player);
		Room room3 = level.getRoom(3);
		assertEquals(room3, level.getRoom(3));
	}
	
	/**
	 * Test game not over on new level creation
	 */
	@Test
	public void initialise3() {
		Player player = new Player(100, 100);
		Level level = new Level(player);
		assertFalse(level.gameOver());
	}
	
	

}
