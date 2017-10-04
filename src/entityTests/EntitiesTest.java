package entityTests;

import static org.junit.Assert.*;
import org.junit.Test;

import entities.Gun;
import entities.MeleeWeapon;
import entities.Monster;
import entities.Player;
import entities.Type;
import entities.Weapon;

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
		fail("Not yet implemented");
	}

	@Test
	public void hitStrongMonster() {
		fail("Not yet implemented");
	}

	@Test
	public void hitWeakMonster() {
		fail("Not yet implemented");
	}

	@Test
	public void killPlayer() {
		fail("Not yet implemented");
	}

	@Test
	public void killMonster() {
		fail("Not yet implemented");
	}

}
