package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import entities.Consumable;
import entities.MeleeWeapon;
import entities.Monster;
import entities.Pickupable;
import entities.Player;
import entities.Type;
import entities.Weapon;
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
		Door door = new Door(room1, room2, new Consumable("food", 100, 100, 1, 1, Type.EARTH), 1, 1);
		room1.addDoor(door);
		room2.addDoor(door);
		Player player = new Player(20, 20, 1, 1);
		room1.setPlayer(player);
		
		room1.goThroughDoor(room1.getDoor(1));
		assertEquals(player, room2.getPlayer());
	}
	
	/**
	 * Player moved through door, player removed from room 1
	 */
	@Test
	public void move2() {
		Room room1 = new Room(4);
		Room room2 = new Room(3);
		Door door = new Door(room1, room2, new Consumable("food", 100, 100, 1, 1, Type.EARTH), 1, 1);
		room1.addDoor(door);
		room2.addDoor(door);
		Player player = new Player(20, 20, 1, 1);
		room1.setPlayer(player);
		
		room1.goThroughDoor(room1.getDoor(1));
		assertEquals(null, room1.getPlayer());
	}
	
	/**
	 * move the player to onto a pickupable (consumable)
	 */
	@Test
	public void move3() {
		Room room = new Room(4);
		Player player = new Player(20, 20, 1, 1);
		room.setPlayer(player);
		Consumable food = new Consumable("food", 40, 40, 1, 1, Type.EARTH);
		room.addItem(food);
		assertTrue(room.movePlayer(player.getX() + 20, player.getY()));
	}
	
	/**
	 * move the player to onto something unable to
	 * pick up (monster)
	 */
	@Test
	public void move4() {
		Room room = new Room(4);
		Player player = new Player(30, 45, 10, 10);
		room.setPlayer(player);
		Monster monster = new Monster("mon", 40, 40, 10, 10, Type.FIRE, null);
		room.addItem(monster);
		assertFalse(room.movePlayer(player.getX() + 15, player.getY()));
	}
	
	/**
	 * move the player into nothing
	 */
	@Test
	public void move5() {
		Room room = new Room(4);
		Player player = new Player(20, 20, 1, 1);
		room.setPlayer(player);
		assertTrue(room.movePlayer(player.getX() + 20, player.getY()));
	}
	
	/**
	 * move the player into door
	 * door x, y = 10; w, h = 10
	 */
	@Test
	public void move6() {
		Room room1 = new Room(1);
		Room room2 = new Room(2);
		Door door = new Door(room1, room2, null, 0, 0);
		Player player = new Player(15, 15, 1, 1);
		room1.setPlayer(player);
		room1.addDoor(door);
		assertTrue(room1.doorCollision());
	}
	
	/**
	 * get Monster from room
	 */
	@Test
	public void get1() {
		Room room = new Room(4);
		Weapon weapon = new MeleeWeapon("melee", 0, 0, 1, 1, Type.FIRE, 10);
		Monster monster = new Monster("mon", 40, 40, 1, 1, Type.FIRE, weapon);
		room.addItem(monster);
		assertEquals(monster, room.getMonster(monster));
	}
	
	/**
	 * get door from room
	 */
	@Test
	public void get2() {
		Room room1 = new Room(1);
		Room room2 = new Room(2);
		Consumable item = new Consumable("food", 10, 10, 1, 1, Type.EARTH);
		Door door = new Door(room1, room2, item, 1, 1);
		room1.addDoor(door);
		room2.addDoor(door);
		assertEquals(door, room1.getDoor(1));
	}
	
	/**
	 * get item from room
	 */
	@Test
	public void get3() {
		Room room = new Room(4);
		Consumable item = new Consumable("food", 10, 10, 1, 1, Type.EARTH);
		room.addItem(item);
		assertEquals(item, room.getItem("food"));
	}
	
	/**
	 * Remove item from room
	 */
	@Test
	public void remove1() {
		Room room = new Room(4);
		Consumable item = new Consumable("food", 10, 10, 1, 1, Type.EARTH);
		room.addItem(item);
		assertTrue(room.removeItem("food"));
	}
	
	/**
	 * check door status is locked as new door
	 */
	@Test
	public void doorLocked() {
		Room room1 = new Room(1);
		Room room2 = new Room(2); 
		Consumable item = new Consumable("food", 10, 10, 1, 1, Type.EARTH);
		Door door = new Door(room1, room2, item, 1, 1);
		room1.addDoor(door);
		assertTrue(room1.doorLocked(door));
	}

}
