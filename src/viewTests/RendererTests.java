package viewTests;

import java.awt.geom.Rectangle2D;

import org.junit.Test;

import entities.Player;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import logic.Level;
import resources.ImgResources;
import view.Renderer;

/**
 * Tests for the renderer class
 * @author Patrick
 * <b>TESTS ADAPTED FROM CODE FOUND AT https://stackoverflow.com/a/18980655</b>
 */
public class RendererTests{
	
	/**
	 * Tries to call the initial draw method
	 * @throws InterruptedException
	 */
	@Test
	public void initialDrawTest() throws InterruptedException {
		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				new JFXPanel(); // Initializes the JavaFx Platform
				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						try {
							Renderer r = new Renderer(new Level(new Player(new Rectangle2D.Double(400, 200, 40, 40), ImgResources.PLAYERDOWN)));
							r.initialDraw();
						} 
						catch (Exception e) {
							e.printStackTrace();
						}

					}
				});
			}
		});
		thread.start();// Initialize the thread
	}
	
	/**
	 * Tries to call the initial draw method
	 * @throws InterruptedException
	 */
	@Test
	public void animateSwordTest() throws InterruptedException {
		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				new JFXPanel(); // Initializes the JavaFx Platform
				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						try {
							Renderer r = new Renderer(new Level(new Player(new Rectangle2D.Double(400, 200, 40, 40), ImgResources.PLAYERDOWN)));
							r.animateSword(true);
							r.animateSword(false);
						} 
						catch (Exception e) {
							e.printStackTrace();
						}

					}
				});
			}
		});
		thread.start();// Initialize the thread
	}
	
	/**
	 * Tries to call the initial draw method
	 * @throws InterruptedException
	 */
	@Test
	public void repaintTest() throws InterruptedException {
		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				new JFXPanel(); // Initializes the JavaFx Platform
				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						try {
							Renderer r = new Renderer(new Level(new Player(new Rectangle2D.Double(400, 200, 40, 40), ImgResources.PLAYERDOWN)));
							r.repaint();
						} 
						catch (Exception e) {
							e.printStackTrace();
						}

					}
				});
			}
		});
		thread.start();// Initialize the thread
	}
	
	/**
	 * Tries to call the initial draw method
	 * @throws InterruptedException
	 */
	@Test
	public void getSceneTest() throws InterruptedException {
		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				new JFXPanel(); // Initializes the JavaFx Platform
				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						try {
							Renderer r = new Renderer(new Level(new Player(new Rectangle2D.Double(400, 200, 40, 40), ImgResources.PLAYERDOWN)));
							Scene scene = r.getScene();
							assert (scene != null);
						} 
						catch (Exception e) {
							e.printStackTrace();
						}

					}
				});
			}
		});
		thread.start();// Initialize the thread
	}
}
