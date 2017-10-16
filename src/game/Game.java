package game;

import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

import entities.PlayerEntity;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.BoundingBox;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import logic.Level;
import resources.ImgResources;
import view.GameOverMenu;
import view.PauseMenu;
import view.Renderer;
import view.YouWinMenu;

/**
 * Main controller class
 *
 * @author Tim Gastrell
 *
 */
public class Game extends Application {

	/**
	 * Player object
	 */
	private PlayerEntity player;

	/**
	 * All the levels in the game
	 */
	private List<Level> levels;

	/**
	 * Direction of player movement
	 */
	private boolean goUp, goDown, goLeft, goRight= false;

	/**
	 * Current level
	 */
	private Level currentLevel;
	
	/**
	 * total number of ticks
	 */
	private int tickNumber = 0;
	
	/**
	 * Timer for the main Game loop
	 */
	private AnimationTimer timer;
	
	/**
	 * Pause Menu
	 */
	private PauseMenu pm;
	
	/**
	 * Game Over Menu
	 */
	private GameOverMenu gom;
	
	/**
	 * You Win Menu
	 */
	private YouWinMenu ywm;
	
	/**
	 * Renderer
	 */
	private Renderer renderer;
	
	/**
	 * Constructs a new Game object
	 */
	public Game() {
		this.player = new PlayerEntity(new Rectangle2D.Double(50, 50, 32, 48), ImgResources.PLAYERDOWN);
		generateLevels();
	}


	/**
	 * Initialises list of levels
	 */
	private void generateLevels() {
		levels = new ArrayList<Level>();
		Level level1 = null;
		try {
			level1 = new Level(player);
			levels.add(level1);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if(level1 != null) {
			currentLevel = level1;
		}
	}

	/**
	 * Inspired by https://stackoverflow.com/questions/29962395/how-to-write-a-keylistener-for-javafx
	 * Main game function.
	 */
	@Override
	public void start(Stage stage) throws Exception {
		//Initialise Renderer
		this.renderer = new Renderer(currentLevel);
		renderer.initialDraw();

		Scene scene = renderer.getScene();
		this.gom = new GameOverMenu(this);
    	this.pm = new PauseMenu(this);
    	this.ywm = new YouWinMenu(this);


		//Game loop
		initialiseTimer(stage);


		keyListener(scene, stage);
		mouseListener(scene);

		stage.setScene(scene);
		stage.show();


        timer.start();
	}
	
	/**
	 * Initialises game time (Game Loop)
	 * @param stage
	 */
	private void initialiseTimer(Stage stage) {
		timer = new AnimationTimer() {
			@Override
			public void handle(long now) {				
				//Processing move commands
				int dx = 0, dy = 0;
				if (goUp) dy -= 1;
				if (goDown) dy += 1;
				if (goLeft){
					dx -= 1; 
					currentLevel.setLeft(true);
				}
				if (goRight){  
					dx += 1; 
					currentLevel.setLeft(false);
				}
				
				if(!player.isAlive()) {
					try {
						this.stop();
						gom.start(stage);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				
				if (!currentLevel.getBoss().isAlive()){
					try {
						this.stop();
						ywm.start(stage);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				
				currentLevel.getCurrentRoom().tick(dx, dy, tickNumber);
				renderer.repaint();
				
				tickNumber++;
			}
		};
	}
	
	/**
	 * Initialises mouse listener
	 * @param scene
	 */
	private void mouseListener(Scene scene) {
		scene.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				currentLevel.getCurrentRoom().use((float) event.getX(), (float) event.getY());
				renderer.animateSword(true);
			}


		});
	}
	
	/**
	 * Initialises key listener
	 * @param scene
	 * @param stage
	 */
	private void keyListener(Scene scene, Stage stage) {
		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				switch (event.getCode()) {
					case W : goUp = true; break;
					case S : goDown = true; break;
					case A : goLeft = true; break;
					case D : goRight = true; break;
					case DIGIT1 : player.selectItem(0); break;
					case DIGIT2 : player.selectItem(1); break;
					case DIGIT3 : player.selectItem(2); break;
					case E : currentLevel.getCurrentRoom().pickupItem(); break;
					case X : currentLevel.getCurrentRoom().dropItem(); break;
					case ESCAPE :
	                	timer.stop();
	                	try {
							pm.start(stage);
						} catch (Exception e) {
							e.printStackTrace();
						}
	                	break;
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
	}

//==================GETTERS AND SETTERS====================//
	
	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public List<Level> getLevels() {
		return levels;
	}

	public void setLevels(List<Level> levels) {
		this.levels = levels;
	}

	public Level getCurrentLevel() {
		return currentLevel;
	}

	public void setCurrentLevel(Level currentLevel) {
		this.currentLevel = currentLevel;
	}
	
	

}
