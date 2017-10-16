package entities;

import java.awt.geom.Rectangle2D;

import resources.ImgResources;

/**
 * Entity type that can be consumed by a player, gives perk after being consumed
 * @author laudernich1
 *
 */
public class Consumable extends Pickupable {
	private static final long serialVersionUID = -4391782330871728287L;
	private static final String[] commands = {"Lives", "Speed", "Ammo", "Key"};
	private String action;
	private int uses;

	/**
	 * Creates a new consumable object
	 * @param box
	 * 		the rectangle that provides the coordinates and size
	 * @param action
	 * 		the action of what the consumable actually odes
	 * @param img
	 * 		the image reference to use
	 */
	public Consumable(Rectangle2D.Double box, String action, ImgResources img) {
		super(box.getMinX(), box.getMinY(), box.getWidth(), box.getHeight(), null);
		setImage(img);
		checkAction(action);
		uses = 1;
	}

	private boolean checkAction(String action) {
		String[] actionCommand = action.split("[, ]+");
		if (actionCommand.length != 2) {
			return false;
		}
		try {
			double val = Double.parseDouble(actionCommand[1]);
			if (val < 0) {
				throw new NumberFormatException();
			}
		} catch (NumberFormatException e){
			return false;
		}
		boolean valid = false;
		for (int i = 0; i < commands.length; i++) {
			if (commands[i].equals(actionCommand[0])) {
				valid = true;
				break;
			}
		}
		if (!valid) {
			return false;
		}
		this.action = action;
		return valid;
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

	@Override
	public void tick() {
		// Does nothing on tick
	}

	@Override
	public String getInfo() {
		return action;
	}

}
