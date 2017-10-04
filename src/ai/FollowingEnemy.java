package ai;

import entities.Monster;
import entities.Player;

/**
 * Class controlling the AI for the enemy that follows the player
 * 
 * @author Ana Ramirez
 */

public class FollowingEnemy implements Enemies {

	public Player player;
	public float speed = 0.5f;

	public FollowingEnemy(Player player) {
		this.player = player;
	}

	/**
	 * Do relevant movement
	 */
	public void tick(Monster monster) {
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

	/**
	 * Speed of movement
	 */
	public float speed() {
		return speed;
	}

}
