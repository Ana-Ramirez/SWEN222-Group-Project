package logic;

import entities.StationaryEntity;
import resources.ImgResources;

public class Wall extends StationaryEntity{

	String position = null;

	public Wall(String pos, double x, double y, int width, int height){
		super("Wall", x, y, width, height, null);

		//TODO: check walls
		switch(pos) {
		case "top":
			//setImage(ImgResources.WALLTOP.img);
			//break;
		case "bottom":
			//setImage(ImgResources.WALLBOT.img);
			//break;
		case "left":
		case "right":
			setImage(ImgResources.WALL);
			break;
		}
	}

	public String getPosition(){
		return this.position;
	}

}
