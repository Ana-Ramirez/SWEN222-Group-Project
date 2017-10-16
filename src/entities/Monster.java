package entities;

import java.awt.geom.Rectangle2D;

import interfaces.StrategyPattern;
import interfaces.EntityType;
import resources.ImgResources;

/**
 * The monster object that can move around the level and attack the player
 * @author laudernich1
 *
 */
public class Monster extends CharacterEntity {
	private static final long serialVersionUID = -4064250638229615542L;
	private StrategyPattern pattern;
	private int oldTick = 0;


	/**
	 * Creates a new Monster
	 * @param name
	 * 		the string name to use
	 * @param x
	 * 		the float x to use
	 * @param y
	 * 		the float y to use
	 * @param width
	 * 		the int width to use
	 * @param height
	 * 		the int height to use
	 * @param type
	 * 		the type to use
	 * @param weapon
	 * 		the weapon to use
	 */
	public Monster(Rectangle2D.Double box, int health, EntityType type, Weapon weapon, ImgResources img, StrategyPattern strategy) {
		super(box.getMinX(), box.getMinY(), box.getWidth(), box.getHeight(), type, health);
		setImage(img);
		setHand(weapon);
		this.pattern = strategy;
		if (weapon != null) {
			weapon.setOwner(this);
		}
	}

	
	@Override
	public void tick() {
		pattern.tick(this);
		if (getHand() != null) {
			getHand().moveTo(getX(), getY());
		}
	}


	/**
	 * Initiates an attack onto another entitiy
	 * @param victim
	 * 		the entity to attack
	 * @return
	 * 		true if successful
	 */
	public boolean attack(Entities victim, int tick) {
		if ((tick-oldTick) >= 120) {
			oldTick = tick;
			return ((Weapon)getHand()).attack(victim);
		} else {
			return false;
		}
	}
}
