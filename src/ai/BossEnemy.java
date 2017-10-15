package ai;

import entities.Monster;
import entities.Player;
import interfaces.Entity;
import interfaces.MoveableEntity;
import interfaces.StratergyPattern;

/**
 * Class controlling the AI for the boss enemy (Mao Zedong)
 * The boss follows the player and every few ticks shoots lazers north, south, east, and west
 * 
 * @author Ana Ramirez
 */

public class BossEnemy  implements StratergyPattern {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1712599152531648053L;
	private final Entity player;
	private double speed = 1.0f;
	public int tick = 0;

	public BossEnemy(Entity player) {
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
		
		if(tick%3 == 0){
			monster.attack(player, tick);
			
			//boss attacking needs implementing!!
			
		}
		
		tick++;

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
