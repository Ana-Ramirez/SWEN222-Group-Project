package entities;

import javafx.geometry.BoundingBox;
import resources.ImgResources;

/**
 * Player object, the entity that is controlled by the user
 * @author Nick Lauder
 *
 */
public class Player extends Character {
	private static final long serialVersionUID = 1364726060443658475L;
	private Pickupable[] backpack;
	private Pickupable hand;


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
	public Player(BoundingBox box, ImgResources img) {
		super(box.getMinX(), box.getMinY(), box.getWidth(), box.getHeight(), null, 3);
		setImage(img);
		hand = null;
		backpack = new Pickupable[2];
		this.speed = 2;
	}


	/**
	 * Changes the players help item to
	 * the given item in the inventory
	 * @return
	 * 		true if successful, else false
	 */
	public void selectItem(int i) {
		if (i >= 0 && i < backpack.length) {
			if (hand == null) {
				hand = backpack[i];
				backpack[i] = null;
			} else {
				Pickupable holder = hand;
				hand = backpack[i];
				backpack[i] = holder;
			}
		}
	}

	/**
	 * Uses the consumable item in the players hand, returns false if failed, it not consumable
	 * @return
	 * 		True if the consumable was successfully used
	 */
	public boolean use() {
		if (getHand() instanceof Consumable) {
			boolean success = parseCommand(((Consumable)getHand()).use());
			if (success) {hand = null;}
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
			for (int i = 0; i < backpack.length; i++) {
				if (backpack[i] instanceof Gun) {
					((Gun) backpack[i]).resupply((int) Float.parseFloat(actionCommand[1]));
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
		return hand;
	}


	/**
	 * Returns an array of the players inventory
	 * @return
	 * 		an array of pickupable items
	 */
	public Pickupable[] getBackpack() {
		return backpack;
	}


	/**
	 * Adds a given item to the players inventory
	 * @param item
	 * 		the item to add
	 * @return
	 * 		the item replaced in inventory
	 */
	public Pickupable pickup(Pickupable item) {
		if (hand == null) {
			hand = item;
		} else {
			boolean pickedUp = false;
			for (int i = 0; i < backpack.length; i++) {
				if (backpack[i] == null) {
					backpack[i] = item;
					pickedUp = true;
					break;
				}
			}
			if (!pickedUp) {
				Pickupable holder = drop();
				hand = item;
				return holder;
			}
		}
		return null;
	}


	/**
	 * Drops an item
	 * @param item
	 * 		the item to add
	 * @return
	 * 		the item removed from the inventory
	 */
	public Pickupable drop() {
		Pickupable holder = hand;
		hand = null;
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
		for (int i = 0; i < backpack.length; i++) {
			if (backpack[i] != null) {
				backpack[i].moveTo(getX(), getY());
			}
		}
		if (hand != null) {
			hand.moveTo(getX(), getY());
		}
	}
}
