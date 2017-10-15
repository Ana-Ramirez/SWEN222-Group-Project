package logicTests;

import static org.junit.Assert.*;

import java.awt.geom.Rectangle2D;

import org.junit.Test;

import entities.Consumable;
import entities.MeleeWeapon;
import entities.Monster;
import entities.Pickupable;
import entities.Player;
import entities.Weapon;
import interfaces.Type;
import logic.Door;
import logic.Level;
import logic.Room;
import resources.ImgResources;

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
		Door door = new Door(room1, room2, new Consumable(new Rectangle2D.Double(10,  10,  10,  10), "Lives 1", null), 1, 1);
		room1.addEntity(door);
		room2.addEntity(door);
		level.setCurrentRoom(room1);		
		room1.goThroughDoor(room1.getDoor(1));
		assertEquals(room2, level.getCurrentRoom());
	}
	
	/**
	 * move the player to onto a pickupable (consumable)
	 */
	@Test
	public void move3() {
		Player player = new Player(new Rectangle2D.Double(10, 10, 10, 10), null);
		Level level = new Level(player);
		Room room1 = new Room(4, level);
		Consumable food = new Consumable(new Rectangle2D.Double(10,  10,  10,  10), "Lives 1", null);		
		level.setCurrentRoom(room1);
		room1.addEntity(food);
		player.pickup(food);
		player.moveTo(10, 10);
		player.pickup(food);
		assertTrue(player.getBackpack()[0] != null);
	}
	
	/**
	 * move the player to onto something unable to
	 * pick up (monster)
	 */
	@Test
	public void move4() {
		Room room = new Room(4);
		Player player = new Player(0, 0, 10, 10, null);
		room.setPlayer(player);
		Monster monster = new Monster("mon", 40, 40, 10, 10, Type.FIRE, null, null);
		room.addEntity(monster);
		player.moveTo(40, 40);
		room.movePlayer();
		assertTrue(player.getLives() != 3);
	}
	
//	/**
//	 * move the player into door
//	 * door x, y = 10; w, h = 10
//	 */
//	@Test
//	public void move6() {
//		Room room1 = new Room(1);
//		Room room2 = new Room(2);
//		Door door = new Door(room1, room2, null, 0, 0);
//		Player player = new Player(15, 15, 1, 1, null);
//		room1.setPlayer(player);
//		room1.addEntity(door);
//		player.moveTo(x, y)
//	}
	
	/**
	 * get Monster from room
	 */
	@Test
	public void get1() {
		Room room = new Room(4);
		Weapon weapon = new MeleeWeapon("melee", 0, 0, 1, 1, Type.FIRE, 10, null);
		Monster monster = new Monster("mon", 40, 40, 1, 1, Type.FIRE, weapon, null);
		room.addEntity(monster);
		assertEquals(monster.getName(), room.getMonster(monster.getName()).getName());
	}
	
	/**
	 * get door from room
	 */
	@Test
	public void get2() {
		Room room1 = new Room(1);
		Room room2 = new Room(2);
		Consumable item = new Consumable("food", 10, 10, 1, 1, "Lives 2", null);
		Door door = new Door(room1, room2, item, 1, 1);
		room1.addEntity(door);
		room2.addEntity(door);
		assertEquals(door, room1.getDoor(1));
	}
	
	/**
	 * get item from room
	 */
	@Test
	public void get3() {
		Room room = new Room(4);
		Consumable item = new Consumable("food", 10, 10, 1, 1, "Lives 2", resources.ImgResources.CONSOLE1.img);
		room.addEntity(item);
		assertEquals(item, room.getItem("food"));
	}
	
	/**
	 * Remove item from room
	 */
	@Test
	public void remove1() {
		Player player = new Player(new Rectangle2D.Double(10, 10, 10, 10), null);
		Level level = new Level(player);
		Room room = new Room(4, level);
		Consumable food = new Consumable(new Rectangle2D.Double(10,  10,  10,  10), "Lives 1", null);		
		room.addEntity(food);
		assertTrue(room.removeItem("food"));
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
