package ai;

import java.awt.Rectangle;

import entities.Monster;
import interfaces.Enemies;

/**
 * Class controlling the AI for the enemy patrols the screen and has a set path
 * to follow
 * 
 * @author Ana Ramirez
 */

public class PatrollingEnemy implements Enemies {

	public float speed = 0.5f;
	public Goal goal1 = new Goal(100, 100);
	public Goal goal2 = new Goal(100, 200);
	public Goal currentGoal = goal1;

	public PatrollingEnemy() {
	}

	/**
	 * Do relevant movement
	 */
	public void tick(Monster monster) {

		if (monster.getX() > currentGoal.getX()) {
			monster.moveBy(-speed, 0);
		}

		if (monster.getX() < currentGoal.getX()) {
			monster.moveBy(+speed, 0);
		}

		if (monster.getY() > currentGoal.getY()) {
			monster.moveBy(0, -speed);
		}

		if (monster.getY() < currentGoal.getY()) {
			monster.moveBy(0, +speed);
		}

		if (monster.getX() == goal1.getX() && monster.getY() == goal1.getY()) {
			currentGoal = goal2;
		}

		if (monster.getX() == goal2.getX() && monster.getY() == goal2.getY()) {
			currentGoal = goal1;
		}
	}

	/**
	 * Speed of movement
	 */
	public float speed() {
		return speed;
	}

	class Goal {

		float x;
		float y;
		public float speed = 2;

		public Goal(float x, float y) {
			this.x = x;
			this.y = y;
		}

		private Rectangle boundingBox = new Rectangle(((int) x * 6) - 30, ((int) y * 6) - 30, 50, 50);

		public Rectangle getBoundingBox() {
			return boundingBox;
		}

		public float getX() {
			return x;
		}// no setter

		public float getY() {
			return y;
		}
	}
}