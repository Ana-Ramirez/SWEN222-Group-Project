package entities;

/**
 * Abstract class for an entity that cannot move/be moved
 * @author Nick Lauder
 *
 */
public abstract class StationaryEntity extends Entity {
	
	/**
	 * Damages a stationary entity. Always returns 
	 * false as a stationary entity cannot take damage
	 * @return
	 * 		always returns false
	 */
	public boolean receiveHit(Weapon weapon) {
		return false;
	}

}
