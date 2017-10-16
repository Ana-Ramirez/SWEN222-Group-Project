package logicTests;

import static org.junit.Assert.*;

import java.awt.geom.Rectangle2D;

import org.junit.Test;

import ai.FollowingEnemy;
import entities.ConsumableEntity;
import entities.GunEntity;
import entities.MeleeWeaponEntity;
import entities.MonsterEntity;
import entities.PlayerEntity;
import entities.ProjectileEntity;
import interfaces.EntityType;
import logic.Door;
import logic.Level;
import logic.Room;
import logic.Wall;

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
		PlayerEntity player = new PlayerEntity(new Rectangle2D.Double(10, 10, 10, 10), null);
		Level level = new Level(player);
		Room room = new Room(4, level);		
		assertEquals(4, room.getRoomNum());
	}
	
	/**
	 * PlayerEntity moved through door, now in room 2
	 */
	@Test
	public void move1() {
		PlayerEntity player = new PlayerEntity(new Rectangle2D.Double(10, 10, 10, 10), null);
		Level level = new Level(player);
		Room room1 = new Room(4, level);
		Room room2 = new Room(3, level);
		ConsumableEntity food = new ConsumableEntity(new Rectangle2D.Double(10,  10,  10,  10), "Lives 1", null);
		Door door = new Door(room1, room2, food, 1, 1);
		player.pickup(food);
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
		PlayerEntity player = new PlayerEntity(new Rectangle2D.Double(10, 10, 10, 10), null);
		Level level = new Level(player);
		Room room1 = new Room(4, level);
		ConsumableEntity food = new ConsumableEntity(new Rectangle2D.Double(10,  10,  10,  10), "Lives 1", null);		
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
		PlayerEntity player = new PlayerEntity(new Rectangle2D.Double(10, 10, 10, 10), null);
		MeleeWeaponEntity weapon = new MeleeWeaponEntity(new Rectangle2D.Double(10, 10, 10, 10), EntityType.EARTH, 100, null);
		MonsterEntity monster = new MonsterEntity(new Rectangle2D.Double(10, 10, 10, 10), 100, EntityType.EARTH, weapon, null, new FollowingEnemy(player, 1));
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
		PlayerEntity player = new PlayerEntity(new Rectangle2D.Double(10, 10, 10, 10), null);
		Level level = new Level(player);
		Room room1 = new Room(4, level);
		ConsumableEntity food = new ConsumableEntity(new Rectangle2D.Double(10,  10,  10,  10), "Lives 1", null);
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
		PlayerEntity player = new PlayerEntity(new Rectangle2D.Double(10, 10, 10, 10), null);
		Level level = new Level(player);
		Room room1 = new Room(1, level);
		Room room2 = new Room(2, level); 
		ConsumableEntity food = new ConsumableEntity(new Rectangle2D.Double(10,  10,  10,  10), "Lives 1", null);		
		Door door = new Door(room1, room2, food, 1, 1);
		room1.addEntity(door);
		assertEquals(door, room1.getDoor(1));
		assertTrue(room1.doorLocked(door));
	}
	
	
	/**
	 * Testing tick method with monster boss
	 */
	@Test
	public void tick1() {
		PlayerEntity player = new PlayerEntity(new Rectangle2D.Double(10, 10, 10, 10), null);
		Level level = new Level(player);
		Room room1 = new Room(4, level);
		GunEntity	gun = new GunEntity(new Rectangle2D.Double(10, 10, 10, 10), null, 10, 10, null, null);	
		MonsterEntity monster = new MonsterEntity(new Rectangle2D.Double(10, 10, 10, 10), 100, EntityType.EARTH, gun, null, new FollowingEnemy(player, 1));
		level.setCurrentRoom(room1);
		level.setBoss(monster);
		room1.addEntity(monster);
		player.pickup(gun);
		room1.use(10, 10);
		room1.tick(0, 0, 120);
		assertTrue(room1.getEntities().contains(monster));
	}
	
	/**
	 * Testing playerTick
	 */
	@Test
	public void tick2() {
		PlayerEntity player = new PlayerEntity(new Rectangle2D.Double(0, 0, 800, 600), null);
		Level level = new Level(player);
		Room room1 = new Room(4, level);
		Room room2 = new Room(3, level);
		ConsumableEntity food = new ConsumableEntity(new Rectangle2D.Double(10,  10,  10,  10), "Lives 1", null);
		Door door = new Door(room1, room2, food, 1, 0);
		Wall wall = new Wall("top", 10, 10, 10, 10);
		level.setCurrentRoom(room1);
		room1.tick(10, 5, 120);
		room1.tick(3, 5, 120);
	}
	
	/**
	 * Testing projectile tick
	 */
	@Test
	public void tick3() {
		PlayerEntity player = new PlayerEntity(new Rectangle2D.Double(100, 100, 10, 10), null);
		Level level = new Level(player);
		Room room1 = new Room(4, level);
		level.setCurrentRoom(room1);
		
		GunEntity	gun = new GunEntity(new Rectangle2D.Double(10, 10, 10, 10), EntityType.EARTH, 10, 10, null, null);	
		Wall wall = new Wall("top", 10, 10, 10, 10);
		MonsterEntity monster = new MonsterEntity(new Rectangle2D.Double(10, 10, 10, 10), 100, EntityType.EARTH, gun, null, new FollowingEnemy(player, 1));
		room1.addEntity(wall);
		room1.addEntity(monster);
		player.pickup(gun);
		room1.addEntity(gun.createProjectile(10, 10));
		room1.tick(10, 10, 120);
		assertTrue(monster.isAlive());
	}
	
	/**
	 * Testing dropping item
	 */
	@Test
	public void drop() {
		PlayerEntity player = new PlayerEntity(new Rectangle2D.Double(100, 100, 10, 10), null);
		Level level = new Level(player);
		Room room1 = new Room(4, level);
		level.setCurrentRoom(room1);
		
		GunEntity	gun = new GunEntity(new Rectangle2D.Double(10, 10, 10, 10), EntityType.EARTH, 10, 10, null, null);	
		player.pickup(gun);
		int oldSize = room1.getEntities().size();
		room1.dropItem();
		assertEquals(oldSize + 1, room1.getEntities().size());
	}
	
	/**
	 * Testing the use method
	 */
	@Test
	public void use() {
		PlayerEntity player = new PlayerEntity(new Rectangle2D.Double(100, 100, 10, 10), null);
		Level level = new Level(player);
		Room room1 = new Room(4, level);
		level.setCurrentRoom(room1);
		MeleeWeaponEntity melee = new MeleeWeaponEntity(new Rectangle2D.Double(10, 10, 10, 10), EntityType.EARTH, 10, null);
		MonsterEntity monster = new MonsterEntity(new Rectangle2D.Double(10, 10, 10, 10), 100, EntityType.EARTH, melee, null, new FollowingEnemy(player, 1));
		room1.addEntity(monster);
		
		player.pickup(melee);
		room1.use(100, 100);
		assertTrue(melee.attack(monster));
	}

}
