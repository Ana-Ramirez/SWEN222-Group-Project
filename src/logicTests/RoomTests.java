package logicTests;

import static org.junit.Assert.*;

import java.awt.geom.Rectangle2D;

import org.junit.Test;

import ai.FollowingEnemy;
import entities.Consumable;
import entities.MeleeWeapon;
import entities.Monster;
import entities.Player;
import interfaces.EntityType;
import logic.Door;
import logic.Level;
import logic.Room;

/**
 * This class tests the Room class
 * @author lewismcewan
 *
 */
public class RoomTests {
	
	/**
	 * create a room
	 */
	@Test
	public void create1() {
		Player player = new Player(new Rectangle2D.Double(10, 10, 10, 10), null);
		Level level = new Level(player);
		Room room = new Room(4, level);		
		assertEquals(4, room.getRoomNum());
	}
	
	/**
	 * Player moved through door, now in room 2
	 */
	@Test
	public void move1() {
		Player player = new Player(new Rectangle2D.Double(10, 10, 10, 10), null);
		Level level = new Level(player);
		Room room1 = new Room(4, level);
		Room room2 = new Room(3, level);
		Consumable food = new Consumable(new Rectangle2D.Double(10,  10,  10,  10), "Lives 1", null);
		Door door = new Door(room1, room2, food, 1, 1);
		room1.addEntity(door);
		room2.addEntity(door);
		room1.addEntity(food);
		level.setCurrentRoom(room1);		
		room1.goThroughDoor(room1.getDoor(1));
		assertEquals(room2, level.getCurrentRoom());
		assertEquals(door, room1.getDoor(1));
		assertEquals(door, room2.getDoor(1));
		assertTrue(room1.getEntities().contains(food));
	}
	
	/**
	 * move the player to onto a pickupable (consumable)
	 */
	@Test
	public void move2() {
		Player player = new Player(new Rectangle2D.Double(10, 10, 10, 10), null);
		Level level = new Level(player);
		Room room1 = new Room(4, level);
		Consumable food = new Consumable(new Rectangle2D.Double(10,  10,  10,  10), "Lives 1", null);		
		level.setCurrentRoom(room1);
		room1.addEntity(food);
		player.pickup(food);	//picking up first item means it goes into hand
		player.moveTo(10, 10);
		player.pickup(food);	//second item goes into backpack
		assertTrue(player.getBackpack()[0] != null);
	}
	
	/**
	 * move the player to onto something unable to
	 * pick up (monster)
	 */
	@Test
	public void move3() {
		Player player = new Player(new Rectangle2D.Double(10, 10, 10, 10), null);
		MeleeWeapon weapon = new MeleeWeapon(new Rectangle2D.Double(10, 10, 10, 10), EntityType.EARTH, 100, null);
		Monster monster = new Monster(new Rectangle2D.Double(10, 10, 10, 10), 100, EntityType.EARTH, weapon, null, new FollowingEnemy(player, 1));
		Level level = new Level(player);
		Room room1 = new Room(4, level);
		level.setCurrentRoom(room1);
		assertFalse(room1.getEntities().contains(monster));
		room1.addEntity(monster);
		assertTrue(room1.getEntities().contains(monster));
		assertEquals(3, player.getLives());
		player.moveTo(10, 10);
		room1.tick(0, 0, 10000);
		assertEquals(2, player.getLives());
	}
		
	/**
	 * Remove item from room
	 */
	@Test
	public void remove1() {
		Player player = new Player(new Rectangle2D.Double(10, 10, 10, 10), null);
		Level level = new Level(player);
		Room room1 = new Room(4, level);
		Consumable food = new Consumable(new Rectangle2D.Double(10,  10,  10,  10), "Lives 1", null);
		room1.addEntity(food);
		level.setCurrentRoom(room1);	
		room1.pickupItem();
		assertFalse(room1.getEntities().contains(food));
	}
	
	/**
	 * check door status is locked as new door
	 */
	@Test
	public void doorLocked() {
		Player player = new Player(new Rectangle2D.Double(10, 10, 10, 10), null);
		Level level = new Level(player);
		Room room1 = new Room(1, level);
		Room room2 = new Room(2, level); 
		Consumable food = new Consumable(new Rectangle2D.Double(10,  10,  10,  10), "Lives 1", null);		
		Door door = new Door(room1, room2, food, 1, 1);
		room1.addEntity(door);
		assertTrue(room1.doorLocked(door));
	}

}
