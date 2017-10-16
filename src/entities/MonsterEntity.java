package entities;

import java.awt.geom.Rectangle2D;

import interfaces.StrategyPattern;
import interfaces.Weapon;
import interfaces.Entity;
import interfaces.EntityType;
import interfaces.Monster;
import resources.ImgResources;

/**
 * The monster object that can move around the level and attack the player
 * @author laudernich1
 *
 */
public class MonsterEntity extends CharacterEntity implements Monster {
	private static final long serialVersionUID = -4064250638229615542L;
	private StrategyPattern pattern;
	private int oldTick = 0;
	private int fullLives;


	/**
	 * Creates a new Monster
	 * @param box
	 * 		the the rectangle that provides the coordinates and size
	 * @param health
	 * 		the base health to use
	 * @param type
	 * 		the type to use
	 * @param weapon
	 * 		the weapon to use
	 * @param img
	 * 		the image reference to use
	 * @param strategy
	 * 		the ai pattern to use
	 */
	public MonsterEntity(Rectangle2D.Double box, int health, EntityType type, Weapon weapon, ImgResources img, StrategyPattern strategy) {
		super(box.getMinX(), box.getMinY(), box.getWidth(), box.getHeight(), type, health);
		setImage(img);
		setHand(weapon);
		this.pattern = strategy;
		if (weapon != null) {
			weapon.setOwner(this);
		}
		fullLives = health;
	}

	
	@Override
	public void tick() {
		pattern.tick(this);
		if (getHand() != null) {
			getHand().moveTo(getX(), getY());
		}
	}

	public boolean attack(Entity victim, int tick) {
		if ((tick-oldTick) >= 120) {
			oldTick = tick;
			return ((WeaponEntity)getHand()).attack(victim);
		} else {
			return false;
		}
	}

	public int getFullLives() {
		return fullLives;
	}
}
