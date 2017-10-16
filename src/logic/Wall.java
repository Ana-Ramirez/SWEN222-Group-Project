package logic;

import entities.Entities;
import resources.ImgResources;

public class Wall extends Entities {
	private static final long serialVersionUID = -9001742836653967062L;

	public Wall(double x, double y, int width, int height){
		super(x, y, width, height, null);
		setImage(ImgResources.WALL);
	}

	@Override
	protected boolean hit(int damage) {
		return false;
	}

}
