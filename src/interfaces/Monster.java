package interfaces;


public interface Monster extends Character {

	/**
	 * Initiates an attack onto another entitiy
	 * @param victim
	 * 		the entity to attack
	 * @param tick
	 * 		the int tick number
	 * @return
	 * 		true if successful
	 */
	public boolean attack(Entity victim, int tick);
	
	/**
	 * Returns the amount lives this monster has at full health
	 * @return
	 * 		int number of max lives
	 */
	public int getFullLives();
}
