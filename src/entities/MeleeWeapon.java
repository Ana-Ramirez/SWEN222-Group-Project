package entities;

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
	public MeleeWeapon(String name, float x, float y, Type type, int damage) {
		super(damage);
		this.name = name;
		this.x = x;
		this.y = y;
		this.type = type;
	}


}
