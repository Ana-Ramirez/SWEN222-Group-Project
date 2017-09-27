package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import entities.Consumable;
import entities.Pickupable;
import entities.Player;
import logic.Door;
import logic.Room;

public class RoomTests {
	
	/**
	 * create a room
	 */
	@Test
	public void create1() {
		Room room = new Room(4);
		assertEquals(4, room.getRoomNum());
	}
	
	/**
	 * Player moved through door, now in room 2
	 */
	@Test
	public void move1() {
		Room room1 = new Room(4);
		Room room2 = new Room(3);
		Door door = new Door(room1, room2, new Consumable("food", 100, 100), 1, 1);
		room1.addDoor(door);
		room2.addDoor(door);
		Player player = new Player(20, 20);
		room1.setPlayer(player);
		
		room1.goThroughDoor(room1.getDoor(1));
		assertEquals(player, room2.getPlayer());
	}
	
	/**
	 * Player moved through door, removed from room 1
	 */
	@Test
	public void move2() {
		Room room1 = new Room(4);
		Room room2 = new Room(3);
		Door door = new Door(room1, room2, new Consumable("food", 100, 100), 1, 1);
		room1.addDoor(door);
		room2.addDoor(door);
		Player player = new Player(20, 20);
		room1.setPlayer(player);
		
		room1.goThroughDoor(room1.getDoor(1));
		assertEquals(null, room1.getPlayer());
	}
	
//	/**
//	 * move the player within the room
//	 */
//	@Test
//	public void move3() {
//		Room room = new Room(4);
//		assertEquals(4, room.getRoomNum());
//	}
	
//	/**
//	 * get Monster from room
//	 */
//	@Test
//	public void get1() {
//		Room room = new Room(4);
//		assertEquals(4, room.getRoomNum());
//	}
//	
//	/**
//	 * get door from room
//	 */
//	@Test
//	public void get2() {
//		Room room = new Room(4);
//		assertEquals(4, room.getRoomNum());
//	}
//	
//	/**
//	 * get item from room
//	 */
//	@Test
//	public void get3() {
//		Room room = new Room(4);
//		assertEquals(4, room.getRoomNum());
//	}
//	
//	/**
//	 * Add door to room
//	 */
//	@Test
//	public void add1() {
//		Room room = new Room(4);
//		assertEquals(4, room.getRoomNum());
//	}
//	
//	/**
//	 * Add item to room
//	 */
//	@Test
//	public void add2() {
//		Room room = new Room(4);
//		assertEquals(4, room.getRoomNum());
//	}
//	
//	/**
//	 * Remove item from room
//	 */
//	@Test
//	public void remove1() {
//		Room room = new Room(4);
//		assertEquals(4, room.getRoomNum());
//	}
//	
//	/**
//	 * check door status (if locked)
//	 */
//	@Test
//	public void doorLocked() {
//		Room room = new Room(4);
//		assertEquals(4, room.getRoomNum());
//	}
	

}
