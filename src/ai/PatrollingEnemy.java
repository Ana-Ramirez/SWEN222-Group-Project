package ai;

import java.awt.Rectangle;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
* Class controlling the AI for the enemy patrols the screen and has a set path to follow
* @author Ana Ramirez
*/

public class PatrollingEnemy implements Enemies{
	public int health;
	public float speed = 0.5f;
	public int x;
	public int y;
	private Rectangle boundingBox = new Rectangle(((int) x * 6) - 30, ((int) y * 6) - 30, 50, 50);

	private Goal g = new Goal(50, 50);

	public Goal getPlayer() {
		return g;
	}

	public PatrollingEnemy(float x, float y) {

	}

	/**
	 * Do relevant movement
	 */
	public void move() {
		if (x > g.x) {
			x -= speed;
		}

		if (x < g.x) {
			x += speed;
		}

		if (y > g.y) {
			y -= speed;
		}

		if (y < g.y) {
			y += speed;
		}

		updateBoundingBox(((int) x * 6) - 30, ((int) y * 6) - 30);
	}

	private void updateBoundingBox(int x, int y) {
		boundingBox = new Rectangle(x, y, 50, 50);
	}

	/**
	 * Decrease health when hit is taken
	 */
	public void decreaseHealth() {
		health--;
		throw new NotImplementedException();
	}

	/**
	 * Speed of movement
	 */
	public float speed() {
		return speed;
	}

	/**
	 * Enemy dies
	 */
	public void die() {
		if(health == 0){
			//die or disappear or w/e
		}
		throw new NotImplementedException();
	}

	public class Goal {

		float x;
		float y;

		public Goal(float x, float y) {

		}

		private Rectangle boundingBox = new Rectangle(((int) x * 6) - 30, ((int) y * 6) - 30, 50, 50);
		public final float speed = 2;

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


