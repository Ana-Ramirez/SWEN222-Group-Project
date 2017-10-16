package viewTests;

import static org.junit.Assert.*;

import java.awt.geom.Rectangle2D;

import org.junit.Test;

import entities.Player;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import logic.Level;
import resources.ImgResources;
import view.Renderer;

/**
 * Tests for the renderer class
 * @author Patrick
 *
 */
public class RendererTests extends Application{
	
	class TestRenderer implements interfaces.Renderer{
		
		public TestRenderer(Level level){
			
		}

		@Override
		public void initialDraw() {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void animateSword(boolean fromGame) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void repaint() {
			// TODO Auto-generated method stub
			
		}

		@Override
		public Scene getScene() {
			// TODO Auto-generated method stub
			return null;
		}
		
	}

	/**
	 * Makes sure the constructor can be called without error
	 */
	@Test
	public void initialDrawTest(){
		TestApplication.launch(null);
		Renderer r = new Renderer(new Level(new Player(new Rectangle2D.Double(400, 200, 40, 40), ImgResources.PLAYERDOWN)));
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		initialDrawTest();
	}
}
