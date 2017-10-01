package entities;


/**
 * Abstract class for an entity that cannot move/be moved
 * @author Nick Lauder
 *
 */
public class StationaryEntity extends Entity {

	public StationaryEntity(String name, int x, int y, int width, int height, Type type) {
		super(name, x, y, width, height, type);
	}

	@Override
	protected boolean hit() {
		return false;
	}

	@Override
	protected boolean hit(int damage) {
		return false;
	}
}
