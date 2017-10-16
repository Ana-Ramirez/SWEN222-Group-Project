package interfaces;


public interface Weapon extends Pickupable {
	/**
	 * Deals damage to a given entity
	 * @param victim
	 * 		the entity to attack
	 * @return
	 * 		true if damage dealt, else false
	 */
	public boolean attack(Entity victim);


	/**
	 * Gets the base damage (without modifier) of the weapon
	 * @return
	 * 		the int value of the damage
	 */
	public int getBaseDamage();
	
	
	/**
	 * Returns the character that owns the entity 
	 * @return
	 * 		The CharacterEntity object
	 */
	public Character getOwner();
	
	public void setOwner(Character c);
}

