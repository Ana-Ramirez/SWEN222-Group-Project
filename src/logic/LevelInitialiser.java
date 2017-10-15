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
import interfaces.StratergyPattern;
import resources.ImgResources;

public class LevelInitialiser {
	
//	Level level;
//	List<Room> rooms;
//	Player player;
	
//	public LevelInitialiser(Level level, List<Room> rooms, Player player){
//		this.level = level;
//		this.rooms = rooms;
//		this.player = player;
//		initialise();
//	}
	
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
		Consumable live1 = new Consumable(new Rectangle2D.Double(20, 40, 32, 32), "Lives 1", ImgResources.LIFE);
		Consumable live2 = new Consumable(new Rectangle2D.Double(20, 40, 32, 32), "Lives 1", ImgResources.LIFE);
		Consumable ammo = new Consumable(new Rectangle2D.Double(20, 40, 32, 32), "Ammo 20", ImgResources.BULLET);

		Gun gunEarth = new Gun(new Rectangle2D.Double(300, 300, 32, 32), Type.EARTH, 10, ImgResources.GUN, ImgResources.BULLET);
		Gun gunFire = new Gun(new Rectangle2D.Double(200, 300, 32, 32), Type.FIRE, 20, ImgResources.GUN, ImgResources.BULLET);
		MeleeWeapon melee = new MeleeWeapon(new Rectangle2D.Double(100, 100, 32, 32), Type.WATER, 40, ImgResources.CONSOLE1);

		StratergyPattern pattern = new FollowingEnemy(player);

		Monster monsterEasy = new Monster(new Rectangle2D.Double(200, 200, 32, 32), Type.EARTH, gunEarth, ImgResources.MONSTER, pattern);
		Monster monsterMedium = new Monster(new Rectangle2D.Double(200, 200, 50, 50), Type.FIRE, gunFire, ImgResources.MONSTER, pattern);
		Monster monsterHard = new Monster(new Rectangle2D.Double(200, 200, 50, 50), Type.WATER, melee, ImgResources.MONSTER, pattern);

		//add to rooms
		room1.addEntity(gunEarth);

		room2.addEntity(monsterEasy);
		room2.addEntity(live1);

		room3.addEntity(monsterEasy);
		room3.addEntity(monsterMedium);
		room3.addEntity(melee);
		room3.addEntity(gunFire);

		room4.addEntity(live2);
		room4.addEntity(ammo);
		room4.addEntity(monsterHard);

	}

	
}
