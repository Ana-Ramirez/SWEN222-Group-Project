package entities;


/**
 * Abstract class for all the different types of weapons
 * @author Nick Lauder
 *
 */
public abstract class Weapon extends Pickupable{
	private int baseDamage;

	public Weapon(String name, float x, float y, int width, int height, Type type, int damage) {
		super(name, x, y, width, height, type);
		this.baseDamage = damage;
	}

	protected String use() {
		return "";
		//TODO: implement properly
	}

	/**
	 * Deals damage to a given entity
	 * @param victim
	 * 		the entity to attack
	 * @return
	 * 		true if damage dealt, else false
	 */
	public boolean attack(Entity victim) {
		return victim.hit(getDamage(victim));
		
	}

	/**
	 * Returns the amount of damage to give a given entity
	 * @param victim
	 * 		the entity to check
	 * @return
	 * 		the int value of damage it would give
	 */
	private int getDamage(Entity victim) {
		return  (int) (baseDamage * getModifier(victim));
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
		
		if (victim.getType() == null) {
			return 1f/baseDamage;
		} else if (victim.getType() == getType()) {
			return 1;
		}
		
		switch (victim.getType()) {
		case EARTH:
			modifier = (getType() == Type.WATER) ? 0.5f : 2;
			break;
		case FIRE:
			modifier = (getType() == Type.EARTH) ? 0.5f : 2;
			break;
		case WATER:
			modifier = (getType() == Type.FIRE) ? 0.5f : 2;
			break;
		}
	
		return modifier;
		
	}

	/**
	 * Gets the base damage (without modifier) of the weapon
	 * @return
	 * 		the int value of the damage
	 */
	public int getBaseDamage() {
		return baseDamage;
	}
}
