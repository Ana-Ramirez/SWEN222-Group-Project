package entities;

import javafx.scene.image.Image;

/**
 * The object of a melee weapon. Which can be used to attack other entities
 * @author Nick Lauder
 *
 */
public class MeleeWeapon extends Weapon {

	/**
	 * Create a new weapon
	 * @param name
	 * 		the string name to use
	 * @param x
	 * 		the float x coordinate to use
	 * @param y
	 * 		the float y coordinate to use
	 * @param type
	 * 		the type to use
	 * @param damage
	 * 		the int amount of damage to use
	 */
	public MeleeWeapon(String name, float x, float y, int width, int height, Type type, int damage, Image img) {
		super(name, x, y, width, height, type, damage);
		setImage(img);
	}


}
