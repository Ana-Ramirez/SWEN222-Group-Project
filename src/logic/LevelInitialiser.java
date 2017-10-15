package logic;

import java.awt.geom.Rectangle2D;
import java.util.List;

import ai.FollowingEnemy;
import entities.Consumable;
import entities.Gun;
import entities.MeleeWeapon;
import entities.Monster;
import entities.Player;
import entities.Type;
import interfaces.StrategyPattern;
import resources.ImgResources;
import view.Renderer;

/**
 * This class generates the level and rooms and objects/monsters and adds
 * them to all the game.
 * The room layout for this level is as such:
 * 		2
 * 3	5	4
 * 		1
 * 
 * @author lewismcewan
 *
 */
public class LevelInitialiser {
	
	private static int monsterEasyHealth = 100;
	private static int monsterMedHealth = 140;
	private static int monsterHardHealth = 180;
	private static int maoHealth = 240;
	
	private static int gunStrength = 20;
	private static int meleeStrength = 5;
	
	/**
	 * create rooms and add to this level
	 * @throws Exception
	 */
	public static void initialise(Level level, List<Room> rooms, Player player){

		//create rooms and doors
		Room room1 = new Room(1, level);
		Room room2 = new Room(2, level);
		Room room3 = new Room(3, level);
		Room room4 = new Room(4, level);
		Room room5 = new Room(5, level);

		//create doors for rooms
		room1.addEntity(new Door(room1, room5, null, 1, 0));	//north door
		room2.addEntity(new Door(room2, room5, null, 2, 1));	//south door
		room3.addEntity(new Door(room3, room5, null, 3, 2));	//east door
		room4.addEntity(new Door(room4, room5, null, 4, 3));	//west door
		
		//adding new door objects which are the same but different
		//position as they need to be flipped for the center room
		room5.addEntity(new Door(room1, room5, null, 51, 1));	//south door
		room5.addEntity(new Door(room2, room5, null, 52, 0));	//north door
		room5.addEntity(new Door(room3, room5, null, 53, 3));	//west door
		room5.addEntity(new Door(room4, room5, null, 54, 2));	//east door

		//add rooms to the level
		rooms.add(room1);
		rooms.add(room2);
		rooms.add(room3);
		rooms.add(room4);
		rooms.add(room5);
		
		//create entities to add
		Gun gunEarth = new Gun(new Rectangle2D.Double(300, 300, 32, 32), Type.EARTH, 10, 20, ImgResources.GUN, ImgResources.BULLET);
		Gun gunFire = new Gun(new Rectangle2D.Double(200, 300, 32, 32), Type.FIRE, 20, 20, ImgResources.GUN, ImgResources.BULLET);
		Gun bossGun = new Gun(new Rectangle2D.Double(200, 300, 32, 32), Type.FIRE, 20, -1, ImgResources.GUN, ImgResources.BULLET);

		StrategyPattern pattern = new FollowingEnemy(player);
		Monster boss = new Monster(new Rectangle2D.Double(400, 200, 75, 75), maoHealth, Type.FIRE, bossGun, ImgResources.MAO, pattern);
		level.setBoss(boss);


		//add to rooms
		room1.addEntity(new Gun(new Rectangle2D.Double(200, 200, Renderer.TILE_SIZE, Renderer.TILE_SIZE), Type.FIRE, gunStrength, 20, ImgResources.GUN, ImgResources.BULLET));
//		room1.addEntity(new Consumable(new Rectangle2D.Double(400, 200, 40, 40), "Key 5", ImgResources.KEY));
//		room1.addEntity(new Consumable(new Rectangle2D.Double(500, 250, 32, 32), "Speed 10", ImgResources.POTION));
		room1.addEntity(new MeleeWeapon(new Rectangle2D.Double(400, 300, 32, 32), Type.WATER, meleeStrength, ImgResources.SWORDLEFTUP));
		
		room2.addEntity(new Consumable(new Rectangle2D.Double(50, 50, Renderer.TILE_SIZE, Renderer.TILE_SIZE), "Lives 1", ImgResources.LIFE));
		room2.addEntity(new Monster(new Rectangle2D.Double(500, 250, 75, 75), monsterMedHealth, Type.EARTH, gunEarth, ImgResources.MONSTER, pattern));
		room2.addEntity(new Monster(new Rectangle2D.Double(50, 50, 40, 40), monsterEasyHealth, Type.EARTH, gunEarth, ImgResources.MONSTER, pattern));
		
		room3.addEntity(new Consumable(new Rectangle2D.Double(700, 300, 32, 32), "Lives 1", ImgResources.LIFE));
//		room3.addEntity(new Consumable(new Rectangle2D.Double(200, 200, 40, 40), "Key 4", ImgResources.KEY));
		room3.addEntity(new Monster(new Rectangle2D.Double(500, 250, 75, 75), monsterHardHealth, Type.FIRE, gunFire, ImgResources.MONSTER, pattern));
//		room3.addEntity(new Consumable(new Rectangle2D.Double(400, 400, 40, 40), "Key 2", ImgResources.KEY));
//		
		room4.addEntity(boss);
		room4.addEntity(new Consumable(new Rectangle2D.Double(600, 400, Renderer.TILE_SIZE, Renderer.TILE_SIZE), "Ammo 10", ImgResources.AMMO));
//		
		room5.addEntity(new Monster(new Rectangle2D.Double(400, 150, 75, 75), monsterEasyHealth, Type.FIRE, gunFire, ImgResources.MONSTER, pattern));
//		room5.addEntity(new Consumable(new Rectangle2D.Double(150, 350, 32, 32), "Speed 10", ImgResources.POTION));
		room5.addEntity(new Consumable(new Rectangle2D.Double(500, 100, 32, 32), "Lives 1", ImgResources.LIFE));
		room5.addEntity(new Gun(new Rectangle2D.Double(650, 350, 32, 32), Type.EARTH, gunStrength, 20, ImgResources.GUN, ImgResources.BULLET));
		
		room1.addEntity(player);

	}

	
}
