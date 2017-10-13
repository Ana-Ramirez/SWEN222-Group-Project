package entities;

import interfaces.Enemies;
import resources.ImgResources;

/**
 * The monster object that can move around the level and attack the player
 * @author Nick Lauder
 *
 */
public class Monster extends Character {
	private Weapon weapon;
	private Enemies pattern;


	/**
	 * Creates a new Monster
	 * @param name
	 * 		the string name to use
	 * @param x
	 * 		the float x to use
	 * @param y
	 * 		the float y to use
	 * @param width
	 * 		the int width to use
	 * @param height
	 * 		the int height to use
	 * @param type
	 * 		the type to use
	 * @param weapon
	 * 		the weapon to use
	 */
	public Monster(double x, double y, int width, int height, Type type, Weapon weapon, ImgResources img, Enemies stratergy) {
		super(x, y, width, height, type, 100);
		setImage(img);
		this.weapon = weapon;
		this.pattern = stratergy;
	}


	/**
	 * Gets the monsters weapon
	 * @return
	 * 		the weapon object
	 */
	public Weapon getWeapon() {
		return weapon;
	}

	/**
	 * Executes a tick for the monster, updating it's location
	 */
	@Override
	public void tick() {
		pattern.tick(this);
	}


	/**
	 * Initiates an attack onto another entitiy
	 * @param victim
	 * 		the entity to attack
	 * @return
	 * 		true if successful
	 */
	public boolean attack(Entities victim) {
		return weapon.attack(victim);
	}
}
