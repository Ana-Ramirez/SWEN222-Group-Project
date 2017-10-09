package entities;

import javafx.scene.image.Image;

//***** Design Discussion *********
//How to manage a weapon firing something



/**
 * Gun object, handles a weapon that
 * fires projectiles in a given direction
 * @author Nick Lauder
 *
 */
public class Gun extends Weapon {
	private int damage;
	private Image ammoImg;
	

	/**
	 * Creates a new projectile
	 * @param name
	 * 		the string name to use
	 * @param x
	 * 		the float x coordinate to use
	 * @param y
	 * 		the float y coordinate to use
	 * @param width
	 * 		the int width to use
	 * @param height
	 * 		the int height to use
	 * @param type
	 * 		the type to use
	 * @param damage
	 * 		the base damage to use
	 */
	public Gun(String name, double x, double y, int width, int height, Type type, int damage, Image img, Image ammoImg) {
		super(name, x, y, width, height, type, damage);
		setImage(img);
	}

	
	/**
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public Projectile createProjectile(double x, double y) {
		return new Projectile(getName() + "Ammo", getX(), getY(), 5, 5, damage, ammoImg, x, y);
	}


	@Override
	public void tick() {
		// Does nothing on tick
		
	}

}
