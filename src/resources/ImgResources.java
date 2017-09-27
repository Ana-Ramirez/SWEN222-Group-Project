package resources;

import javafx.scene.image.Image;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * Contains the image resources for the game.
 * Adapted from WheatRun code.
 * @author Patrick
 *
 */
public enum ImgResources {
	MAINMENUBG("menuBackground.jpg");

	public final Image img;

	//TODO adapt this from java.awt to javafx
	  ImgResources(String resourceName) {
	    //try{ 
	    	img = null;//ImageIO.read(ImgResources.class.getResource(resourceName)); 
	    //}
	    //catch (IOException e){ throw new Error(e); }
	  }
}
