package entities;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class Monster extends MovableEntity {
	private Weapon weapon;

	public Monster(float x, float y, Type type) {
		super(x, y, type);
	}
	
	@Override
	public boolean receiveHit(Weapon weapon) {
		//TODO implement;
		throw new NotImplementedException();
	}
}
