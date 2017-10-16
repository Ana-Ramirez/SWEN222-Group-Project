package entities;

import interfaces.Entity;
import interfaces.EntityType;
import interfaces.Weapon;
import interfaces.Character;

/**
 * Abstract class for all the different types of weapons
 * @author laudernich1
 *
 */
public abstract class WeaponEntity extends PickupableEntity implements Weapon  {
	private static final long serialVersionUID = -3336903366847446135L;
	private int baseDamage;
	private Character owner;


	WeaponEntity(double x, double y, double width, double height, EntityType type, int damage) {
		super(x, y, width, height, type);
		this.baseDamage = damage;
	}

	public boolean attack(Entity victim) {
		return ((Entities)victim).hit(getDamage(victim));
		
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
			modifier = (getType() == EntityType.WATER) ? 0.5f : 2;
			break;
		case FIRE:
			modifier = (getType() == EntityType.EARTH) ? 0.5f : 2;
			break;
		case WATER:
			modifier = (getType() == EntityType.FIRE) ? 0.5f : 2;
			break;
		}
	
		return modifier;
		
	}

	public int getBaseDamage() {
		return baseDamage;
	}
	
	public void setOwner(Character c) {
		owner = c;
	}

	public Character getOwner() {
		return owner;
	}
}
