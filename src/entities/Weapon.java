package entities;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public abstract class Weapon extends Pickupable {
	private int damage;
	

	protected Weapon(float x, float y, Type type, int damage) {
		super(x, y, type);
		this.damage = damage;
		// TODO Auto-generated constructor stub
	}
	
	public boolean attack(Entity victim) {
		throw new NotImplementedException();
	}
	
	private static int modifier() {
		throw new NotImplementedException();
	}

}
