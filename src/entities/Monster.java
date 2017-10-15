package entities;

import java.awt.geom.Rectangle2D;

import interfaces.StratergyPattern;
import resources.ImgResources;

/**
 * The monster object that can move around the level and attack the player
 * @author Nick Lauder
 *
 */
public class Monster extends Character {
	private static final long serialVersionUID = -4064250638229615542L;
	private Weapon weapon;
	private StratergyPattern pattern;
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
	public Monster(Rectangle2D.Double box, Type type, Weapon weapon, ImgResources img, StratergyPattern stratergy) {
		super(box.getMinX(), box.getMinY(), box.getWidth(), box.getHeight(), type, 100);
		setImage(img);
		this.weapon = weapon;
		this.pattern = stratergy;
	}


	/**
	 * Gets the monsters weapon
	 * @return
	 * 		the weapon object
	 */
	public Weapon getWeapon() {
		return weapon;
	}

	
	@Override
	public void tick() {
		pattern.tick(this);
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
			return weapon.attack(victim);
		} else {
			return false;
		}
	}
}
