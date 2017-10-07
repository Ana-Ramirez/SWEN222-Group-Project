package entities;

import javafx.scene.image.Image;

/**
 * Entity type that can be consumed by a player, gives perk after being consumed
 * @author Nick Lauder
 *
 */
public class Consumable extends Pickupable {
	private final String[] commands = {"Lives"};
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
	public Consumable(String name, float x, float y, int width, int height, String action, Image img) {
		super(name, x, y, width, height, null);
		setImage(img);
		checkAction(action);
		uses = 1;
	}
	
	private boolean checkAction(String action) {
		String[] actionCommand = action.split("[, ]+");
		if (actionCommand.length != 2) {
			throw new IllegalArgumentException("Action command incorrect argument number");
		}
		try {
			Float.parseFloat(actionCommand[1]);
		} catch (NumberFormatException e){
			throw new IllegalArgumentException("Action command value in not valid");
		}
		boolean valid = false;
		for (int i = 0; i < commands.length; i++) {
			if (commands[i].equals(actionCommand[0])) {
				valid = true;
				break;
			}
		}
		if (!valid) {
			throw new IllegalArgumentException("Action command is not a valid command");
		}
		this.action = action;
		return true;
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
