package interfaces;

public interface Consumable extends Pickupable {
	/**
	 * Returns whether or not the consumable can still be used
	 * @return
	 * 		true if can use
	 */
	public boolean canUse();
}
