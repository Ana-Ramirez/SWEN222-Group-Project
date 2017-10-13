package entities;

import javafx.geometry.BoundingBox;
import resources.ImgResources;

/**
 * Class that handles the specific projectile fired by a gun
 * @author Nick Lauder
 *
 */
public class Projectile extends Weapon {
	private static final long serialVersionUID = 1620202643777534005L;
	private double angle;

	protected Projectile(BoundingBox box, int damage, ImgResources img, double targetX, double targetY) {
		super(box.getMinX()+16, box.getMinY()+8, (int)box.getWidth(), (int)box.getHeight(), null, damage);
		setImage(img);
		angle = Math.toDegrees(Math.atan2(targetY-(getY()+box.getHeight()/2d), targetX-(getX()+box.getWidth()/2d)));
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
