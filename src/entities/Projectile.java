package entities;


//***** Design Discussion *********
//How to manage a weapon firing something



/**
 * Projectile object, handles a weapon that 
 * fires projectiles in a given direction
 * @author Nick Lauder
 *
 */
public class Projectile extends Weapon {

	
	/**
	 * Creates a new projectile
	 * @param name
	 * 		the string name to use
	 * @param x
	 * 		the starting float x coordinate
	 * @param y
	 * 		the starting float y coordinate
	 * @param type
	 * 		the type to use
	 * @param damage
	 * 		the base damage to use
	 */
	public Projectile(String name, float x, float y, Type type, int damage) {
		super(damage);
		// TODO Auto-generated constructor stub
	}

}
