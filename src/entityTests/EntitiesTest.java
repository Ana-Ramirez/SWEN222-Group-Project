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
import interfaces.Enemies;
import javafx.geometry.BoundingBox;
import javafx.scene.image.Image;
import resources.ImgResources;

public class EntitiesTest {

	class MockMonsterPattern implements Enemies {

		public void tick(Monster monster) {
			monster.moveBy(1, 1);
		}
	}

	@Test
	public void createPlayer() {
		Player player = new Player(0, 0, 5, 5, null);
		assertEquals(3, player.getLives());
		assertNull(player.getType());
		assertEquals(5, player.getWidth());
		assertEquals(5, player.getHeight());
		assertNull(player.getImage());
	}

	@Test
	public void createMonster() {
		Weapon weapon = new MeleeWeapon(0, 0, 5, 5, Type.WATER, 5, null);
		Monster monster = new Monster(0, 0, 5, 5, Type.WATER, weapon, null, null);
		assertEquals(100, monster.getLives());
		assertSame(weapon, monster.getWeapon());
		assertEquals(Type.WATER, monster.getType());
	}

	@Test
	public void createMeleeWeapon() {
		Weapon weapon = new MeleeWeapon(0, 0, 5, 5, Type.WATER, 5, null);
		assertEquals("Melee Weapon", weapon.getInfo());
		assertEquals(Type.WATER, weapon.getType());

	}

	@Test
	public void createGun() {
		Weapon weapon = new Gun(0, 0, 5, 5, Type.WATER, 5, null, null);
		assertEquals("Gun - Ammo: 20/20", weapon.getInfo());
		assertEquals(Type.WATER, weapon.getType());
	}

	@Test
	public void hitPlayer() {
		Player player = new Player(0, 0, 5, 5, null);
		Weapon weapon = new MeleeWeapon(0, 0, 5, 5, Type.WATER, 5, null);
		Monster monster = new Monster(0, 0, 5, 5, Type.WATER, weapon, null, null);
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
		Weapon weapon = new MeleeWeapon(0, 0, 5, 5, Type.WATER, damage, null);
		Monster monster = new Monster(0, 0, 5, 5, Type.WATER, weapon, null, null);

		player.pickup(weapon);
		for (int i = 100; i > 0; i -= damage) {
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
		Weapon weapon = new MeleeWeapon(0, 0, 5, 5, Type.FIRE, damage, null);
		Monster monster = new Monster(0, 0, 5, 5, Type.WATER, weapon, null, null);

		player.pickup(weapon);
		for (int i = 100; i > 0; i -= damage / 2) {
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
		Weapon weapon = new MeleeWeapon(0, 0, 5, 5, Type.EARTH, damage, null);
		Monster monster = new Monster(0, 0, 5, 5, Type.WATER, weapon, null, null);

		player.pickup(weapon);
		for (int i = 100; i > 0; i -= damage * 2) {
			assertEquals(i, monster.getLives());
			assertTrue(((Weapon) player.getHand()).attack(monster));
		}
		assertEquals(0, monster.getLives());
		assertFalse(((Weapon) player.getHand()).attack(monster));
	}

	@Test
	public void killPlayer() {
		Player player = new Player(0, 0, 5, 5, null);
		Weapon weapon = new MeleeWeapon(0, 0, 5, 5, Type.WATER, 5, null);
		Monster monster = new Monster(0, 0, 5, 5, Type.WATER, weapon, null, null);
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
		Weapon weapon = new MeleeWeapon(0, 0, 5, 5, Type.WATER, damage, null);
		Monster monster = new Monster(0, 0, 5, 5, Type.WATER, weapon, null, null);

		player.pickup(weapon);
		for (int i = 100; i > 0; i -= damage) {
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
		Consumable cons = new Consumable(0, 0, 5, 5, "Lives, 2", null);
		assertEquals("Lives", cons.getInfo());
		assertEquals(3, player.getLives());
		assertNull(player.pickup(cons));
		assertTrue(((Consumable) player.getHand()).canUse());
		assertTrue(player.use());
		assertEquals(5, player.getLives());
	}
	
	@Test
	public void useAmmoConsumable() {
		Player player = new Player(0, 0, 5, 5, null);
		Gun gun = new Gun(0, 0, 5, 5, Type.WATER, 5, null, null);
		Consumable cons = new Consumable(0, 0, 5, 5, "Ammo, 2", null);
		
		player.pickup(gun);
		player.selectItem(2);
		
		assertEquals(20, gun.getAmmoCount());
		assertNull(player.pickup(cons));
		assertTrue(((Consumable) player.getHand()).canUse());
		assertTrue(player.use());
		assertEquals(22, gun.getAmmoCount());
		assertNull(player.getHand());
		assertFalse(player.use());
		assertNull(player.pickup(cons));
		assertFalse(((Consumable) player.getHand()).canUse());
		assertFalse(player.use());
	}
	
	
	@Test
	public void useAmmoConsumableNoGun() {
		Player player = new Player(0, 0, 5, 5, null);
		Consumable cons = new Consumable(0, 0, 5, 5, "Ammo, 2", null);
		
		assertNull(player.pickup(cons));
		assertTrue(((Consumable) player.getHand()).canUse());
		assertFalse(player.use());

	}
	
	@Test
	public void useEmptyAmmoConsumable() {
		Player player = new Player(0, 0, 5, 5, null);
		Gun gun = new Gun(0, 0, 5, 5, Type.WATER, 5, null, null);
		Consumable cons = new Consumable(0, 0, 5, 5, "Ammo, 2", null);
		
		assertNull(player.pickup(gun));
		
		assertEquals(20, gun.getAmmoCount());
		for (int i = 0; i < 20; i++) {
			assertNotNull(gun.createProjectile(0, 0));
		}
		assertNull(gun.createProjectile(0, 0));
	}

	@Test
	public void badConsumableCommand() {
		try {
		Consumable cons = new Consumable(0, 0, 5, 5, "Health, 2", null);
		fail("Should not succeed");
		} catch (IllegalArgumentException e) {

		}
	}

	@Test
	public void badConsumableVariable() {
		try {
		Consumable cons = new Consumable(0, 0, 5, 5, "Lives, a", null);
		fail("Should not succeed");
		} catch (IllegalArgumentException e) {

		}
	}

	@Test
	public void consumableTooManyArgs() {
		try {
		Consumable cons = new Consumable(0, 0, 5, 5, "Lives, 2, a", null);
		fail("Should not succeed");
		} catch (IllegalArgumentException e) {

		}
	}

	@Test
	public void consumableTooFewArgs() {
		try {
		Consumable cons = new Consumable(0, 0, 5, 5, "Lives", null);
		fail("Should not succeed");
		} catch (IllegalArgumentException e) {

		}
	}


	@Test
	public void consumableNoArgs() {
		try {
		Consumable cons = new Consumable(0, 0, 5, 5, "", null);
		fail("Should not succeed");
		} catch (IllegalArgumentException e) {

		}
	}

	@Test
	public void multipleUseConsumable() {
		Player player = new Player(0, 0, 5, 5, null);
		Consumable cons = new Consumable(0, 0, 5, 5, "Lives, 2", null);
		assertEquals(3, player.getLives());
		assertNull(player.pickup(cons));
		assertTrue(((Consumable) player.getHand()).canUse());
		assertTrue(player.use());
		assertEquals(5, player.getLives());
		assertNull(player.getHand());
		assertFalse(player.use());
	}

	@Test
	public void hitFireWithFireMonster() {
		int damage = 10;
		Player player = new Player(0, 0, 5, 5, null);
		Weapon weapon = new MeleeWeapon(0, 0, 5, 5, Type.FIRE, damage, null);
		Monster monster = new Monster(0, 0, 5, 5, Type.FIRE, weapon, null, null);

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
		Weapon weapon = new MeleeWeapon(0, 0, 5, 5, Type.EARTH, damage, null);
		Monster monster = new Monster(0, 0, 5, 5, Type.FIRE, weapon, null, null);

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
		Weapon weapon = new MeleeWeapon(0, 0, 5, 5, Type.WATER, damage, null);
		Monster monster = new Monster(0, 0, 5, 5, Type.FIRE, weapon, null, null);

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
		Weapon weapon = new MeleeWeapon(0, 0, 5, 5, Type.EARTH, damage, null);
		Monster monster = new Monster(0, 0, 5, 5, Type.EARTH, weapon, null, null);

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
		Weapon weapon = new MeleeWeapon(0, 0, 5, 5, Type.WATER, damage, null);
		Monster monster = new Monster(0, 0, 5, 5, Type.EARTH, weapon, null, null);

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
		Weapon weapon = new MeleeWeapon(0, 0, 5, 5, Type.FIRE, damage, null);
		Monster monster = new Monster(0, 0, 5, 5, Type.EARTH, weapon, null, null);

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
		Gun weapon = new Gun(0, 0, 5, 5, Type.WATER, 5, null, null);
		Projectile ammo1 = weapon.createProjectile(0, 0);
		Projectile ammo2 = weapon.createProjectile(0, 0);
		assertEquals("Ammo", ammo1.getInfo());
		assertEquals("Ammo", ammo2.getInfo());

		assertEquals(ammo1.getBaseDamage(), ammo2.getBaseDamage());
		assertNotSame(ammo1, ammo2);
	}

	@Test
	public void pickupDropAtSameTimeItems() {
		Player player = new Player(0, 0, 5, 5, null);
		Consumable cons1 = new Consumable(0, 0, 5, 5, "Lives, 2", null);
		Consumable cons2 = new Consumable(0, 0, 5, 5, "Lives, 2", null);
		Consumable cons3 = new Consumable(0, 0, 5, 5, "Lives, 2", null);
		Consumable cons4 = new Consumable(0, 0, 5, 5, "Lives, 2", null);


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
		Consumable cons1 = new Consumable(0, 0, 5, 5, "Lives, 2", null);
		Consumable cons2 = new Consumable(0, 0, 5, 5, "Lives, 2", null);
		Consumable cons3 = new Consumable(0, 0, 5, 5, "Lives, 2", null);


		assertNull(player.pickup(cons1));
		assertTrue(player.selectItem(1));
		assertNull(player.pickup(cons2));
		assertTrue(player.selectItem(2));
		assertNull(player.pickup(cons3));

		Consumable[] testArray = {cons1, cons2, cons3};
		assertArrayEquals(testArray, player.getInventory());
	}

	@Test
	public void confirmFullInventoryMoves() {
		Player player = new Player(0, 0, 5, 5, null);
		Consumable cons1 = new Consumable(0, 0, 5, 5, "Lives, 2", null);
		Consumable cons2 = new Consumable(0, 0, 5, 5, "Lives, 2", null);
		Consumable cons3 = new Consumable(0, 0, 5, 5, "Lives, 2", null);


		assertNull(player.pickup(cons1));
		assertTrue(player.selectItem(1));
		assertNull(player.pickup(cons2));
		assertTrue(player.selectItem(2));
		assertNull(player.pickup(cons3));

		assertEquals(player.getX(), cons1.getX(), 0);
		assertEquals(player.getY(), cons1.getY(), 0);
		assertEquals(player.getX(), cons2.getX(), 0);
		assertEquals(player.getY(), cons2.getY(), 0);
		assertEquals(player.getX(), cons3.getX(), 0);
		assertEquals(player.getY(), cons3.getY(), 0);

		player.moveBy(5, 5);
		player.tick();

		assertEquals(player.getX(), cons1.getX(), 0);
		assertEquals(player.getY(), cons1.getY(), 0);
		assertEquals(player.getX(), cons2.getX(), 0);
		assertEquals(player.getY(), cons2.getY(), 0);
		assertEquals(player.getX(), cons3.getX(), 0);
		assertEquals(player.getY(), cons3.getY(), 0);
	}

	@Test
	public void selectInvlaidInventorySlot() {
		Player player = new Player(0, 0, 5, 5, null);
		assertFalse(player.selectItem(3));
		assertFalse(player.selectItem(-1));
	}

	@Test
	public void validBoundingBox() {
		Player player = new Player(0, 0, 5, 5, null);
		BoundingBox box1 = player.getBoundingBox();
		assertEquals(player.getX(), box1.getMinX(), 0);
		assertEquals(player.getY(), box1.getMinY(), 0);
		assertEquals(player.getWidth(), box1.getWidth(), 0);
		assertEquals(player.getHeight(), box1.getHeight(), 0);

		player.moveBy(10, 10);
		BoundingBox box2 = player.getBoundingBox();

		assertEquals(player.getX(), box2.getMinX(), 0);
		assertEquals(player.getY(), box2.getMinY(), 0);
		assertEquals(player.getWidth(), box2.getWidth(), 0);
		assertEquals(player.getHeight(), box2.getHeight(), 0);
	}

	@Test
	public void validExtendedBoundingBox() {
		Player player = new Player(5, 5, 5, 5, null);
		BoundingBox box1 = player.getExtendedBoundingBox();
		assertEquals(player.getX()-5, box1.getMinX(), 0);
		assertEquals(player.getY()-5, box1.getMinY(), 0);
		assertEquals(player.getWidth()+10, box1.getWidth(), 0);
		assertEquals(player.getHeight()+10, box1.getHeight(), 0);

		player.moveBy(10, 10);
		BoundingBox box2 = player.getExtendedBoundingBox();

		assertEquals(20, box2.getMinX(), 0);
		assertEquals(20, box2.getMinY(), 0);
		assertEquals(15, box2.getWidth(), 0);
		assertEquals(15, box2.getHeight(), 0);
	}

	@Test
	public void attackStationary() {
		StationaryEntity stat = new StationaryEntity(0, 0, 5, 5, null);
		Weapon sword = new MeleeWeapon(0, 0, 5, 5, Type.WATER, 5, null);
		assertFalse(sword.attack(stat));
	}

	@Test
	public void attackConsumable() {
		Consumable stat = new Consumable(0, 0, 5, 5, "Lives 2", null);
		Weapon sword = new MeleeWeapon(0, 0, 5, 5, Type.WATER, 5, null);
		assertFalse(sword.attack(stat));
	}


	@Test
	public void invlaidConsumable() {
		Consumable cons = new Consumable(0, 0, 5, 5, "Speed 2", null);
		Player player = new Player(0, 0, 5, 5, null);
		assertNull(player.pickup(cons));
		try {
			assertTrue(player.use());
			fail("Should not be able to use this consumable");
		} catch (UnsupportedOperationException e) {

		}
	}

	@Test
	public void attemptToUseWeapon() {
		Weapon sword = new MeleeWeapon(0, 0, 5, 5, Type.WATER, 5, null);
		Player player = new Player(0, 0, 5, 5, null);
		assertNull(player.pickup(sword));
		assertFalse(player.use());
	}

	@Test
	public void testStatergyPattern() {
		Monster monster = new Monster(0, 0, 5, 5, Type.WATER, null, null, new MockMonsterPattern());
		assertEquals(0, monster.getX(), 0);
		assertEquals(0, monster.getX(), 0);
		monster.tick();
		assertEquals(1, monster.getX(), 0);
		assertEquals(1, monster.getX(), 0);
		monster.tick();
		assertEquals(2, monster.getX(), 0);
		assertEquals(2, monster.getX(), 0);
	}

	@Test
	public void testEnums() {
		assertArrayEquals(Type.values(),new Type[] {Type.FIRE, Type.WATER, Type.EARTH});
		assertSame(Type.valueOf("FIRE"), Type.FIRE);
		assertSame(Type.valueOf("WATER"), Type.WATER);
		assertSame(Type.valueOf("EARTH"), Type.EARTH);
	}

	@Test
	public void tickConsumable() {
		Consumable cons = new Consumable(0, 0, 5, 5, "Lives, 2", null);
		assertEquals(0, cons.getY(), 0);
		assertEquals(0, cons.getX(), 0);
		assertEquals(5, cons.getHeight());
		assertEquals(5, cons.getWidth());
		assertNull(cons.getImage());
		cons.tick();
		assertEquals(0, cons.getY(), 0);
		assertEquals(0, cons.getX(), 0);
		assertEquals(5, cons.getHeight());
		assertEquals(5, cons.getWidth());
		assertNull(cons.getImage());
	}


	@Test
	public void tickMeleeWeapon() {
		MeleeWeapon cons = new MeleeWeapon(0, 0, 5, 5, Type.WATER, 5, null);
		assertEquals(0, cons.getY(), 0);
		assertEquals(0, cons.getX(), 0);
		assertEquals(5, cons.getHeight());
		assertEquals(5, cons.getWidth());
		assertEquals(5, cons.getBaseDamage());
		assertEquals(Type.WATER, cons.getType());
		assertNull(cons.getImage());
		cons.tick();
		assertEquals(0, cons.getY(), 0);
		assertEquals(0, cons.getX(), 0);
		assertEquals(5, cons.getHeight());
		assertEquals(5, cons.getWidth());
		assertNull(cons.getImage());
		assertEquals(5, cons.getBaseDamage());
		assertEquals(Type.WATER, cons.getType());
	}


	@Test
	public void tickGun() {
		Gun cons = new Gun(0, 0, 5, 5, Type.WATER, 5, null, null);
		assertEquals(0, cons.getY(), 0);
		assertEquals(0, cons.getX(), 0);
		assertEquals(5, cons.getHeight());
		assertEquals(5, cons.getWidth());
		assertEquals(5, cons.getBaseDamage());
		assertEquals(Type.WATER, cons.getType());
		assertNull(cons.getImage());
		cons.tick();
		assertEquals(0, cons.getY(), 0);
		assertEquals(0, cons.getX(), 0);
		assertEquals(5, cons.getHeight());
		assertEquals(5, cons.getWidth());
		assertNull(cons.getImage());
		assertEquals(5, cons.getBaseDamage());
		assertEquals(Type.WATER, cons.getType());
	}

	@Test
	public void tickProjectileMovementTest() {
		Gun cons = new Gun(0, 0, 5, 5, Type.WATER, 5, null, null);
		Projectile bullet = cons.createProjectile(1, 1);

		assertEquals(8, bullet.getY(), 0);
		assertEquals(16, bullet.getX(), 0);

		bullet.tick();
		assertEquals(5.995854917833179, bullet.getY(), 0);
		assertEquals(12.538294858075496, bullet.getX(), 0);
	}


	/**
	 * External testing
	 */

	@Test
	public void createConsumable() {
		Consumable cons = new Consumable(0, 0, 5, 5, "Lives, 2", null);
	}

	@Test
	public void createStationaryEntity() {
		StationaryEntity sEntity = new StationaryEntity(0, 0, 5, 5, null);
	}


	@Test
	public void movePlayerByXY(){
		Player player = new Player(0, 0, 5, 5, null);
		assertTrue(player.moveBy(5,5));
		assertEquals(10, player.getX(),0);
		assertEquals(10, player.getY(),0);
	}

	@Test
	public void movePlayerToXY(){
		Player player = new Player(0, 0, 5, 5, null);
		assertTrue(player.moveTo(20,100));
		assertEquals(20.0f, player.getX(),0);
		assertEquals(100.0f, player.getY(),0);
	}

}
