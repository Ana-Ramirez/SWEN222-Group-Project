package entities;

import ai.Enemies;
import javafx.scene.image.Image;

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
	public Monster(String name, float x, float y, int width, int height, Type type, Weapon weapon, Image img) {
		super(name, x, y, width, height, type, 100);
		setImage(img);
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

	
	/**
	 * Initiates an attack onto another entitiy
	 * @param victim
	 * 		the entity to attack
	 * @return
	 * 		true if successful
	 */
	public boolean attack(Entity victim) {
		return weapon.attack(victim);
	}
}
