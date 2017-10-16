package logic;

import entities.Entities;
import resources.ImgResources;

public class Wall extends Entities {

	String position = null;

	public Wall(String pos, double x, double y, int width, int height){
		super(x, y, width, height, null);

		//TODO: check walls
		switch(pos) {
		case "top":
			setImage(ImgResources.WALLTOP);
			break;
		case "bottom":
			setImage(ImgResources.WALLBOT);
			break;
		case "left":
		case "right":
			setImage(ImgResources.WALL);
			break;
		}
	}

	public String getPosition(){
		return this.position;
	}

	@Override
	protected boolean hit(int damage) {
		return false;
	}

}
