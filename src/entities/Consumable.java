package entities;

/**
 * Entity type that can be consumed by a player, gives perk after being consumed
 * @author Nick Lauder
 *
 */
public class Consumable extends Pickupable {

	/**
	 * Creates a new consumable object
	 * @param name
	 * 		the name to use
	 * @param x
	 * 		the float x coordinate
	 * @param y
	 * 		the float y coordinate
	 * @param width
	 * 		the int width to use
	 * @param height
	 * 		the int height to use
	 * @param type
	 * 		the type to use
	 */
	public Consumable(String name, float x, float y, int width, int height) {
		super(name, x, y, width, height, null);
	}
}
