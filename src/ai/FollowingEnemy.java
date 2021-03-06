package ai;

import interfaces.Entity;
import interfaces.Moveable;
import interfaces.StrategyPattern;

/**
 * Class controlling the AI for the enemy that follows the player
 * 
 * @author ramireana
 */

public class FollowingEnemy implements StrategyPattern {

	private static final long serialVersionUID = 7116534404337031487L;
	private final Entity player;
	private double speed;

	public FollowingEnemy(Entity player, double speed) {
		this.player = player;
		this.speed = speed;
	}
	
	/**
	 * Do relevant movement
	 */
	public void tick(Moveable monster) {
		
		if(monster == null){
			return;
		}
		
		if (monster.getX() > player.getX()) {
			monster.moveBy(-speed, 0);
		}

		if (monster.getX() < player.getX()) {
			monster.moveBy(+speed, 0);
		}

		if (monster.getY() > player.getY()) {
			monster.moveBy(0, -speed);
		}

		if (monster.getY() < player.getY()) {
			monster.moveBy(0, +speed);
		}

	}

	@Override
	public double getSpeed() {
		return speed;
	}

	@Override
	public void setSpeed(double speed) {
		this.speed = speed;
	}

}
