package entities;


//***** Design Discussion *********
//How to manage a weapon firing something



/**
 * Projectile object, handles a weapon that
 * fires projectiles in a given direction
 * @author Nick Lauder
 *
 */
public class Gun extends Weapon {

	/**
	 * Creates a new projectile
	 * @param name
	 * 		the string name to use
	 * @param x
	 * 		the float x coordinate to use
	 * @param y
	 * 		the float y coordinate to use
	 * @param width
	 * 		the int width to use
	 * @param height
	 * 		the int height to use
	 * @param type
	 * 		the type to use
	 * @param damage
	 * 		the base damage to use
	 */
	public Gun(String name, float x, float y, int width, int height, Type type, int damage) {
		super(name, x, y, width, height, type, damage);
		// TODO Auto-generated constructor stub
	}

}
