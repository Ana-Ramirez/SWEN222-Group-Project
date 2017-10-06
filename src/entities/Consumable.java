package entities;

import javafx.scene.image.Image;

/**
 * Entity type that can be consumed by a player, gives perk after being consumed
 * @author Nick Lauder
 *
 */
public class Consumable extends Pickupable {
	private String action;
	private int uses;

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
	public Consumable(String name, float x, float y, int width, int height, String action, int uses, Image img) {
		super(name, x, y, width, height, null);
		setImage(img);
		this.action = action;
		this.uses = uses;
	}


	protected String use() {
		if (uses-- > 0) {
			return action;
		} else {
			return null;
		}
	}
	
	/**
	 * Returns whether or not the consumable can still be used
	 * @return
	 * 		true if can use
	 */
	public boolean canUse() {
		return uses > 0;
	}

}
