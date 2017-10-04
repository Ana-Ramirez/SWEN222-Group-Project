package TestInterfaces;

import entities.Monster;

public interface FollowingEnemy {

	public void tick(Monster monster);

	public float speed();

}
