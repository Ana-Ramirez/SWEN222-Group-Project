package entities;

import resources.ImgResources;

/**
 * Class that handles the specific projectile fired by a gun
 * @author Nick Lauder
 *
 */
public class Projectile extends Weapon {
	private double angle;

	protected Projectile(double x, double y, int width, int height, int damage, ImgResources img, double targetX, double targetY) {
		super(x+16, y+8, width, height, null, damage);
		setImage(img);
		angle = Math.toDegrees(Math.atan2(targetY-(getY()+height/2), targetX-(getX()+width/2)));
		this.speed = 2;
	}

	@Override
	public void tick() {
		double theta = Math.toRadians(angle);
		double newX = speed*Math.cos(theta);
		double newY = speed*Math.sin(theta);
		moveBy(newX, newY);
	}

	@Override
	public String getInfo() {
		return "Ammo";
	}
}
