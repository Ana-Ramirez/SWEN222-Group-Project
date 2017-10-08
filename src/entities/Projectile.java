package entities;

import javafx.scene.image.Image;

/**
 * Class that handles the specific projectile fired by a gun
 * @author Nick Lauder
 *
 */
public class Projectile extends Weapon {

	protected Projectile(String name, float x, float y, int width, int height, int damage, Image img) {
		super(name, x, y, width, height, null, damage);
		setImage(img);
		// TODO Auto-generated constructor stub
	}
	

	@Override
	protected Projectile clone() {
		return new Projectile(getName(), x, y, getWidth(), getHeight(), getBaseDamage(), getImage());
	}

}
