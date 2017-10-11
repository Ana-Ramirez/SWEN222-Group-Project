package ai;

import java.io.Serializable;

import entities.Monster;
import interfaces.Enemies;

public class EnemyStrategy implements Enemies, Serializable{
	private Monster monster;
	
	public EnemyStrategy(int xPos, int yPos, int direction, Monster monster){
	}
	
	public float speed(){
		return 0f;
	}
	
	
	public void tick(Monster monster){
		monster.tick();
	}
	

	
}
