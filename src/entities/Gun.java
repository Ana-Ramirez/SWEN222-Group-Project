package entities;

import java.awt.geom.Rectangle2D;

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
	private static final long serialVersionUID = 1557911205592499799L;
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
	public Gun(Rectangle2D.Double box, Type type, int damage, int ammoCount, ImgResources img, ImgResources ammoImg) {
		super(box.getMinX(), box.getMinY(), box.getWidth(), box.getHeight(), type, damage);
		this.damage = damage;
		setImage(img);
		this.ammoImg = ammoImg;
		this.ammoCount = ammoCount;
	}


	/**
	 *
	 * @param x
	 * @param y
	 * @return
	 */
	public Projectile createProjectile(double targetX, double targetY) {
		if (ammoCount != 0) {
			ammoCount--;
			return new Projectile(getOwner(), new Rectangle2D.Double(getX(), getY(), 8, 8), damage, ammoImg, targetX, targetY);
		} else {
			return null;
		}
	}
	
	
	public Projectile createProjectile(ImgResources ammoImg, double targetX, double targetY) {
		if (ammoCount != 0) {
			ammoCount--;
			return new Projectile(getOwner(), new Rectangle2D.Double(getX(), getY(), ammoImg.getWidth(), ammoImg.getHeight()), damage, ammoImg, targetX, targetY);
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
	

	@Override
	public String getInfo() {
		return "Gun - Ammo: " + ammoCount;
	}

}
