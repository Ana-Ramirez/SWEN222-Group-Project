package ai;

import org.junit.Test;

import entities.Entity;
import entities.Monster;
import entities.Player;
import entities.Type;
import entities.Weapon;

import static org.junit.Assert.*;

public class aiTests {
	
	@Test
	public void createFollowEnemy(){
		FollowingEnemy fEnemy = new FollowingEnemy(100,100, 5, 5);
		assertEquals(0.5f, fEnemy.speed(),0);	
	}
	

	@Test
	/**
	 * Test to make sure the following AI finds the player
	 */ 
	public void followFindsPlayer() {
		Monster monster = new Monster("follow", 100, 100, 5, 5, Type.WATER, null);
		Player player = new Player(200,200,5,5);
		FollowingEnemy fEnemy = new FollowingEnemy(100,100,5, 5);
		int i = 1000;
		while(i != 0){
			fEnemy.tick(monster);
			i--;
			System.out.println(monster.getX());
		}
		
		assertEquals(200f, monster.getX(), 0);
	}
	
	
	@Test
	/**
	 * Test to make sure the patrolling AI goes between the two goals
	 */ 
	public void patrolEnemy() {
		Monster monster = new Monster("follow", 100, 100, 5, 5, Type.WATER, null);
		Player player = new Player(200,200,5,5);
		PatrollingEnemy pEnemy = new PatrollingEnemy(100,100,5,5);
		int i = 401;
		while(i != 0){
			pEnemy.tick(monster);
			i--;
			System.out.println(monster.getY());
		}
		
		assertEquals(100f, monster.getY(), 0);
	}
	
	
	@Test
	/**
	 * Test to make sure run away AI runs away from the player
	 */ 
	public void runAwayEnemy() {
		Monster monster = new Monster("follow", 100, 100, 5, 5, Type.WATER, null);
		Player player = new Player(200,200,5,5);
		RunawayEnemy rEnemy = new RunawayEnemy(100,100,5, 5);
		int i = 200;
		while(i != 0){
			rEnemy.tick(monster);
			i--;
			System.out.println(monster.getX());
		}
		
		assertEquals(0f, monster.getX(), 0);
	}

}


