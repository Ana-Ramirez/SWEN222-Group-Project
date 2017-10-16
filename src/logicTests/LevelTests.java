package logicTests;

import static org.junit.Assert.*;

import java.awt.geom.Rectangle2D;

import org.junit.Test;

import entities.PlayerEntity;
import logic.Level;
import logic.Room;

/**
 * This class tests the use of the Level class
 * @author lewismcewan
 *
 */
public class LevelTests {

	/**
	 * Correct amount of rooms in Level
	 */
	@Test
	public void initialise1() {
		PlayerEntity player = new PlayerEntity(new Rectangle2D.Double(10, 10, 10, 10), null);
		Level level = new Level(player);
		assertEquals(5, level.getRooms().size());
	}
	
	/**
	 * Get a room from the level
	 */
	@Test
	public void initialise2() {
		PlayerEntity player = new PlayerEntity(new Rectangle2D.Double(10, 10, 10, 10), null);
		Level level = new Level(player);
		Room room3 = level.getRoom(3);
		assertEquals(room3, level.getRoom(3));
	}

}
