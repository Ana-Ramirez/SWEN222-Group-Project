package entityTests;

import static org.junit.Assert.*;
import org.junit.Test;

import entities.Consumable;
import entities.Gun;
import entities.MeleeWeapon;
import entities.Monster;
import entities.Player;
import entities.Projectile;
import entities.StationaryEntity;
import entities.Type;
import entities.Weapon;
import javafx.scene.image.Image;

public class EntitiesTest {

	@Test
	public void createPlayer() {
		Player player = new Player(0, 0, 5, 5, null);
		assertEquals("Tim", player.getName());
		assertEquals(3, player.getLives());
		assertNull(player.getType());
	}

	@Test
	public void createMonster() {
		Weapon weapon = new MeleeWeapon("Sword", 0, 0, 5, 5, Type.WATER, 5, null);
		Monster monster = new Monster("Baddie", 0, 0, 5, 5, Type.WATER, weapon, null);
		assertEquals(100, monster.getLives());
		assertEquals("Baddie", monster.getName());
		assertSame(weapon, monster.getWeapon());
		assertEquals(Type.WATER, monster.getType());
	}

	@Test
	public void createMeleeWeapon() {
		Weapon weapon = new MeleeWeapon("Sword", 0, 0, 5, 5, Type.WATER, 5, null);
		assertEquals("Sword", weapon.getName());
		assertEquals(Type.WATER, weapon.getType());
		
	}


	@Test
	public void createGun() {
		Weapon weapon = new Gun("Gun", 0, 0, 5, 5, Type.WATER, 5, null, null);
		assertEquals("Gun", weapon.getName());
		assertEquals(Type.WATER, weapon.getType());
	}

	@Test
	public void hitPlayer() {
		Player player = new Player(0, 0, 5, 5, null);
		Weapon weapon = new MeleeWeapon("Sword", 0, 0, 5, 5, Type.WATER, 5, null);
		Monster monster = new Monster("Baddie", 0, 0, 5, 5, Type.WATER, weapon, null);
		assertEquals(3, player.getLives());
		assertTrue(monster.attack(player));
		assertEquals(2, player.getLives());
		assertTrue(monster.attack(player));
		assertEquals(1, player.getLives());
		assertTrue(monster.attack(player));
		assertEquals(0, player.getLives());
		assertFalse(monster.attack(player));
		assertEquals(0, player.getLives());
	}

	@Test
	public void hitWaterWithWaterMonster() {
		int damage = 10;
		Player player = new Player(0, 0, 5, 5, null);
		Weapon weapon = new MeleeWeapon("Sword", 0, 0, 5, 5, Type.WATER, damage, null);
		Monster monster = new Monster("Baddie", 0, 0, 5, 5, Type.WATER, weapon, null);
	
		player.pickup(weapon);
		for (int i = 100; i > 0; i-=damage) {
			assertEquals(i, monster.getLives());
			assertTrue(((Weapon) player.getHand()).attack(monster));
		}
		assertEquals(0, monster.getLives());
		assertFalse(((Weapon) player.getHand()).attack(monster));
	}

	@Test
	public void hitWaterWithFireMonster() {
		int damage = 10;
		Player player = new Player(0, 0, 5, 5, null);
		Weapon weapon = new MeleeWeapon("Sword", 0, 0, 5, 5, Type.FIRE, damage, null);
		Monster monster = new Monster("Baddie", 0, 0, 5, 5, Type.WATER, weapon, null);
	
		player.pickup(weapon);
		for (int i = 100; i > 0; i-=damage/2) {
			assertEquals(i, monster.getLives());
			assertTrue(((Weapon) player.getHand()).attack(monster));
		}
		assertEquals(0, monster.getLives());
		assertFalse(((Weapon) player.getHand()).attack(monster));
	}

	@Test
	public void hitWaterWithEarthMonster() {
		int damage = 5;
		Player player = new Player(0, 0, 5, 5, null);
		Weapon weapon = new MeleeWeapon("Sword", 0, 0, 5, 5, Type.EARTH, damage, null);
		Monster monster = new Monster("Baddie", 0, 0, 5, 5, Type.WATER, weapon, null);
	
		player.pickup(weapon);
		for (int i = 100; i > 0; i-=damage*2) {
			assertEquals(i, monster.getLives());
			assertTrue(((Weapon) player.getHand()).attack(monster));
		}
		assertEquals(0, monster.getLives());
		assertFalse(((Weapon) player.getHand()).attack(monster));
	}

	@Test
	public void killPlayer() {
		Player player = new Player(0, 0, 5, 5, null);
		Weapon weapon = new MeleeWeapon("Sword", 0, 0, 5, 5, Type.WATER, 5, null);
		Monster monster = new Monster("Baddie", 0, 0, 5, 5, Type.WATER, weapon, null);
		assertEquals(3, player.getLives());
		assertTrue(player.isAlive());
		assertTrue(monster.attack(player));
		assertEquals(2, player.getLives());
		assertTrue(player.isAlive());
		assertTrue(monster.attack(player));
		assertEquals(1, player.getLives());
		assertTrue(player.isAlive());
		assertTrue(monster.attack(player));
		assertEquals(0, player.getLives());
		assertFalse(player.isAlive());
		assertFalse(monster.attack(player));
		assertEquals(0, player.getLives());
		assertFalse(player.isAlive());
	}

	@Test
	public void killMonster() {
		int damage = 10;
		Player player = new Player(0, 0, 5, 5, null);
		Weapon weapon = new MeleeWeapon("Sword", 0, 0, 5, 5, Type.WATER, damage, null);
		Monster monster = new Monster("Baddie", 0, 0, 5, 5, Type.WATER, weapon, null);
	
		player.pickup(weapon);
		for (int i = 100; i > 0; i-=damage) {
			assertEquals(i, monster.getLives());
			assertTrue(monster.isAlive());
			assertTrue(((Weapon) player.getHand()).attack(monster));
		}
		assertEquals(0, monster.getLives());
		assertFalse(((Weapon) player.getHand()).attack(monster));
		assertFalse(monster.isAlive());
	}
	
	@Test
	public void useHealthConsumable() {
		Player player = new Player(0, 0, 5, 5, null);
		Consumable cons = new Consumable("Health", 0, 0, 5, 5, "Lives, 2", null);
		assertEquals(3, player.getLives());
		assertNull(player.pickup(cons));
		assertTrue(((Consumable) player.getHand()).canUse());
		assertTrue(player.use());
		assertEquals(5, player.getLives());
	}
	
	@Test
	public void badConsumableCommand() {
		try {
		Consumable cons = new Consumable("Health", 0, 0, 5, 5, "Health, 2", null);
		fail("Should not succeed"); 
		} catch (IllegalArgumentException e) {
			
		}
	}
	
	@Test
	public void badConsumableVariable() {
		try {
		Consumable cons = new Consumable("Health", 0, 0, 5, 5, "Lives, a", null);
		fail("Should not succeed"); 
		} catch (IllegalArgumentException e) {
			
		}
	}
	
	@Test
	public void consumableTooManyArgs() {
		try {
		Consumable cons = new Consumable("Health", 0, 0, 5, 5, "Lives, 2, a", null);
		fail("Should not succeed"); 
		} catch (IllegalArgumentException e) {
			
		}
	}
	
	@Test
	public void consumableTooFewArgs() {
		try {
		Consumable cons = new Consumable("Health", 0, 0, 5, 5, "Lives", null);
		fail("Should not succeed"); 
		} catch (IllegalArgumentException e) {
			
		}
	}
	
	
	@Test
	public void consumableNoArgs() {
		try {
		Consumable cons = new Consumable("Health", 0, 0, 5, 5, "", null);
		fail("Should not succeed"); 
		} catch (IllegalArgumentException e) {
			
		}
	}
	
	@Test
	public void multipleUseConsumable() {
		Player player = new Player(0, 0, 5, 5, null);
		Consumable cons = new Consumable("Health", 0, 0, 5, 5, "Lives, 2", null);
		assertEquals(3, player.getLives());
		assertNull(player.pickup(cons));
		assertTrue(((Consumable) player.getHand()).canUse());
		assertTrue(player.use());
		assertEquals(5, player.getLives());
		assertFalse(((Consumable) player.getHand()).canUse());
		assertFalse(player.use());
	}
	
	@Test
	public void hitFireWithFireMonster() {
		int damage = 10;
		Player player = new Player(0, 0, 5, 5, null);
		Weapon weapon = new MeleeWeapon("Sword", 0, 0, 5, 5, Type.FIRE, damage, null);
		Monster monster = new Monster("Baddie", 0, 0, 5, 5, Type.FIRE, weapon, null);
	
		player.pickup(weapon);
		for (int i = 100; i > 0; i-=damage) {
			assertEquals(i, monster.getLives());
			assertTrue(((Weapon) player.getHand()).attack(monster));
		}
		assertEquals(0, monster.getLives());
		assertFalse(((Weapon) player.getHand()).attack(monster));
	}

	@Test
	public void hitFireWithEarthMonster() {
		int damage = 10;
		Player player = new Player(0, 0, 5, 5, null);
		Weapon weapon = new MeleeWeapon("Sword", 0, 0, 5, 5, Type.EARTH, damage, null);
		Monster monster = new Monster("Baddie", 0, 0, 5, 5, Type.FIRE, weapon, null);
	
		player.pickup(weapon);
		for (int i = 100; i > 0; i-=damage/2) {
			assertEquals(i, monster.getLives());
			assertTrue(((Weapon) player.getHand()).attack(monster));
		}
		assertEquals(0, monster.getLives());
		assertFalse(((Weapon) player.getHand()).attack(monster));
	}

	@Test
	public void hitFireWithWaterMonster() {
		int damage = 5;
		Player player = new Player(0, 0, 5, 5, null);
		Weapon weapon = new MeleeWeapon("Sword", 0, 0, 5, 5, Type.WATER, damage, null);
		Monster monster = new Monster("Baddie", 0, 0, 5, 5, Type.FIRE, weapon, null);
	
		player.pickup(weapon);
		for (int i = 100; i > 0; i-=damage*2) {
			assertEquals(i, monster.getLives());
			assertTrue(((Weapon) player.getHand()).attack(monster));
		}
		assertEquals(0, monster.getLives());
		assertFalse(((Weapon) player.getHand()).attack(monster));
	}
	
	@Test
	public void hitEarthWithEarthMonster() {
		int damage = 10;
		Player player = new Player(0, 0, 5, 5, null);
		Weapon weapon = new MeleeWeapon("Sword", 0, 0, 5, 5, Type.EARTH, damage, null);
		Monster monster = new Monster("Baddie", 0, 0, 5, 5, Type.EARTH, weapon, null);
	
		player.pickup(weapon);
		for (int i = 100; i > 0; i-=damage) {
			assertEquals(i, monster.getLives());
			assertTrue(((Weapon) player.getHand()).attack(monster));
		}
		assertEquals(0, monster.getLives());
		assertFalse(((Weapon) player.getHand()).attack(monster));
	}

	@Test
	public void hitEarthWithWaterMonster() {
		int damage = 10;
		Player player = new Player(0, 0, 5, 5, null);
		Weapon weapon = new MeleeWeapon("Sword", 0, 0, 5, 5, Type.WATER, damage, null);
		Monster monster = new Monster("Baddie", 0, 0, 5, 5, Type.EARTH, weapon, null);
	
		player.pickup(weapon);
		for (int i = 100; i > 0; i-=damage/2) {
			assertEquals(i, monster.getLives());
			assertTrue(((Weapon) player.getHand()).attack(monster));
		}
		assertEquals(0, monster.getLives());
		assertFalse(((Weapon) player.getHand()).attack(monster));
	}

	@Test
	public void hitEarthWithFireMonster() {
		int damage = 5;
		Player player = new Player(0, 0, 5, 5, null);
		Weapon weapon = new MeleeWeapon("Sword", 0, 0, 5, 5, Type.FIRE, damage, null);
		Monster monster = new Monster("Baddie", 0, 0, 5, 5, Type.EARTH, weapon, null);
	
		player.pickup(weapon);
		for (int i = 100; i > 0; i-=damage*2) {
			assertEquals(i, monster.getLives());
			assertTrue(((Weapon) player.getHand()).attack(monster));
		}
		assertEquals(0, monster.getLives());
		assertFalse(((Weapon) player.getHand()).attack(monster));
	}
	
	@Test
	public void gunAmmoDifferentObjects() {
		Gun weapon = new Gun("Gun", 0, 0, 5, 5, Type.WATER, 5, null, null);
		Projectile ammo1 = weapon.getAmmo();
		Projectile ammo2 = weapon.getAmmo();
		assertEquals(ammo1.getName(), ammo2.getName());
		assertEquals(ammo1.getBaseDamage(), ammo2.getBaseDamage());
		assertNotSame(ammo1, ammo2);
	}

	@Test
	public void pickupDropAtSameTimeItems() {
		Player player = new Player(0, 0, 5, 5, null);
		Consumable cons1 = new Consumable("Health", 0, 0, 5, 5, "Lives, 2", null);
		Consumable cons2 = new Consumable("Health", 0, 0, 5, 5, "Lives, 2", null);
		Consumable cons3 = new Consumable("Health", 0, 0, 5, 5, "Lives, 2", null);
		Consumable cons4 = new Consumable("Health", 0, 0, 5, 5, "Lives, 2", null);

		
		assertNull(player.pickup(cons1));
		assertTrue(player.selectItem(1));
		assertNull(player.pickup(cons2));
		assertTrue(player.selectItem(2));
		assertNull(player.pickup(cons3));
		assertTrue(player.selectItem(0));
		assertSame(cons1, player.pickup(cons4));
	}
	
	@Test
	public void confirmFullInventory() {
		Player player = new Player(0, 0, 5, 5, null);
		Consumable cons1 = new Consumable("Health", 0, 0, 5, 5, "Lives, 2", null);
		Consumable cons2 = new Consumable("Health", 0, 0, 5, 5, "Lives, 2", null);
		Consumable cons3 = new Consumable("Health", 0, 0, 5, 5, "Lives, 2", null);

		
		assertNull(player.pickup(cons1));
		assertTrue(player.selectItem(1));
		assertNull(player.pickup(cons2));
		assertTrue(player.selectItem(2));
		assertNull(player.pickup(cons3));
		
		Consumable[] testArray = {cons1, cons2, cons3};
		assertArrayEquals(testArray, player.getInventory());
	}
	
	@Test
	public void selectInvlaidInventorySlot() {
		Player player = new Player(0, 0, 5, 5, null);

		assertFalse(player.selectItem(3));
		assertFalse(player.selectItem(-1));
	}
	
	
	/**
	 * External testing
	 */

	@Test
	public void createConsumable() {
		Consumable cons = new Consumable("Health", 0, 0, 5, 5, "Lives, 2", null);
		assertEquals("Health", cons.getName());
	}

	@Test
	public void createStationaryEntity() {
		StationaryEntity sEntity = new StationaryEntity("Door", 0, 0, 5, 5, null);
		assertEquals("Door", sEntity.getName());
	}

	
	@Test
	public void movePlayerByXY(){
		Player player = new Player(0, 0, 5, 5, null);
		assertTrue(player.moveBy(5,5));
		assertEquals(5.0f, player.getX(),0);
		assertEquals(5.0f, player.getY(),0);
	}
	
	@Test
	public void movePlayerToXY(){
		Player player = new Player(0, 0, 5, 5, null);
		assertTrue(player.moveBy(20,100));
		assertEquals(20.0f, player.getX(),0);
		assertEquals(100.0f, player.getY(),0);
	}
	

	

}
