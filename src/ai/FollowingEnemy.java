package ai;

import interfaces.Entity;
import interfaces.MoveableEntity;
import interfaces.StratergyPattern;

/**
 * Class controlling the AI for the enemy that follows the player
 * 
 * @author Ana Ramirez
 */

public class FollowingEnemy implements StratergyPattern {

	private static final long serialVersionUID = 7116534404337031487L;
	private final Entity player;
	private double speed = 0.5f;

	public FollowingEnemy(Entity player) {
		this.player = player;
	}
	
	/**
	 * Do relevant movement
	 */
	public void tick(MoveableEntity monster) {
		
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
