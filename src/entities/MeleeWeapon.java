package entities;

import java.awt.geom.Rectangle2D;

import interfaces.EntityType;
import resources.ImgResources;

/**
 * The object of a melee weapon. Which can be used to attack other entities
 * @author laudernich1
 *
 */
public class MeleeWeapon extends WeaponEntity {
	private static final long serialVersionUID = -7531191138324291062L;

	/**
	 * Create a new weapon
	 * @param box
	 * 		a rectangle with the coordinates and size to use
	 * @param type
	 * 		the type to use
	 * @param damage
	 * 		the int amount of damage to use
	 * @param img
	 * 		the img reference to use
	 */
	public MeleeWeapon(Rectangle2D.Double box, EntityType type, int damage, ImgResources img) {
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
