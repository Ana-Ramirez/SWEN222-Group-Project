package interfaces;

import java.io.Serializable;
/**
* Interface for all AI enemies
* @author Ana Ramirez
*/

public interface StrategyPattern extends Serializable{
	
	/**
	 * 
	 * @param monster
	 */
	public void tick(Moveable monster);
	
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
