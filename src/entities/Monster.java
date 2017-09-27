package entities;

import ai.Enemies;

/**
 * The monster object that can move around the level and attack the player
 * @author Nick Lauder
 *
 */
public class Monster extends Character {
	private Weapon weapon;
	private Enemies pattern;


	/**
	 * Creates a new monster
	 * @param name
	 * 		the string name to use
	 * @param x
	 * 		the float x coordinate to use
	 * @param y
	 * 		the float y coordinate to use
	 * @param type
	 * 		the type of the monster
	 * @param weapon
	 * 		the monsters weapon
	 */
	public Monster(String name, float x, float y, Type type, Weapon weapon) {
		super(100);
		this.name = name;
		this.x = x;
		this.y = y;
		this.type = type;
		this.weapon = weapon;
	}


	/**
	 * Gets the monsters weapon
	 * @return
	 * 		a copy of the weapon object
	 */
	public Weapon getWeapon() {
		return weapon.clone();
	}

	/**
	 * Sets the starergy pattern for the monster
	 * @param pattern
	 * 		the pattern to use
	 */
	public void setStratergy(Enemies pattern) {
		this.pattern = pattern;
	}

	/**
	 * Executes a tick for the monster, updating it's location
	 */
	public void tick() {
		pattern.tick(this);
	}
}
