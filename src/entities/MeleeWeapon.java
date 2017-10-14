package entities;

import java.awt.geom.Rectangle2D;

import resources.ImgResources;

/**
 * The object of a melee weapon. Which can be used to attack other entities
 * @author Nick Lauder
 *
 */
public class MeleeWeapon extends Weapon {
	private static final long serialVersionUID = -7531191138324291062L;

	/**
	 * Create a new weapon
	 * @param name
	 * 		the string name to use
	 * @param x
	 * 		the float x coordinate to use
	 * @param y
	 * 		the float y coordinate to use
	 * @param type
	 * 		the type to use
	 * @param damage
	 * 		the int amount of damage to use
	 */
	public MeleeWeapon(Rectangle2D.Double box, Type type, int damage, ImgResources img) {
		super(box.getMinX(), box.getMinY(), box.getWidth(), box.getHeight(), type, damage);
		setImage(img);
	}

	@Override
	public void tick() {
		//Does nothing on tick
	}

	@Override
	public String getInfo() {
		return "Melee Weapon";
	}

}
