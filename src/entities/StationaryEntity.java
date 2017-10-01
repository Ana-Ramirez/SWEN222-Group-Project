package entities;


/**
 * Abstract class for an entity that cannot move/be moved
 * @author Nick Lauder
 *
 */
public abstract class StationaryEntity extends Entity {

	@Override
	protected boolean hit() {
		return false;
	}
}
