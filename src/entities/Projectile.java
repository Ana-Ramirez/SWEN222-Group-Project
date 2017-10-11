package entities;

import javafx.scene.image.Image;
import resources.ImgResources;

/**
 * Class that handles the specific projectile fired by a gun
 * @author Nick Lauder
 *
 */
public class Projectile extends Weapon {
	private double angle;
	private final double SPEED=1;

	protected Projectile(String name, double x, double y, int width, int height, int damage, ImgResources img, double targetX, double targetY) {
		super(name, x, y, width, height, null, damage);
		setImage(img);
		angle = Math.toDegrees(Math.atan2(targetY-(getY()+height/2), targetX-(getX()+width/2)));

	}

	@Override
	public void tick() {
		double theta = Math.toRadians(angle);
		double newX = SPEED*Math.cos(theta);
		double newY = SPEED*Math.sin(theta);
		moveBy(newX, newY);
	}
}
