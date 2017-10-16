package interfaces;


import resources.ImgResources;

public interface Gun extends Weapon {
	/**
	 * Creates a new projectile to shoot
	 * @param targetX
	 * 		the target x double
	 * @param targetY
	 * 		the target y double
	 * @return
	 * 		the newly created projectile
	 */
	public Projectile createProjectile(double targetX, double targetY);
	
	
	/**
	 * Creates a new projectile to shoot, including specific image
	 * @param ammoImg
	 * 		the image reference to use with the projectile
	 * @param targetX
	 * 		the target x double
	 * @param targetY
	 * 		the target y double
	 * @return
	 * 		the newly created projectile
	 */
	
	public Projectile createProjectile(ImgResources ammoImg, double targetX, double targetY);
	
	
	public int getAmmoCount();
	
	public void resupply(int amount);
}
