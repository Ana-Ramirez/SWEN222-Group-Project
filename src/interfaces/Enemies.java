package interfaces;

import java.io.Serializable;

import entities.Monster;

/**
* Interface for all AI enemies
* @author Ana Ramirez
*/

public interface Enemies extends Serializable{
	
	
	public void tick(Monster monster);
	
}
