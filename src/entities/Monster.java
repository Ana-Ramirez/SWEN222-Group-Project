package entities;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class Monster extends MovableEntity {
	private Weapon weapon;

	public Monster(String name, float x, float y, Type type) {
		super.name = name;
		super.x = x;
		super.y = y;
		super.type = type;
	}
	
	@Override
	public boolean receiveHit(Weapon weapon) {
		//TODO implement;
		throw new NotImplementedException();
	}
}
