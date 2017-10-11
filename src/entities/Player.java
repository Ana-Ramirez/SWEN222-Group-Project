package entities;

import javafx.geometry.BoundingBox;
import resources.ImgResources;

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
	public Player(double x, double y, int width, int height, ImgResources img) {
		super("Tim", x, y, width, height, null, 3);
		setImage(img);
		inventory = new Pickupable[3];
		this.speed = 2;
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

	/**
	 * Uses the consumable item in the players hand, returns false if failed, it not consumable
	 * @return
	 * 		True if the consumable was successfully used
	 */
	public boolean use() {
		if (getHand() instanceof Consumable) {
			boolean success = parseCommand(((Consumable)getHand()).use());
			if (success) {inventory[itemSelected] = null;}
			return success;
		} else {
			return false;
		}
	}

	private boolean parseCommand(String command) {
		if (command == null) {
			return false;
		}
		String[] actionCommand = command.split("[, ]+");
		if (actionCommand[0].equals("Lives")) {
			lives += (int) Float.parseFloat(actionCommand[1]);
		} else if (actionCommand[0].equals("Ammo")) {
			for (int i = 0; i < inventory.length; i++) {
				if (inventory[i] instanceof Gun) {
					((Gun) inventory[i]).resupply((int) Float.parseFloat(actionCommand[1]));
					return true;
				}
			}
			return false;
		} else {
			throw new UnsupportedOperationException("The player does not support this command");
		}
		return true;
	}


	/**
	 * Returns the object currently in the players hand
	 * @return
	 * 		the pickupable object in the hand
	 */
	public Pickupable getHand() {
		//TODO: proper comments

		return inventory[itemSelected];
	}


	/**
	 * Returns an array of the players inventory
	 * @return
	 * 		an array of pickupable items
	 */
	public Pickupable[] getInventory() {
		//TODO: proper comments
		return inventory;
	}


	/**
	 * Adds a given item to the players inventory
	 * @param item
	 * 		the item to add
	 * @return
	 * 		the item replaced in inventory
	 */
	public Pickupable pickup(Pickupable item) {
		//TODO: proper comments
		Pickupable holder = drop();
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

	/**
	 * Returns a large bounding box used for the attack radius
	 * @return
	 */
	public BoundingBox getExtendedBoundingBox() {
		return new BoundingBox(getX()-5, getY()-5, getWidth()+10, getHeight()+10);
	}


	@Override
	public void tick() {
		for (int i = 0; i < inventory.length; i++) {
			if (inventory[i] != null) {
				inventory[i].moveTo(getX(), getY());
			}
		}
	}
}
