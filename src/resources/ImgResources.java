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
	//UI
	MAINMENUBG("menuBackground.png"),
	//ENVIRONMENT
	FLOOR("floor.png"),
	CONSOLE1("console1.png"),
	CONSOLE2("console2.png"),
	CONSOLE3("console3.png"),
	STAIRSBOT("stairsBot.png"),
	STAIRSLEFT("stairsLeft.png"),
	STAIRSRIGHT("stairsRight.png"),
	STAIRSTOP("stairsTop.png"),
	WALL("wall.png"),
	WALLBOT("wallBot.png"),
	WALLTOP("wallTop.png"),
	//PLAYER
	PLAYERDOWN("playerDown.png");

	public final Image img;

	ImgResources(String resourceName) {
		img = new Image(ImgResources.class.getResource(resourceName).toString());
	}
}
