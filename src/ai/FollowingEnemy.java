package ai;

import java.awt.Rectangle;

import entities.Monster;
import entities.Player;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * Class controlling the AI for the enemy that follows the player
 * 
 * @author Ana Ramirez
 */ 

public class FollowingEnemy implements Enemies {

	public Player player;
	public int health;
	public float speed = 0.5f;
	public float x = 10;
	public float y = 100;


	public FollowingEnemy(float x, float y, Player player) {
		this.x = x;
		this.y = y;
		this.player = player;
	}

	/**
	 * Do relevant movement
	 */
	public void tick(Monster monster) {
		if (monster.getX() > player.getX()) {
			x -= speed;
			monster.moveBy(-speed,0f);
			
		}

		if (x < player.getX()) {
			x += speed;
		}

		if (y > player.getY()) {
			y -= speed;
		}

		if (y < player.getY()) {
			y += speed;
		}

	}
	
	public float getX(){
		return x;
	}

	public float getY(){
		return y;
	}


	/**
	 * Speed of movement
	 */
	public int speed() {
		return speed;
	}


	
}
