package ai;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
* Class controlling the AI for the enemy that runs away from the player
* @author Ana Ramirez
*/

public class RunawayEnemy implements Enemies{
	
	public int health;
	public int speed;
	
	public RunawayEnemy(float x, float y){
		
	}
	
	/**
	 * Do relevant movement
	 */
	public void move(){
		throw new NotImplementedException();
	}
	
	
	/**
	 * Decrease health when hit is taken
	 */
	public void decreaseHealth(){
		health--;
		throw new NotImplementedException();
	}
	
	/**
	 * Attack player
	 */
	public void attack(){
		throw new NotImplementedException();
	}
	
	/**
	 * Speed of movement
	 */
	public int speed(){
		return speed;
	}
	
	/**
	 * Enemy dies
	 */
	public void die(){
		throw new NotImplementedException();
	}
	
}