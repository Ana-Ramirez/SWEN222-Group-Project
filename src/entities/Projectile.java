package entities;

public class Projectile extends Weapon {

	public Projectile(String name, float x, float y, Type type, int damage) {
		super(damage);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean receiveHit(Weapon weapon) {
		// TODO Auto-generated method stub
		return false;
	}

}
