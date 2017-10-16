package ai;


import interfaces.Entity;
import interfaces.Moveable;
import interfaces.StrategyPattern;

/**
 * Class controlling the AI for the enemy that follows the player
 * 
 * @author ramireana
 */

public class BossEnemy implements StrategyPattern {

	private static final long serialVersionUID = 7116534404337031487L;
	private final Entity player;
	private double speed;
	private double playerPointX;
	private double playerPointY;

	public BossEnemy(Entity player, double speed) {
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
		
		if((player.getX() - monster.getX() < 250) && (player.getX() - monster.getX() > -250) && (player.getY() - monster.getY() < 250) && (player.getY() - monster.getY() > -250)){
			setSpeed(2);
			
			if (monster.getX() > playerPointX) {
				monster.moveBy(-speed, 0);
			}

			if (monster.getX() < playerPointX) {
				monster.moveBy(+speed, 0);
			}

			if (monster.getY() > playerPointY) {
				monster.moveBy(0, -speed);
			}

			if (monster.getY() < playerPointY) {
				monster.moveBy(0, +speed);
			}
			
		}
		else{
			 playerPointX = player.getX();
			 playerPointY = player.getY();
			setSpeed(1);
			
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
