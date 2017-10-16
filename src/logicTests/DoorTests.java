package logicTests;

import static org.junit.Assert.*;

import java.awt.geom.Rectangle2D;

import org.junit.Test;

import entities.MeleeWeapon;
import entities.Player;
import interfaces.EntityType;
import logic.Door;
import logic.Level;
import logic.Room;

/**
 * This class tests the actions performed around the door,
 * hence based around Door.class
 * @author lewismcewan
 *
 */
public class DoorTests {
	
	/**
	 * Door creates with correct information
	 */
	@Test
	public void create() {
		Player player = new Player(new Rectangle2D.Double(10, 10, 10, 10), null);
		Level level = new Level(player);
		MeleeWeapon weapon = new MeleeWeapon(new Rectangle2D.Double(10, 10, 10, 10), EntityType.EARTH, 10, null);
		Room room1 = new Room(1, level);
		Room room2 = new Room(2, level);
		Door door = new Door(room1, room2, weapon, 1, 3);
		assertEquals(1, door.getDoorNum());
		assertEquals(3, door.getDoorPosition());
		assertEquals(weapon, door.getUnlockItem());
		assertEquals(room1, door.getRoom1());
		assertEquals(room2, door.getRoom2());
	}
	
	/**
	 * Change door position
	 */
	@Test
	public void setPosition() {
		Player player = new Player(new Rectangle2D.Double(10, 10, 10, 10), null);
		Level level = new Level(player);
		MeleeWeapon weapon = new MeleeWeapon(new Rectangle2D.Double(10, 10, 10, 10), EntityType.EARTH, 10, null);
		Room room1 = new Room(1, level);
		Room room2 = new Room(2, level);
		Door door = new Door(room1, room2, weapon, 1, 3);
		assertEquals(3, door.getDoorPosition());
		door.setDoorPosition(2);
		assertEquals(2, door.getDoorPosition());
	}
	
	/**
	 * unlock door
	 */
	@Test
	public void unlock() {
		Player player = new Player(new Rectangle2D.Double(10, 10, 10, 10), null);
		Level level = new Level(player);
		MeleeWeapon weapon = new MeleeWeapon(new Rectangle2D.Double(10, 10, 10, 10), EntityType.EARTH, 10, null);
		Room room1 = new Room(1, level);
		Room room2 = new Room(2, level);
		Door door = new Door(room1, room2, weapon, 1, 3);
		assertTrue(door.isLocked());
		door.unlockDoor(weapon);
		assertFalse(door.isLocked());
	}
	
	/**
	 * Testing the flipping of the door
	 */
	@Test
	public void flip() {
		Player player = new Player(new Rectangle2D.Double(10, 10, 10, 10), null);
		Level level = new Level(player);
		MeleeWeapon weapon = new MeleeWeapon(new Rectangle2D.Double(10, 10, 10, 10), EntityType.EARTH, 10, null);
		Room room1 = new Room(1, level);
		Room room2 = new Room(2, level);
		Door door = new Door(room1, room2, weapon, 0, 0);
		door.unlockDoor(weapon);
		door.flipDoor();
		assertEquals(1, door.getDoorPosition());
		door.flipDoor();
		assertEquals(0, door.getDoorPosition());
		door.setDoorPosition(2);
		door.flipDoor();
		assertEquals(3, door.getDoorPosition());
		door.flipDoor();
		assertEquals(2, door.getDoorPosition());
	}
	
	/**
	 * Test setting rooms works correctly
	 */
	@Test
	public void setRooms() {
		Player player = new Player(new Rectangle2D.Double(10, 10, 10, 10), null);
		Level level = new Level(player);
		MeleeWeapon weapon = new MeleeWeapon(new Rectangle2D.Double(10, 10, 10, 10), EntityType.EARTH, 10, null);
		Room room1 = new Room(1, level);
		Room room2 = new Room(2, level);
		Door door = new Door(null, null, weapon, 0, 0);
		door.setRoom1(room1);
		door.setRoom2(room2);
		assertEquals(room1, door.getRoom1());
		assertEquals(room2, door.getRoom2());
	}

}
