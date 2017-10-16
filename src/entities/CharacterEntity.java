package entities;

import interfaces.EntityType;
import interfaces.Pickupable;
import interfaces.Character;

/**
 * Abstract class of all different character types
 * @author laudernich1
 *
 */
public abstract class CharacterEntity extends MovableEntity implements Character {
	private static final long serialVersionUID = 4776917612212761920L;
	protected int lives;
	private Pickupable hand;


	protected CharacterEntity(double x, double y, double width, double height, EntityType type, int lives) {
		super(x, y, width, height, type);
		this.lives = lives;
		hand = null;
	}

	@Override
	protected boolean hit(int damage) {
		if (lives > 0) {
			lives-=damage;
			return true;
		}
		return false;
	}

	public boolean isAlive() {
		return lives > 0;
	}

	public int getLives() {
		return lives;
	}

	public Pickupable getHand() {
		return hand;
	}
	
	/**
	 * Sets the characters hand
	 * @param item
	 * 		the item to put into the characters hand
	 */
	protected void setHand(Pickupable item) {
		hand = item;
		
	}

}
