package entities;

public class Consumable extends Pickupable {

	public Consumable(String name, float x, float y, Type type) {
		super.name = name;
		super.x = x;
		super.y = y;
		super.type = type;
	}

	@Override
	public boolean receiveHit(Weapon weapon) {
		// TODO Auto-generated method stub
		return false;
	}
	
	
	
}
