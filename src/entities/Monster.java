package entities;


/**
 * The monster object that can move around the level and attack the player
 * @author Nick Lauder
 *
 */
public class Monster extends MovableEntity {
	private Weapon weapon;
	
	/**
	 * Creates a new monster
	 * @param name
	 * 		the string name to use
	 * @param x
	 * 		the float x coordinate to use
	 * @param y
	 * 		the float y coordinate to use
	 * @param type
	 * 		the type of the monster
	 * @param weapon
	 * 		the monsters weapon
	 */
	public Monster(String name, float x, float y, Type type, Weapon weapon) {
		this.name = name;
		this.x = x;
		this.y = y;
		this.type = type;
		this.weapon = weapon;
	}
}
