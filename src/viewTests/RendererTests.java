package viewTests;

import static org.junit.Assert.*;

import java.awt.geom.Rectangle2D;

import org.junit.Test;

import entities.Player;
import logic.Level;
import resources.ImgResources;
import view.Renderer;

/**
 * Tests for the renderer class
 * @author Patrick
 *
 */
public class RendererTests {

	/**
	 * Makes sure the constructor can be called without error
	 */
	@Test
	public void constructorTest(){
		ImgResources.values();
		Renderer r = new Renderer(new Level(new Player(new Rectangle2D.Double(400, 200, 40, 40), ImgResources.PLAYERDOWN)));
		//r.initialDraw();
	}
}
