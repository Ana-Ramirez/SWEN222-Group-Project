package entities;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * Abstract class for all the different types of weapons
 * @author Nick Lauder
 *
 */
public abstract class Weapon extends Pickupable {
	private int damage;
	
	/**
	 * Creates a new weapon with a given base damage
	 * @param damage
	 * 		the int base damage to use
	 */
	protected Weapon(int damage) {
		this.damage = damage;
	}
	
	/**
	 * Deals damage to a given entity
	 * @param victim
	 * 		the entity to attack
	 * @return
	 * 		true if damage dealt, else false
	 */
	public boolean attack(Entity victim) {
		throw new NotImplementedException();
	}
	
	/**
	 * Returns the amount of damage to give a given entity
	 * @param victim
	 * 		the entity to check
	 * @return
	 * 		the int value of damage it would give
	 */
	private int getDamage(Entity victim) {
		throw new NotImplementedException();
	}
	
	/**
	 * Gets the modifier depending on the type of the 
	 * weapon compared to the type of the entity
	 * @param victim
	 * 		the entity to check
	 * @return
	 * 		the float value of the modifier
	 */
	private float getModifier(Entity victim) {
		throw new NotImplementedException();
	}
	
	/**
	 * Gets the base damage (without modifier) of the weapon
	 * @return
	 * 		the int value of the damage
	 */
	protected int getBaseDamage() {
		return damage;
	}

}
