package entities;

public class MeleeWeapon extends Weapon {

	public MeleeWeapon(String name, float x, float y, Type type, int damage) {
		super(damage);
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
