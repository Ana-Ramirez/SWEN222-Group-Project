package ai;

import org.junit.Test;

import entities.Monster;
import entities.Player;
import entities.Type;
import resources.ImgResources;

import static org.junit.Assert.*;

public class aiTests {
	
	@Test
	public void createFollowEnemy(){
		Player player = new Player(200,200,5,5, null);
		FollowingEnemy fEnemy = new FollowingEnemy(player);
		assertEquals(0.5f, fEnemy.speed(),0);
	}
	

	@Test
	/**
	 * Test to make sure the following AI finds the player
	 */ 
	public void followFindsPlayer() {
		Monster monster = new Monster("follow", 100, 100, 5, 5, Type.WATER, null ,ImgResources.FLOOR, null);
		Player player = new Player(200,200,5,5, null);
		FollowingEnemy fEnemy = new FollowingEnemy(player);
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
		Monster monster = new Monster("follow", 100, 100, 5, 5, Type.WATER, null,ImgResources.FLOOR, null);
		PatrollingEnemy pEnemy = new PatrollingEnemy();
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
		Monster monster = new Monster("follow", 100, 100, 5, 5, Type.WATER, null, ImgResources.FLOOR, null);
		Player player = new Player(200,200,5,5, null);
		RunawayEnemy rEnemy = new RunawayEnemy(player);
		int i = 200;
		while(i != 0){
			rEnemy.tick(monster);
			i--;
			System.out.println(monster.getX());
		}
		
		assertEquals(0f, monster.getX(), 0);
	}
	
	/**
	 * EXTERNAL TESTS
	 * @author Patrick Lynch
	 */
	
	/**
	 * Tests what happens when a following enemy is given nulls as inputs
	 */
	@Test
	public void testNullFollow(){
		FollowingEnemy fEnemy = new FollowingEnemy(null);
		fEnemy.tick(null);
	}
	
	/**
	 * Tests what happens when a patrolling enemy is given nulls as inputs
	 */
	@Test
	public void testNullPatrol(){
		PatrollingEnemy pEnemy = new PatrollingEnemy();
		pEnemy.tick(null);
	}
	
	/**
	 * Tests what happens when a runaway enemy is given nulls as inputs
	 */
	@Test
	public void testNullRunaway(){
		RunawayEnemy rEnemy = new RunawayEnemy(null);
		rEnemy.tick(null);
	}
	
	

}


