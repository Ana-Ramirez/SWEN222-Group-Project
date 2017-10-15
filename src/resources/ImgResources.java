package resources;

import javafx.scene.image.Image;

/**
 * Contains the image resources for the game.
 * Adapted from WheatRun code.
 * @author Patrick
 *
 */
public enum ImgResources {

	//UI
	MAINMENUBG("menuBackground.png"),
	INVENTORYBOX("inventoryBox.png"),
	GUN("gun.png"),
	KEY("key.png"),
	LIFE("head.png"),
	AMMO("ammo.png"),
	//WEAPONS
	SWORDLEFTUP("swordUpLeft.png"),
	SWORDLEFTDIAG("swordDiagLeft.png"),
	SWORDLEFTDOWN("swordDownLeft.png"),
	SWORDRIGHTUP("swordUpRight.png"),
	SWORDRIGHTDIAG("swordDiagRight.png"),
	SWORDRIGHTDOWN("swordDownRight.png"),
	GUNLEFT("gunLeft.png"),
	GUNRIGHT("gunRight.png"),
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
	MONSTERINJURED("monsterInjured.png"),
	MAO("mao.png"),
	MAOHIT("maoRed.png"),
	MAOINJURED("maoInjured.png"),
	//PROJECTILES
	BULLET("bullet.png"),
	HLASER("lazerHoriz.png"),
	VLASER("lazerVert.png"),
	//CONSUMABLES
	POTION("potion.png"),
	//PLAYER
	PLAYERDOWN("playerDown.png");

	public final String imgPath;
	public Image img;

	ImgResources(String resourceName) {
		imgPath = ImgResources.class.getResource(resourceName).toString();
	}
	
	public void setImage() {
		img = new Image(imgPath);
	}
}
