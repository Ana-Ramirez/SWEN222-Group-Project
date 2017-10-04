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
	
	public Player player = new Player(200,200,5,5);
	public int health;
	public float speed = 0.5f;
	public int x;
	public int y;
	public int width;
	public int height;


	public RunawayEnemy(float x, float y, int width, int height) {

	}

	/**
	 * Do relevant movement
	 */
	public void tick(Monster monster) {
		if (monster.getX() > player.getX()) {
			monster.moveBy(+speed, 0);
		}

		if (monster.getX() < player.getX()) {
			monster.moveBy(-speed, 0);
		}

		if (monster.getY() > player.getY()) {
			monster.moveBy(0, +speed);
		}

		if (monster.getY() < player.getY()) {
			monster.moveBy(0, -speed);
		}
	}
	

	/**
	 * Speed of movement
	 */
	public float speed() {
		return speed;
	}
}

