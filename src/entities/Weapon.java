package entities;


/**
 * Abstract class for all the different types of weapons
 * @author Nick Lauder
 *
 */
public abstract class Weapon extends Pickupable implements Cloneable{
	private int baseDamage;


	/**
	 * Creates a new weapon with a given base damage
	 * @param damage
	 * 		the int base damage to use
	 */
	protected Weapon(int damage) {
		this.baseDamage = damage;
	}


	/**
	 * Deals damage to a given entity
	 * @param victim
	 * 		the entity to attack
	 * @return
	 * 		true if damage dealt, else false
	 */
	public boolean attack(Entity victim) {
		return victim.hit();
	}


	/**
	 * Returns the amount of damage to give a given entity
	 * @param victim
	 * 		the entity to check
	 * @return
	 * 		the int value of damage it would give
	 */
	private int getDamage(Entity victim) {
		return baseDamage * (int) getModifier(victim);

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
		float modifier = 1;
		int i;

		for (i = 0; i < Type.values().length; i++) {
			if (type == Type.values()[i]) {
				break;
			}
		}

		try {
			if (Type.values()[i-1] == victim.type) {
				modifier = 0.5f;
			} else if (Type.values()[i+1] == victim.type) {
				modifier = 2;
			}
		} catch (IndexOutOfBoundsException e) {
			if (i == 0) {
				if (Type.values()[Type.values().length-1] == victim.type) {
					modifier = 0.5f;
				}
			} else if (i == Type.values().length-1) {
				if (Type.values()[0] == victim.type) {
					modifier = 2;
				}
			}
		}

		return modifier;

	}


	/**
	 * Gets the base damage (without modifier) of the weapon
	 * @return
	 * 		the int value of the damage
	 */
	protected int getBaseDamage() {
		return baseDamage;
	}


	@Override
	protected Weapon clone() {
		if (this instanceof MeleeWeapon) {
			return new MeleeWeapon(name, x, y, type, baseDamage);
		} else if (this instanceof Projectile) {
			return new Projectile(name, x, y, type, baseDamage);
		} else {
			return null;
		}
	}

}
