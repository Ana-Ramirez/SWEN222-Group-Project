package logic;

import entities.Entities;
import resources.ImgResources;

public class Wall extends Entities {
	public Wall(double x, double y, int width, int height){
		super(x, y, width, height, null);
		setImage(ImgResources.WALL);

	}

	@Override
	protected boolean hit(int damage) {
		return false;
	}

}
