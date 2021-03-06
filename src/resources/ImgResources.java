package resources;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

import javafx.embed.swing.SwingFXUtils;
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
	GUNLEFT("gunLeft.png"),
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
	PLAYERDOWN("playerDown.png"),
	PLAYERDOWNINJURED("playerDownInjured.png");

	public final URL imgPath;
	public Image img;

	/**
	 * Used to retrieve the images
	 * @param resourceName name of the image
	 */
	ImgResources(String resourceName) {
		imgPath = getClass().getResource(resourceName);
	}
	
	/**
	 * @return width of the image
	 */
	public int getWidth() {
		try {
			return ImageIO.read(imgPath).getWidth();
		} catch (IOException e) {
			return 0;
		}
	}
	
	/**
	 * @return height of the image
	 */
	public int getHeight() {
		try {
			return ImageIO.read(imgPath).getHeight();
		} catch (IOException e) {
			return 0;
		}
	}
	
	/**
	 * sets the img field to the current imgPath
	 */
	public void setImage() {
		try {
			img = SwingFXUtils.toFXImage(ImageIO.read(imgPath), null);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
