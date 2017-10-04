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
import view.PauseMenu;
import view.Renderer;

/**
 * Main controller class
 * 
 * @author Tim Gastrell
 *
 */
public class Game extends Application implements Serializable{
	
	/**
	 * Player dimensions
	 */
	int playerWidth = 10, playerHeight = 10;
	
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
	
	/**
	 * Direction of player movement
	 */
	
	private boolean goUp, goDown, goLeft, goRight;
	
	/**
	 * Constructs a new Game object
	 */
	public Game() {
		this.renderer = new Renderer();
		this.player = new Player(0,0, playerWidth, playerHeight);
		generateLevels();
	}
	
	/**
	 * Initialised list of levels
	 */
	private void generateLevels() {
		//TODO Initialise levels
		levels.add(new Level(player));
	}
	
	/**
	 * Inspired by https://stackoverflow.com/questions/29962395/how-to-write-a-keylistener-for-javafx
	 */
	@Override
	public void start(Stage stage) throws Exception {
		
		Scene scene = renderer.getScene();
		
		//Game loop
		AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                int dx = 0, dy = 0;

                if (goUp) dy -= 1;
                if (goDown) dy += 1;
                if (goLeft)  dx -= 1;
                if (goRight)  dx += 1;
                
                player.moveBy(dx, dy);
            }
        };
		
    	PauseMenu pm = new PauseMenu(this);

        //Key listening
		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				switch (event.getCode()) {
					case W : goUp = true; break;
					case S : goDown = true; break;
					case A : goLeft = true; break;
					case D : goRight = true; break;
					case ESCAPE : 
	                	timer.stop(); 
	                	try {
							pm.start(stage);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					//TODO PICK UP ITEM
					default : break;
				}
			}
		});
		
		scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case W: goUp = false; break;
                    case S: goDown = false; break;
                    case A: goLeft  = false; break;
                    case D: goRight  = false; break;

                    default : break;
                }
            }
        });
		
		//TODO MOUSE HANDLING
		
		stage.setScene(scene);
		stage.show();
		
		
        timer.start();
	}	
}
