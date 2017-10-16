package entities;

import java.awt.geom.Rectangle2D;

import interfaces.EntityType;
import resources.ImgResources;

/**
 * Class that handles the specific projectile fired by a gun
 * @author laudernich1
 *
 */
public class Projectile extends Weapon {
	private static final long serialVersionUID = 1620202643777534005L;
	private double angle;

	protected Projectile(CharacterEntity owner, Rectangle2D.Double box, int damage, ImgResources img, double targetX, double targetY, EntityType type) {
		super(box.getMinX()+owner.getWidth()/2d, box.getMinY()+owner.getHeight()/2d, box.getWidth(), box.getHeight(), type, damage);
		setImage(img);
		angle = Math.toDegrees(Math.atan2((targetY)-(getY()), (targetX)-(getX())));
		this.speed = 2;
		setOwner(owner);
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
