package interfaces;

import java.io.Serializable;
/**
* Interface for all AI enemies
* @author ramireana
*/

public interface StrategyPattern extends Serializable{
	
	/**
	 * 
	 * @param monster
	 */
	public void tick(MoveableEntity monster);
	
	/**
	 * 
	 * @return
	 */
	public double getSpeed();
	
	/**
	 * 
	 * @param speed
	 */
	public void setSpeed(double speed);
	
}
