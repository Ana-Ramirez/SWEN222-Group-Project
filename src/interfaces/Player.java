package interfaces;

import java.awt.geom.Rectangle2D;


public interface Player extends Character {


	/**
	 * Changes the players help item to
	 * the given item in the inventory
	 * @param i
	 * 		the int for the inventory slot to because the hand
	 */
	public void selectItem(int i);

	/**
	 * Uses the consumable item in the players hand, returns false if failed, it not consumable
	 * @return
	 * 		True if the consumable was successfully used
	 */
	public boolean use();


	/**
	 * Returns an array of the players inventory
	 * @return
	 * 		an array of pickupable items
	 */
	public Pickupable[] getBackpack();


	/**
	 * Adds a given item to the players inventory
	 * @param item
	 * 		the item to add
	 * @return
	 * 		the item replaced in inventory
	 */
	public Pickupable pickup(Pickupable item);


	/**
	 * Drops an item
	 * @return
	 * 		the item removed from the inventory
	 */
	public Pickupable drop();

	/**
	 * Returns a large bounding box used for the attack radius
	 * @return
	 * 		returns a larger bounding box
	 */
	public Rectangle2D.Double getExtendedBoundingBox();
}
