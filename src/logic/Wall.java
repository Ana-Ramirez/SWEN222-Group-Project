package logic;

import entities.Entities;
import resources.ImgResources;

/**
 * This creates a wall object as an entity which is used around the
 * perimeter of the rooms.
 * @author lewismcewan
 *
 */
public class Wall extends Entities {
	private static final long serialVersionUID = -9001742836653967062L;

//<<<<<<< HEAD
	String position = null;
	
	/**
	 * 
	 * @param pos		where on wall this entity is
	 * @param x			x pos
	 * @param y			y pos
	 * @param width		wall width
	 * @param height	wall height
	 */
	public Wall(String pos, double x, double y, int width, int height){
		super(x, y, width, height, null);

		switch(pos) {
		case "top":
			setImage(ImgResources.WALLTOP);
			break;
		case "other":
			setImage(ImgResources.WALL);
			break;
		}
	}
	
	/**
	 * @return		position of the wall
	 */
	public String getPosition(){
		return this.position;
	}

	@Override
	protected boolean hit(int damage) {
		return false;
	}

}
