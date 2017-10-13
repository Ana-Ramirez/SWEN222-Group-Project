package entities;

import resources.ImgResources;

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
	private ImgResources ammoImg;
	private int ammoCount;


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
	public Gun(double x, double y, int width, int height, Type type, int damage, ImgResources img, ImgResources ammoImg) {
		super(x, y, width, height, type, damage);
		setImage(img);
		this.ammoImg = ammoImg;
		ammoCount = 20;
	}


	/**
	 *
	 * @param x
	 * @param y
	 * @return
	 */
	public Projectile createProjectile(double x, double y) {
		if (ammoCount > 0) {
			ammoCount--;
			return new Projectile(getX(), getY(), 8, 8, damage, ammoImg, x, y);
		} else {
			return null;
		}
	}
	
	
	public int getAmmoCount() {
		return ammoCount;
	}
	
	public void resupply(int amount) {
		ammoCount += amount;
	}
	
	@Override
	public void tick() {
		// Does nothing on tick
	}

}
