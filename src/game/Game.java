package game;

import java.io.Serializable;
import java.util.List;

import entities.Player;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import logic.Level;
import view.Renderer;

/**
 * Main controller class
 * 
 * @author Tim Gastrell
 *
 */
public class Game extends Application implements Serializable{
	
	/**
	 * View objects
	 */
	private Renderer renderer;
	
	/**
	 * Player object
	 */
	private Player player;
	
	/**
	 * All the levels in the game
	 */
	private List<Level> levels;
	
	private boolean goUp, goDown, goLeft, goRight;
	
	
	public void pause() {
		
	}
	
	public void resume() {
		
	}
	
	/**
	 * Initialised list of levels
	 */
	private void generateLevels() {
		//TODO Initialise levels
	}
	
	/**
	 * Inspired by https://stackoverflow.com/questions/29962395/how-to-write-a-keylistener-for-javafx
	 */
	@Override
	public void start(Stage stage) throws Exception {
		generateLevels();
		if(levels.isEmpty()) throw new GameException("No level data found");
		this.renderer = new Renderer();
		this.player = new Player(0,0);
		
		Scene scene = renderer.getScene();
		
		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				switch (event.getCode()) {
					case W : goUp = true; break;
					case S : goDown = true; break;
					case A : goLeft = true; break;
					case D : goRight = true; break;
					default : break;
				}
			}
		});
		
		scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case W:    goUp = false; break;
                    case S:  goDown = false; break;
                    case A:  goLeft  = false; break;
                    case D: goRight  = false; break;
                    case ESCAPE : 
                    default : break;
                }
            }
        });
		
		//TODO MOUSE HANDLING\
		
		stage.setScene(scene);
		stage.show();
		
		AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                int dx = 0, dy = 0;

                if (goUp) dy -= 1;
                if (goDown) dy += 1;
                if (goLeft)  dx += 1;
                if (goRight)  dx -= 1;
                
                player.move(dx, dy);
            }
        };
        timer.start();
	}	
}
