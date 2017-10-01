package entities;

/**
 * Player object, the entity that is controlled by the user
 * @author Nick Lauder
 *
 */
public class Player extends Character {
	private Pickupable[] inventory;
	private Pickupable hand;


	/**
	 * Creates a new playable character
	 * @param x
	 * 		the float of the starting x coordinate
	 * @param y
	 * 		the float of the starting y coordinate
	 */
	public Player(float x, float y, int width, int height) {
		super("Tim", x, y, width, height, null, 3);
		inventory = new Pickupable[2];
		hand = null;
	}


	/**
	 * Changes the players help item to
	 * the next item in the inventory
	 * @return
	 * 		true if successful, else false
	 */
	public boolean changeHand() {
		Pickupable holder = hand;
		hand = inventory[0];
		inventory[0] = inventory[1];
		inventory[1] = holder;
		return true;
		//TODO proper success checking
	}


	/**
	 * Adds a given item to the players inventory
	 * @param item
	 * 		the item to add
	 * @return
	 * 		true only if space is available in the inventory
	 */
	public boolean pickup(Pickupable item) {
		if (hand == null) {
			hand = item;
		} else if (inventory[0] == null) {
			inventory[0] = item;
		} else if (inventory[1] == null) {
			inventory[1] = item;
		} else {
			return false;
		}
		return true;
	}
}
