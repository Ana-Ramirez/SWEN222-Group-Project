package entities;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class Player extends MovableEntity {
	private Pickupable[] inventory;
	private Pickupable hand;
	private int health;
	
	public Player(float x, float y, Type type) {
		super(x, y, type);
		health = 100;
	}
	
	public void setCoor(float x, float y) {
		setXY(x, y);
	}
	
	@Override
	public boolean receiveHit(Weapon weapon) {
		//TODO implement;
		throw new NotImplementedException();
	}
	
	public boolean changeHand() {
		throw new NotImplementedException();
	}
	
	
}
