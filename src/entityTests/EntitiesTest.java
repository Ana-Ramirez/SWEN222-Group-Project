package entityTests;

import static org.junit.Assert.*;
import org.junit.Test;

import entities.Consumable;
import entities.Gun;
import entities.MeleeWeapon;
import entities.Monster;
import entities.Player;
import entities.Projectile;
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
		assertNotSame(weapon, monster.getWeapon());
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
	public void hitSameTypeMonster() {
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
	public void hitStrongerMonster() {
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
	public void hitWeakerMonster() {
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
	
	
	
	
	

}
