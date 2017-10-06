package entities;

import javafx.scene.image.Image;

/**
 * Player object, the entity that is controlled by the user
 * @author Nick Lauder
 *
 */
public class Player extends Character {
	private Pickupable[] inventory;
	private int itemSelected;


	/**
	 * Creates a new playable character
	 * @param x
	 * 		the float x to use
	 * @param y
	 * 		the float y to use
	 * @param width
	 * 		the int width to use
	 * @param height
	 * 		the int height to use
	 */
	public Player(float x, float y, int width, int height, Image img) {
		super("Tim", x, y, width, height, null, 3);
		setImage(img);
		inventory = new Pickupable[3];
	}


	/**
	 * Changes the players help item to
	 * the given item in the inventory
	 * @return
	 * 		true if successful, else false
	 */
	public boolean selectItem(int i) {
		if (i < 0 || i > 2) {
			return false;
		}
		itemSelected = i;
		return true;
		//TODO proper success checking
	}


	public Pickupable getHand() {
		return inventory[itemSelected];
	}
	
	
	public Pickupable[] getInventory() {
		return inventory;
	}


	/**
	 * Adds a given item to the players inventory
	 * @param item
	 * 		the item to add
	 * @return
	 * 		true only if space is available in the inventory
	 */
	public Pickupable pickup(Pickupable item) {
		//TODO: proper comments
		Pickupable holder = null;
		if (inventory[itemSelected] != null) {
			holder = inventory[itemSelected];
		}
		inventory[itemSelected] = item;
		return holder;

	}


	/**
	 * Drops an item
	 * @param item
	 * 		the item to add
	 * @return
	 * 		true only if space is available in the inventory
	 */
	public Pickupable drop() {
		//TODO:
		Pickupable holder = inventory[itemSelected];
		inventory[itemSelected] = null;
		return holder;
	}
}
