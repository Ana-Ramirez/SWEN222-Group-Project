package interfaces;

import java.awt.geom.Rectangle2D;
import java.util.List;

import ai.BossEnemy;
import ai.FollowingEnemy;
import ai.Goal;
import ai.PatrollingEnemy;
import entities.ConsumableEntity;
import entities.GunEntity;
import entities.MeleeWeaponEntity;
import entities.MonsterEntity;
import interfaces.Consumable;
import interfaces.Monster;
import interfaces.Player;
import interfaces.EntityType;
import interfaces.StrategyPattern;

import logic.Door;
import logic.Level;
import logic.Room;
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
	private static int maoHealth = 300;
	
	private static int gunStrength = 20;
	private static int meleeStrength = 20;
	
	/**
	 * create rooms and add to this level
	 * @throws Exception
	 */
	public static void initialise(Level level, List<Room> rooms, Player player){

		//Create keys for rooms
		Consumable key1 = new ConsumableEntity(new Rectangle2D.Double(400, 200, 40, 40), "Key 1", ImgResources.KEY);
		Consumable key2 = new ConsumableEntity(new Rectangle2D.Double(400, 200, 40, 40), "Key 2", ImgResources.KEY);
		Consumable key3 = new ConsumableEntity(new Rectangle2D.Double(400, 200, 40, 40), "Key 3", ImgResources.KEY);
		Consumable key4 = new ConsumableEntity(new Rectangle2D.Double(400, 200, 40, 40), "Key 4", ImgResources.KEY);
		
		//create rooms
		Room room1 = new Room(1, level);
		Room room2 = new Room(2, level);
		Room room3 = new Room(3, level);
		Room room4 = new Room(4, level);
		Room room5 = new Room(5, level);

		//create doors for rooms
		Door door1 = new Door(room1, room5, key1, 1, 0);
		Door door2 = new Door(room2, room5, key2, 2, 1);
		Door door3 = new Door(room3, room5, key3, 3, 2);
		Door door4 = new Door(room4, room5, key4, 4, 3);
		
		//adding doors to all rooms
		room1.addEntity(door1);	//north door
		room2.addEntity(door2);	//south door
		room3.addEntity(door3);	//east door
		room4.addEntity(door4);	//west door
		
		room5.addEntity(door1);	
		room5.addEntity(door2);	
		room5.addEntity(door3);	
		room5.addEntity(door4);	

		//add rooms to the level
		rooms.add(room1);
		rooms.add(room2);
		rooms.add(room3);
		rooms.add(room4);
		rooms.add(room5);
		
		//create entities to add
		Weapon gunEarth = new GunEntity(new Rectangle2D.Double(300, 300, 32, 32), EntityType.EARTH, 10, 20, ImgResources.GUN, ImgResources.BULLET);
		Weapon gunFire = new GunEntity(new Rectangle2D.Double(200, 300, 32, 32), EntityType.FIRE, 20, 20, ImgResources.GUN, ImgResources.BULLET);
		Weapon bossGun = new GunEntity(new Rectangle2D.Double(200, 300, 32, 32), EntityType.FIRE, 20, -1, ImgResources.GUN, ImgResources.BULLET);
		
		Goal goals[] = new Goal[] {new Goal(50, 50), new Goal(150, 350), new Goal(600, 50), new Goal(600, 350)};
		StrategyPattern followPattern = new FollowingEnemy(player, 1d),
				bossPattern = new BossEnemy(player, 1d),
				patrolPattern = new PatrollingEnemy(goals, 2d);
		Monster boss = new MonsterEntity(new Rectangle2D.Double(400, 200, 75, 75), maoHealth, EntityType.FIRE, bossGun, ImgResources.MAO, bossPattern);
		level.setBoss(boss);


		//add to rooms
		room1.addEntity(new GunEntity(new Rectangle2D.Double(200, 200, Renderer.TILE_SIZE, Renderer.TILE_SIZE), EntityType.FIRE, gunStrength, 20, ImgResources.GUN, ImgResources.BULLET));
		room1.addEntity(key1);
//		room1.addEntity(new Consumable(new Rectangle2D.Double(500, 250, 32, 32), "Speed 10", ImgResources.POTION));
		room1.addEntity(new MeleeWeaponEntity(new Rectangle2D.Double(400, 300, 32, 32), EntityType.WATER, meleeStrength, ImgResources.SWORDLEFTUP));
		
		room2.addEntity(new ConsumableEntity(new Rectangle2D.Double(50, 50, Renderer.TILE_SIZE, Renderer.TILE_SIZE), "Lives 1", ImgResources.LIFE));
		room2.addEntity(new MonsterEntity(new Rectangle2D.Double(500, 250, 30, 58), monsterMedHealth, EntityType.EARTH, gunEarth, ImgResources.MONSTER, followPattern));
		room2.addEntity(new MonsterEntity(new Rectangle2D.Double(50, 50, 22, 44), monsterEasyHealth, EntityType.EARTH, gunEarth, ImgResources.MONSTER, patrolPattern));
		room2.addEntity(key4);
		
		room3.addEntity(new ConsumableEntity(new Rectangle2D.Double(700, 300, 32, 32), "Lives 1", ImgResources.LIFE));
		room3.addEntity(new MonsterEntity(new Rectangle2D.Double(500, 250, 45, 87), monsterHardHealth, EntityType.FIRE, gunFire, ImgResources.MONSTER, patrolPattern));
		room3.addEntity(key2);
		
		room4.addEntity(boss);
		room4.addEntity(new ConsumableEntity(new Rectangle2D.Double(600, 400, Renderer.TILE_SIZE, Renderer.TILE_SIZE), "Ammo 10", ImgResources.AMMO));
		
		room5.addEntity(new MonsterEntity(new Rectangle2D.Double(400, 150, 22, 44), monsterEasyHealth, EntityType.FIRE, gunFire, ImgResources.MONSTER, followPattern));
//		room5.addEntity(new Consumable(new Rectangle2D.Double(150, 350, 32, 32), "Speed 10", ImgResources.POTION));
		room5.addEntity(new ConsumableEntity(new Rectangle2D.Double(500, 100, 32, 32), "Lives 1", ImgResources.LIFE));
		room5.addEntity(new GunEntity(new Rectangle2D.Double(650, 350, 32, 32), EntityType.EARTH, gunStrength, 20, ImgResources.GUN, ImgResources.BULLET));
		room5.addEntity(key3);
		
		room1.addEntity(player);

	}

	
}
