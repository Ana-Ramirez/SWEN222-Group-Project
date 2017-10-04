package TestInterfaces;

import java.awt.Rectangle;

import ai.PatrollingEnemy.Goal;
import entities.Monster;
import entities.Player;

public interface PatrollingEnemy {

	public Goal goal1 = new Goal(100, 100);
	public Goal goal2 = new Goal(100, 200);
	public Goal currentGoal = goal1;
	
	public void tick(Monster monster);

	public float speed();
		

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
