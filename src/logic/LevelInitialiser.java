package logic;

import ai.FollowingEnemy;
import entities.Consumable;
import entities.Gun;
import entities.MeleeWeapon;
import entities.Monster;
import entities.Type;
import interfaces.Enemies;
import resources.ImgResources;

public class LevelInitialiser {
	
	Level level;
	
	
	public LevelInitialiser(){
		
	}
	
	/**
	 * create rooms and add to this level
	 * @throws Exception
	 */
	private void initialise(){
		//create rooms and doors
		Room room1 = new Room(1, this);
		Room room2 = new Room(2, this);
		Room room3 = new Room(3, this);
		Room room4 = new Room(4, this);
		Room room5 = new Room(5, this);

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
		this.rooms.add(room1);
		this.rooms.add(room2);
		this.rooms.add(room3);
		this.rooms.add(room4);
		this.rooms.add(room5);

		//create entities to add
		Consumable consumable = new Consumable("food", 20, 40, 32, 32, "Lives 1", ImgResources.LIFE);

		Gun gunEarth = new Gun("gunEarth", 300, 300, 32, 32, Type.EARTH, 10, ImgResources.GUN, ImgResources.CONSOLE1);
		Gun gunFire = new Gun("gunFire", 200, 300, 32, 32, Type.FIRE, 20, ImgResources.GUN, ImgResources.CONSOLE1);
		MeleeWeapon melee = new MeleeWeapon("knife", 100, 100, 32, 32, Type.WATER, 40, ImgResources.CONSOLE1);

		Enemies pattern = new FollowingEnemy(player);

		Monster monsterEasy = new Monster("monsterEasy", 200, 200, 32, 32, Type.EARTH, gunEarth, ImgResources.MONSTER, pattern);
		Monster monsterMedium = new Monster("monsterMedium", 200, 200, 50, 50, Type.FIRE, gunFire, ImgResources.MONSTER, pattern);
		Monster monsterHard = new Monster("monsterHard", 200, 200, 50, 50, Type.WATER, melee, ImgResources.MONSTER, pattern);

		//add to rooms
		room1.addEntity(player);
		room1.addEntity(gunEarth);

		room2.addEntity(monsterEasy);
		room2.addEntity(consumable);

		room3.addEntity(monsterEasy);
		room3.addEntity(monsterMedium);
		room3.addEntity(melee);
		room3.addEntity(gunFire);

		room4.addEntity(consumable);
		room4.addEntity(consumable);
		room4.addEntity(monsterHard);

	}
	
}
