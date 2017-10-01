package entities;

import static org.junit.Assert.*;
import org.junit.Test;

public class EntitiesTest {

	@Test
	public void createPlayer() {
		Player player = new Player(0,0);
		assertEquals(player.getName(), "Tim");
		assertEquals(player.getLives(), 3);
		assertNull(player.getType());
	}
	
	@Test
	public void createMonster() {
		fail("Not yet implemented");
	}
	
	@Test
	public void createWeapon() {
		fail("Not yet implemented");
	}
	
	@Test
	public void hitPlayer() {
		fail("Not yet implemented");
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
