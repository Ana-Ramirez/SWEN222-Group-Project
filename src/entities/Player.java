package entities;

import java.awt.geom.Rectangle2D;

import resources.ImgResources;

/**
 * Player object, the entity that is controlled by the user
 * @author laudernich1
 *
 */
public class Player extends CharacterEntity {
	private static final long serialVersionUID = 1364726060443658475L;
	private Pickupable[] backpack;


	/**
	 * Creates a new playable character
	 * @param box
	 * 		the rectangle that provides the coordinates and size
	 * @param img
	 * 		the initial image for this player to use
	 */
	public Player(Rectangle2D.Double box, ImgResources img) {
		super(box.getMinX(), box.getMinY(), box.getWidth(), box.getHeight(), null, 3);
		setImage(img);
		backpack = new Pickupable[2];
		this.speed = 2;
	}


	/**
	 * Changes the players help item to
	 * the given item in the inventory
	 * @param i
	 * 		the int for the inventory slot to because the hand
	 */
	public void selectItem(int i) {
		if (i >= 0 && i < backpack.length) {
			if (getHand() == null) {
				setHand(backpack[i]);
				backpack[i] = null;
			} else {
				Pickupable holder = getHand();
				setHand(backpack[i]);
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
			if (success) {setHand(null);}
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
			return true;
		} else if (actionCommand[0].equals("Ammo")) {
			for (int i = 0; i < backpack.length; i++) {
				if (backpack[i] instanceof Gun) {
					((Gun) backpack[i]).resupply((int) Float.parseFloat(actionCommand[1]));
					return true;
				}
			}
		} 
		return false;
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
		if (item instanceof Weapon) {
			((Weapon) item).setOwner(this);
		}
		if (getHand() == null) {
			setHand(item);
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
				setHand(item);
				return holder;
			}
		}
		return null;
	}


	/**
	 * Drops an item
	 * @return
	 * 		the item removed from the inventory
	 */
	public Pickupable drop() {
		Pickupable holder = getHand();
		setHand(null);
		if (holder instanceof Weapon) {
			((Weapon) holder).setOwner(null);
		}
		return holder;
	}

	/**
	 * Returns a large bounding box used for the attack radius
	 * @return
	 * 		returns a larger bounding box
	 */
	public Rectangle2D.Double getExtendedBoundingBox() {
		return new Rectangle2D.Double(getX()-30, getY()-30, getWidth()+60, getHeight()+60);
	}


	@Override
	public void tick() {
		for (int i = 0; i < backpack.length; i++) {
			if (backpack[i] != null) {
				backpack[i].moveTo(getX(), getY());
			}
		}
		if (getHand() != null) {
			getHand().moveTo(getX(), getY());
		}
	}
}
