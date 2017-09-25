package entities;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public abstract class Weapon extends Pickupable {
	private int damage;
	
	protected Weapon(int damage) {
		this.damage = damage;
	}
	
	public boolean attack(Entity victim) {
		throw new NotImplementedException();
	}
	
	public int getDamage(Entity victim) {
		throw new NotImplementedException();
	}
	
	private int getModifier(Entity victim) {
		throw new NotImplementedException();
	}
	
	protected int getBaseDamage() {
		return damage;
	}

}
