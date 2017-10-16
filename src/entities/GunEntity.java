package entities;

import java.awt.geom.Rectangle2D;

import interfaces.Entity;
import interfaces.EntityType;
import interfaces.Gun;
import interfaces.Projectile;
import resources.ImgResources;

//***** Design Discussion *********
//How to manage a weapon firing something



/**
 * Gun object, handles a weapon that
 * fires projectiles in a given direction
 * @author laudernich1
 *
 */
public class GunEntity extends WeaponEntity implements Gun {
	private static final long serialVersionUID = 1557911205592499799L;
	private int damage;
	private ImgResources ammoImg;
	private int ammoCount;

	/**
	 * Creates a new projectile
	 * @param box
	 * 		a rectangle with the coordinates and size 
	 * @param type
	 * 		the type to use
	 * @param damage
	 * 		the base damage to use
	 * @param ammoCount
	 * 		the initial ammo count to use
	 * @param img
	 *		the img reference to use
	 * @param ammoImg
	 * 		the default projectile image to use
	 */
	public GunEntity(Rectangle2D.Double box, EntityType type, int damage, int ammoCount, ImgResources img, ImgResources ammoImg) {
		super(box.getMinX(), box.getMinY(), box.getWidth(), box.getHeight(), type, damage);
		this.damage = damage;
		setImage(img);
		this.ammoImg = ammoImg;
		this.ammoCount = ammoCount;
	}

	public Projectile createProjectile(double targetX, double targetY) {
		if (ammoCount != 0) {
			ammoCount--;
			return new ProjectileEntity(getOwner(), new Rectangle2D.Double(getX(), getY(), 8, 8), damage, ammoImg, targetX, targetY, getType());
		} else {
			return null;
		}
	}
	
	public Projectile createProjectile(ImgResources ammoImg, double targetX, double targetY) {
		if (ammoCount != 0) {
			ammoCount--;
			return new ProjectileEntity(getOwner(), new Rectangle2D.Double(getX(), getY(), ammoImg.getWidth(), ammoImg.getHeight()), damage, ammoImg, targetX, targetY, getType());
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
