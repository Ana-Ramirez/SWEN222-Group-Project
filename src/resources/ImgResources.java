package resources;

import java.io.Serializable;

import javafx.scene.image.Image;

/**
 * Contains the image resources for the game.
 * Adapted from WheatRun code.
 * @author Patrick
 *
 */
public enum ImgResources implements Serializable{
	//UI
	MAINMENUBG("menuBackground.png"),
	INVENTORYBOX("inventoryBox.png"),
	GUN("gun.png"),
	KEY("key.png"),
	LIFE("head.png"),
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
	//ENEMIES
	MONSTER("monster.png"),
	MAO("mao.png"),
	//PROJECTILES
	BULLET("bullet.png"),
	HLASER("lazerHoriz.png"),
	VLASER("lazerVert.png"),
	//CONSUMABLES
	POTION("potion.png"),
	//PLAYER
	PLAYERDOWN("playerDown.png");

	public final transient Image img;

	ImgResources(String resourceName) {
		img = new Image(ImgResources.class.getResource(resourceName).toString());
	}
}
