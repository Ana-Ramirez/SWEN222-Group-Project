package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import entities.Consumable;
import entities.MeleeWeapon;
import entities.Pickupable;
import logic.Door;
import logic.Room;

public class DoorTests {
	
	/**
	 * Verify door number is what it should be
	 */
	@Test
	public void create1() {
		MeleeWeapon weapon = new MeleeWeapon("weapon", 1, 1, entities.Type.FIRE, 10);
		Door door = new Door(new Room(1), new Room(2), weapon, 1, 1);
		assertEquals(1, door.getDoorNum());
	}
	
	/**
	 * Verify wall position is what it should be
	 */
	@Test
	public void create2() {
		MeleeWeapon weapon = new MeleeWeapon("weapon", 1, 1, entities.Type.FIRE, 10);
		Door door = new Door(new Room(1), new Room(2), weapon, 1, 1);
		assertEquals(1, door.getDoorPosition());
	}
	
	/**
	 * Verify unlock item is what it should be
	 */
	@Test
	public void create3() {
		MeleeWeapon weapon = new MeleeWeapon("weapon", 1, 1, entities.Type.FIRE, 10);
		Door door = new Door(new Room(1), new Room(2), weapon, 1, 1);
		assertEquals(weapon, door.getUnlockItem());
	}
	
	/**
	 * Verify unlock is what it should be
	 */
	@Test
	public void create4() {
		Consumable consumable = new Consumable("consumable", 20, 100);
		Door door = new Door(new Room(1), new Room(2), consumable, 1, 1);
		assertEquals(consumable, door.getUnlockItem());
	}
	
	/**
	 * Verify a room is what it should be
	 */
	@Test
	public void create5() {
		MeleeWeapon weapon = new MeleeWeapon("weapon", 1, 1, entities.Type.FIRE, 10);
		Room room = new Room(5);
		Door door = new Door(new Room(1), room, weapon, 1, 1);
		assertEquals(room, door.getRoom2());
	}

}
