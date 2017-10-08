package logic;

import javafx.scene.image.Image;
import resources.ImgResources;

public class Wall {
	
	String position = null;
	Image image = null;
	
	public Wall(String pos){
		this.position = pos;
		setImage();
	}
	
	private void setImage(){
		if(this.position == "top"){ this.image = ImgResources.WALLTOP.img; }
		else if(this.position == "bottom"){ this.image = ImgResources.WALLBOT.img; }
		else if(this.position == "left" || this.position == "right"){ this.image = ImgResources.WALL.img; }
	}
	
	public String getPosition(){
		return this.position;
	}
	
}
