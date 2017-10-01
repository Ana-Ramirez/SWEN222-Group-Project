package entities;

import static org.junit.Assert.*;
import org.junit.Test;

public class EntitiesTest {

	@Test
	public void createPlayer() {
		Player player = new Player(0, 0, 5, 5);
		assertEquals("Tim", player.getName());
		assertEquals(3, player.getLives());
		assertNull(player.getType());
	}

	@Test
	public void createMonster() {
		Weapon weapon = new MeleeWeapon("Sword", 0, 0, 5, 5, Type.WATER, 5);
		Monster monster = new Monster("Baddie", 0, 0, 5, 5, Type.WATER, weapon);
		assertEquals(100, monster.getLives());
		assertEquals("Baddie", monster.getName());
		assertNotSame(weapon, monster.getWeapon());
		assertEquals(Type.WATER, monster.getType());
	}

	@Test
	public void createWeapon() {
		Weapon weapon = new MeleeWeapon("Sword", 0, 0, 5, 5, Type.WATER, 5);
		assertEquals("Sword", weapon.getName());
		assertEquals(Type.WATER, weapon.getType());
	}

	@Test
	public void hitPlayer() {
		Player player = new Player(0, 0, 5, 5);
		Weapon weapon = new MeleeWeapon("Sword", 0, 0, 5, 5, Type.WATER, 5);
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
