package ai;

import java.awt.Rectangle;

import entities.Monster;
import entities.Player;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
* Class controlling the AI for the enemy that runs away from the player
* @author Ana Ramirez
*/

public class RunawayEnemy implements Enemies{
	
	public Player player;
	public int health;
	public float speed = 0.5f;
	public int x;
	public int y;
	private Rectangle boundingBox = new Rectangle(((int) x * 6) - 30, ((int) y * 6) - 30, 50, 50);


	public RunawayEnemy(float x, float y) {

	}

	/**
	 * Do relevant movement
	 */
	public void tick(Monster monster) {
		if (x > player.getX()) {
			x += speed;
		}

		if (x < player.getY()) {
			x -= speed;
		}

		if (y > player.getX()) {
			y += speed;
		}

		if (y < player.getY()) {
			y -= speed;
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

	


	
}

