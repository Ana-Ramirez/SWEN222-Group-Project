package ai;

import java.awt.Rectangle;

import entities.Monster;
import entities.Player;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * Class controlling the AI for the enemy patrols the screen and has a set path
 * to follow
 * 
 * @author Ana Ramirez
 */

public class PatrollingEnemy implements Enemies {

	public Player player;
	public int health;
	public float speed = 0.5f;
	public int x;
	public int y;
	private Rectangle boundingBox = new Rectangle(((int) x * 6) - 30, ((int) y * 6) - 30, 50, 50);
	public Goal goal1 = new Goal(100, 100);
	public Goal goal2 = new Goal(200, 200);
	public Goal currentGoal = goal1;

	public PatrollingEnemy(float x, float y) {

	}

	/**
	 * Do relevant movement
	 */
	public void tick(Monster monster) {

		if (x > currentGoal.getX()) {
			x -= speed;
		}

		if (x < currentGoal.getY()) {
			x += speed;
		}

		if (y > currentGoal.getX()) {
			y -= speed;
		}

		if (y < currentGoal.getY()) {
			y += speed;
		}

		if (x == goal1.getX() && y == goal1.getY()) {
			currentGoal = goal2;
		}

		if (x == goal2.getX() && y == goal2.getY()) {
			currentGoal = goal1;
		}

		updateBoundingBox(((int) x * 6) - 30, ((int) y * 6) - 30);
	}

	private void updateBoundingBox(int x, int y) {
		boundingBox = new Rectangle(x, y, 50, 50);
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
