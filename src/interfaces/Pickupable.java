package interfaces;

public interface Pickupable extends Moveable {
	
	
	/**
	 * Gets the general information of the item
	 * @return
	 * 		a string info description
	 */
	public abstract String getInfo();
}
