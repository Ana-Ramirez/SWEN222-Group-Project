package aiTests;

import org.junit.Test;

import ai.FollowingEnemy;
import ai.Goal;
import ai.PatrollingEnemy;
import ai.RunawayEnemy;
import entities.Type;
import interfaces.Entity;
import interfaces.MoveableEntity;
import interfaces.StratergyPattern;
import javafx.geometry.BoundingBox;
import resources.ImgResources;

import static org.junit.Assert.*;

@SuppressWarnings("serial")
public class aiTests {
	
	private class TestEntity implements Entity {
		private double x, y, width, height;
		public TestEntity(double x, double y, double width, double height) {
			this.x = x;
			this.y = y;
			this.width = width;
			this.height = height;
		}
		@Override
		public Type getType() {return null;}
		@Override
		public double getX() {return x;}
		@Override
		public double getY() {return y;}
		@Override
		public double getWidth() {return width;}
		@Override
		public double getHeight() {return height;}
		@Override
		public void setImage(ImgResources image) {}
		@Override
		public ImgResources getImage() {return null;}
		@Override
		public BoundingBox getBoundingBox() {return null;}
	}
	
	
	private class TestMoveableEntity implements MoveableEntity {
		private double x, y, width, height, speed = 1;
		private StratergyPattern pattern;
		public TestMoveableEntity(double x, double y, double width, double height, StratergyPattern pattern) {
			this.x = x;
			this.y = y;
			this.width = width;
			this.height = height;
			this.pattern = pattern;
		}
		@Override
		public Type getType() {return null;}
		@Override
		public double getX() {return x;}
		@Override
		public double getY() {return y;}
		@Override
		public double getWidth() {return width;}
		@Override
		public double getHeight() {return height;}
		@Override
		public void setImage(ImgResources image) {}
		@Override
		public ImgResources getImage() {return null;}
		@Override
		public BoundingBox getBoundingBox() {return null;}
		@Override
		public void moveBy(double x, double y) {
			this.x += x*speed;
			this.y += y*speed;
		}
		@Override
		public void moveTo(double x, double y) {
			this.x = x;
			this.y = y;
		}
		@Override
		public void tick() {
			pattern.tick(this);
		}
	}
	
	@Test
	public void createFollowEnemy(){
		TestEntity player = new TestEntity(200, 200, 5, 5);
		FollowingEnemy fEnemy = new FollowingEnemy(player);
		assertEquals(0.5, fEnemy.getSpeed(),0);
	}
	

	@Test
	/**
	 * Test to make sure the following AI finds the player
	 */ 
	public void followFindsPlayer() {
		TestMoveableEntity monster = new TestMoveableEntity(100, 100, 5, 5, null);
		TestEntity player = new TestEntity(200, 200, 5, 5);
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
		TestMoveableEntity monster = new TestMoveableEntity(100, 100, 5, 5, null);
		PatrollingEnemy pEnemy = new PatrollingEnemy(new Goal[] {new Goal(100, 100), new Goal(100, 100)});
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
		TestMoveableEntity monster = new TestMoveableEntity(100, 100, 5, 5, null);
		TestEntity player = new TestEntity(200, 200, 5, 5);
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
		PatrollingEnemy pEnemy = new PatrollingEnemy(new Goal[] {new Goal(100, 100), new Goal(100, 100)});
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
	
	
	/**
	 * External tests
	 * @author Nick Lauder
	 */
	
	@Test
	public void testPlayerTopLeftFollowing() {
		TestEntity player = new TestEntity(0, 0, 0, 0);
		TestMoveableEntity monster = new TestMoveableEntity(100, 100, 0, 0, new FollowingEnemy(player));
		
		for (int i = 0; i < 200; i++) {
			assertEquals(100-i/2d, monster.getX(), 0);
			assertEquals(100-i/2d, monster.getY(), 0);
			monster.tick();
		}
		assertEquals(0, monster.getX(), 0);
		assertEquals(0, monster.getY(), 0);
		monster.tick();
		assertEquals(0, monster.getX(), 0);
		assertEquals(0, monster.getY(), 0);
		monster.tick();
		assertEquals(0, monster.getX(), 0);
		assertEquals(0, monster.getY(), 0);
		monster.tick();
	}
	
	@Test
	public void testPlayerTopRightFollowing() {
		TestEntity player = new TestEntity(200, 0, 0, 0);
		TestMoveableEntity monster = new TestMoveableEntity(100, 100, 0, 0, new FollowingEnemy(player));
		
		for (int i = 0; i < 200; i++) {
			assertEquals(100+i/2d, monster.getX(), 0);
			assertEquals(100-i/2d, monster.getY(), 0);
			monster.tick();
		}
		assertEquals(200, monster.getX(), 0);
		assertEquals(0, monster.getY(), 0);
		monster.tick();
		assertEquals(200, monster.getX(), 0);
		assertEquals(0, monster.getY(), 0);
		monster.tick();
		assertEquals(200, monster.getX(), 0);
		assertEquals(0, monster.getY(), 0);
		monster.tick();
	}
	
	@Test
	public void testPlayerBottomLeftFollowing() {
		TestEntity player = new TestEntity(0, 200, 0, 0);
		TestMoveableEntity monster = new TestMoveableEntity(100, 100, 0, 0, new FollowingEnemy(player));
		
		for (int i = 0; i < 200; i++) {
			assertEquals(100-i/2d, monster.getX(), 0);
			assertEquals(100+i/2d, monster.getY(), 0);
			monster.tick();
		}
		assertEquals(0, monster.getX(), 0);
		assertEquals(200, monster.getY(), 0);
		monster.tick();
		assertEquals(0, monster.getX(), 0);
		assertEquals(200, monster.getY(), 0);
		monster.tick();
		assertEquals(0, monster.getX(), 0);
		assertEquals(200, monster.getY(), 0);
		monster.tick();
	}
	
	@Test
	public void testPlayerBottomRightFollowing() {
		TestEntity player = new TestEntity(200, 200, 0, 0);
		TestMoveableEntity monster = new TestMoveableEntity(100, 100, 0, 0, new FollowingEnemy(player));
		
		for (int i = 0; i < 200; i++) {
			assertEquals(100+i/2d, monster.getX(), 0);
			assertEquals(100+i/2d, monster.getY(), 0);
			monster.tick();
		}
		assertEquals(200, monster.getX(), 0);
		assertEquals(200, monster.getY(), 0);
		monster.tick();
		assertEquals(200, monster.getX(), 0);
		assertEquals(200, monster.getY(), 0);
		monster.tick();
		assertEquals(200, monster.getX(), 0);
		assertEquals(200, monster.getY(), 0);
		monster.tick();
	}
	
	@Test
	public void testPlayerTopLeftRunaway() {
		TestEntity player = new TestEntity(0, 0, 0, 0);
		TestMoveableEntity monster = new TestMoveableEntity(100, 100, 0, 0, new RunawayEnemy(player));
		
		for (int i = 0; i < 200; i++) {
			assertEquals(100+i/2d, monster.getX(), 0);
			assertEquals(100+i/2d, monster.getY(), 0);
			monster.tick();
		}
	}
	
	@Test
	public void testPlayerTopRightRunaway() {
		TestEntity player = new TestEntity(200, 0, 0, 0);
		TestMoveableEntity monster = new TestMoveableEntity(100, 100, 0, 0, new RunawayEnemy(player));
		
		for (int i = 0; i < 200; i++) {
			assertEquals(100-i/2d, monster.getX(), 0);
			assertEquals(100+i/2d, monster.getY(), 0);
			monster.tick();
		}		
	}
	
	@Test
	public void testPlayerBottomLeftRunaway() {
		TestEntity player = new TestEntity(0, 200, 0, 0);
		TestMoveableEntity monster = new TestMoveableEntity(100, 100, 0, 0, new RunawayEnemy(player));
		
		for (int i = 0; i < 200; i++) {
			assertEquals(100+i/2d, monster.getX(), 0);
			assertEquals(100-i/2d, monster.getY(), 0);
			monster.tick();
		}		
	}
	
	@Test
	public void testPlayerBottomRightRunaway() {
		TestEntity player = new TestEntity(200, 200, 0, 0);
		TestMoveableEntity monster = new TestMoveableEntity(100, 100, 0, 0, new RunawayEnemy(player));
		
		for (int i = 0; i < 200; i++) {
			assertEquals(100-i/2d, monster.getX(), 0);
			assertEquals(100-i/2d, monster.getY(), 0);
			monster.tick();
		}
	}
	
	@Test
	public void testMonsterFasterFollow() {
		TestEntity player = new TestEntity(200, 200, 0, 0);
		StratergyPattern pattern = new FollowingEnemy(player);
		TestMoveableEntity monster = new TestMoveableEntity(100, 100, 0, 0, pattern);
		
		
		for (int i = 0; i < 10; i++) {
			assertEquals(100+i*0.5, monster.getX(), 0);
			assertEquals(100+i*0.5, monster.getY(), 0);
			monster.tick();
		}
		
		pattern.setSpeed(1);
		for (int i = 0; i < 10; i++) {
			assertEquals(105+i*1d, monster.getX(), 0);
			assertEquals(105+i*1d, monster.getY(), 0);
			monster.tick();
		}
		
		pattern.setSpeed(2);
		for (int i = 0; i < 10; i++) {
			assertEquals(115+i*2d, monster.getX(), 0);
			assertEquals(115+i*2d, monster.getY(), 0);
			monster.tick();
		}
	}
	
	@Test
	public void testMonsterSlowerFollow() {
		TestEntity player = new TestEntity(200, 200, 0, 0);
		StratergyPattern pattern = new FollowingEnemy(player);
		TestMoveableEntity monster = new TestMoveableEntity(100, 100, 0, 0, pattern);
		
		assertEquals(0.5,  pattern.getSpeed(), 0);
		for (int i = 0; i < 10; i++) {
			assertEquals(100+i*0.5, monster.getX(), 0);
			assertEquals(100+i*0.5, monster.getY(), 0);
			monster.tick();
		}
		
		pattern.setSpeed(0.25);
		assertEquals(0.25,  pattern.getSpeed(), 0);
		for (int i = 0; i < 10; i++) {
			assertEquals(105+i*0.25, monster.getX(), 0);
			assertEquals(105+i*0.25, monster.getY(), 0);
			monster.tick();
		}
		
		pattern.setSpeed(0.125);
		assertEquals(0.125,  pattern.getSpeed(), 0);
		for (int i = 0; i < 10; i++) {
			assertEquals(107.5+i*0.125, monster.getX(), 0);
			assertEquals(107.5+i*0.125, monster.getY(), 0);
			monster.tick();
		}
	}
	
	@Test
	public void testMonsterFasterRunaway() {
		TestEntity player = new TestEntity(200, 200, 0, 0);
		StratergyPattern pattern = new RunawayEnemy(player);
		TestMoveableEntity monster = new TestMoveableEntity(100, 100, 0, 0, pattern);
		
		assertEquals(0.5,  pattern.getSpeed(), 0);
		for (int i = 0; i < 10; i++) {
			assertEquals(100-i*0.5, monster.getX(), 0);
			assertEquals(100-i*0.5, monster.getY(), 0);
			monster.tick();
		}
		
		pattern.setSpeed(1);
		assertEquals(1,  pattern.getSpeed(), 0);
		for (int i = 0; i < 10; i++) {
			assertEquals(95-i*1d, monster.getX(), 0);
			assertEquals(95-i*1d, monster.getY(), 0);
			monster.tick();
		}
		
		pattern.setSpeed(2);
		assertEquals(2,  pattern.getSpeed(), 0);
		for (int i = 0; i < 10; i++) {
			assertEquals(85-i*2d, monster.getX(), 0);
			assertEquals(85-i*2d, monster.getY(), 0);
			monster.tick();
		}
	}
	
	@Test
	public void testMonsterSlowerRunaway() {
		TestEntity player = new TestEntity(200, 200, 0, 0);
		StratergyPattern pattern = new RunawayEnemy(player);
		TestMoveableEntity monster = new TestMoveableEntity(100, 100, 0, 0, pattern);
		
		for (int i = 0; i < 10; i++) {
			assertEquals(100-i*0.5, monster.getX(), 0);
			assertEquals(100-i*0.5, monster.getY(), 0);
			monster.tick();
		}
		
		pattern.setSpeed(0.25);
		for (int i = 0; i < 10; i++) {
			assertEquals(95-i*0.25, monster.getX(), 0);
			assertEquals(95-i*0.25, monster.getY(), 0);
			monster.tick();
		}
		
		pattern.setSpeed(0.125);
		for (int i = 0; i < 10; i++) {
			assertEquals(92.5-i*0.125, monster.getX(), 0);
			assertEquals(92.5-i*0.125, monster.getY(), 0);
			monster.tick();
		}
	}
	
	@Test
	public void testPatrolling1Goals() {
		try {
			@SuppressWarnings("unused")
			StratergyPattern pattern = new PatrollingEnemy(new Goal[] {new Goal(100, 100)});
			fail("Requires more than 1 goal");
		} catch(IllegalArgumentException e) {
			
		}
	}
	
	@Test
	public void testPatrolling2Goals() {
		StratergyPattern pattern = new PatrollingEnemy(new Goal[] {new Goal(100, 100), new Goal(200, 200)});
		TestMoveableEntity monster = new TestMoveableEntity(150, 150, 0, 0, pattern);
		
		for (int i = 0; i < 100; i++) {
			assertEquals(150-i*0.5, monster.getX(), 0);
			assertEquals(150-i*0.5, monster.getY(), 0);
			monster.tick();
		}
		
		for (int i = 0; i < 200; i++) {
			assertEquals(100+i*0.5, monster.getX(), 0);
			assertEquals(100+i*0.5, monster.getY(), 0);
			monster.tick();
		}
		
		for (int i = 0; i < 200; i++) {
			assertEquals(200-i*0.5, monster.getX(), 0);
			assertEquals(200-i*0.5, monster.getY(), 0);
			monster.tick();
		}
	}
	
	@Test
	public void testPatrolling3Goals() {
		StratergyPattern pattern = new PatrollingEnemy(new Goal[] {new Goal(100, 200), new Goal(200, 200), new Goal(200, 300)});
		TestMoveableEntity monster = new TestMoveableEntity(150, 200, 0, 0, pattern);
		
		for (int i = 0; i < 100; i++) {
			assertEquals(150-i*0.5, monster.getX(), 0);
			assertEquals(200, monster.getY(), 0);
			monster.tick();
		}
		
		for (int i = 0; i < 200; i++) {
			assertEquals(100+i*0.5, monster.getX(), 0);
			assertEquals(200, monster.getY(), 0);
			monster.tick();
		}
		
		for (int i = 0; i < 200; i++) {
			assertEquals(200, monster.getX(), 0);
			assertEquals(200+i*0.5, monster.getY(), 0);
			monster.tick();
		}
		
		for (int i = 0; i < 200; i++) {
			assertEquals(200-i*0.5, monster.getX(), 0);
			assertEquals(300-i*0.5, monster.getY(), 0);
			monster.tick();
		}
	}
	
	@Test
	public void testPatrollingSlower() {
		StratergyPattern pattern = new PatrollingEnemy(new Goal[] {new Goal(100, 100), new Goal(200, 200)});
		TestMoveableEntity monster = new TestMoveableEntity(150, 150, 0, 0, pattern);
		
		assertEquals(0.5,  pattern.getSpeed(), 0);
		for (int i = 0; i < 100; i++) {
			assertEquals(150-i*0.5, monster.getX(), 0);
			assertEquals(150-i*0.5, monster.getY(), 0);
			monster.tick();
		}
		
		pattern.setSpeed(0.25);
		assertEquals(0.25,  pattern.getSpeed(), 0);
		for (int i = 0; i < 400; i++) {
			assertEquals(100+i*0.25, monster.getX(), 0);
			assertEquals(100+i*0.25, monster.getY(), 0);
			monster.tick();
		}
		
		pattern.setSpeed(0.125);
		assertEquals(0.125,  pattern.getSpeed(), 0);
		for (int i = 0; i < 800; i++) {
			assertEquals(200-i*0.125, monster.getX(), 0);
			assertEquals(200-i*0.125, monster.getY(), 0);
			monster.tick();
		}
	}
	
	@Test
	public void testPatrollingFaster() {
		StratergyPattern pattern = new PatrollingEnemy(new Goal[] {new Goal(100, 100), new Goal(200, 200)});
		TestMoveableEntity monster = new TestMoveableEntity(150, 150, 0, 0, pattern);
		
		assertEquals(0.5,  pattern.getSpeed(), 0);
		for (int i = 0; i < 100; i++) {
			assertEquals(150-i*0.5, monster.getX(), 0);
			assertEquals(150-i*0.5, monster.getY(), 0);
			monster.tick();
		}
		
		pattern.setSpeed(1);
		assertEquals(1,  pattern.getSpeed(), 0);
		for (int i = 0; i < 100; i++) {
			assertEquals(100+i*1d, monster.getX(), 0);
			assertEquals(100+i*1d, monster.getY(), 0);
			monster.tick();
		}
		
		pattern.setSpeed(2);
		assertEquals(2,  pattern.getSpeed(), 0);
		for (int i = 0; i < 50; i++) {
			assertEquals(200-i*2d, monster.getX(), 0);
			assertEquals(200-i*2d, monster.getY(), 0);
			monster.tick();
		}
	}
}


