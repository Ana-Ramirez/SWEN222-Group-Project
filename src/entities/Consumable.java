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
	 * 		the string name to use
	 * @param x
	 * 		the float x coordinate to use
	 * @param y
	 * 		the float y to use
	 */
	public Consumable(String name, float x, float y) {
		super.name = name;
		super.x = x;
		super.y = y;
	}
}
