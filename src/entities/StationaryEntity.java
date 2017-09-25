package entities;

public abstract class StationaryEntity extends Entity {

	public boolean receiveHit(Weapon weapon) {
		return false;
	}

}
