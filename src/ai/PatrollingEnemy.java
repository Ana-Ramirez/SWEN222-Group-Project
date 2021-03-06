package ai;


import interfaces.Moveable;
import interfaces.StrategyPattern;

/**
 * Class controlling the AI for the enemy patrols the window and has a set path
 * to follow
 * 
 * @author ramireana
 * @author laudernich1
 */

public class PatrollingEnemy implements StrategyPattern {

	private static final long serialVersionUID = 7378834863382700237L;
	private double speed;
	private Goal[] goals;
	private int currentGoal;

	public PatrollingEnemy(Goal[] goals, double speed) {
		if (goals.length < 2) {
			throw new IllegalArgumentException("More than 1 goal must be given");
		}
		this.goals = goals;
		currentGoal = 0;
		this.speed = speed;
	}

	/**
	 * Do relevant movement
	 */
	public void tick(Moveable monster) {
		
		if(monster == null){
			return;
		}

		if (monster.getX() > goals[currentGoal].getX()) {
			monster.moveBy(-speed, 0);
		} else if (monster.getX() < goals[currentGoal].getX()) {
			monster.moveBy(speed, 0);
		}

		if (monster.getY() > goals[currentGoal].getY()) {
			monster.moveBy(0, -speed);
		} else if (monster.getY() < goals[currentGoal].getY()) {
			monster.moveBy(0, speed);
		}

		if (monster.getX() == goals[currentGoal].getX() && monster.getY() == goals[currentGoal].getY()) {
			nextGoal();
		}
	}
	
	private void nextGoal() {
		if (currentGoal+1 == goals.length) {
			currentGoal = 0;
		} else {
			currentGoal++;
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