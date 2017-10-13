package entities;


/**
 * Abstract class for a pickupable object
 * @author Nick Lauder
 *
 */
public abstract class Pickupable extends MovableEntity {
	private static final long serialVersionUID = 1654057472702704263L;

	protected Pickupable(double x, double y, int width, int height, Type type) {
		super(x, y, width, height, type);
	}

	@Override
	protected boolean hit(int damage) {
		return false;
	}

	/**
	 * Gets the general information of the item
	 * @return
	 * 		a string info description
	 */
	public abstract String getInfo();
}
