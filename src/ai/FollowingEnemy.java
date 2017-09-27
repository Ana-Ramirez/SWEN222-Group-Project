package ai;

import java.awt.Rectangle;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * Class controlling the AI for the enemy that follows the player
 * 
 * @author Ana Ramirez
 */ 

public class FollowingEnemy implements Enemies {

	public int health;
	public float speed = 0.5f;
	public int x;
	public int y;
	private Rectangle boundingBox = new Rectangle(((int) x * 6) - 30, ((int) y * 6) - 30, 50, 50);

	private Player p = new Player(50, 50);

	public Player getPlayer() {
		return p;
	}

	public FollowingEnemy(float x, float y) {

	}

	/**
	 * Do relevant movement
	 */
	public void move() {
		if (x > p.x) {
			x -= speed;
		}

		if (x < p.x) {
			x += speed;
		}

		if (y > p.y) {
			y -= speed;
		}

		if (y < p.y) {
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

	public class Player {

		float x;
		float y;

		public Player(float x, float y) {

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
