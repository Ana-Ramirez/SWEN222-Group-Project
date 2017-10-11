package game;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import entities.Player;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import logic.Level;
import logic.Room;
import resources.ImgResources;
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
	private final int playerWidth = 1, playerHeight = 1;

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
	private boolean goUp, goDown, goLeft, goRight, waitForRelease = false;

	/**
	 * Current level
	 */
	private Level currentLevel;

	private int tickNumber = 0;

	private AnimationTimer timer;

	/**
	 * Constructs a new Game object
	 */
	public Game() {
		this.player = new Player(50,50, 32, 32, ImgResources.PLAYERDOWN);
		generateLevels();
		this.renderer = new Renderer(currentLevel);
		renderer.initialDraw();
	}

	/**
	 * Constructor that does not initialise the renderer, for testing purposes
	 * @param Differentiates from normal constructor
	 */
	public Game(boolean x) {
		this.player = new Player(50,50, 32, 32, ImgResources.PLAYERDOWN);
		generateLevels();
	}

	/**
	 * Initialised list of levels
	 */
	private void generateLevels() {
		levels = new ArrayList<Level>();
		Level level1 = null;
		try {
			level1 = new Level(player);
			levels.add(level1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if(level1 != null) {
			currentLevel = level1;
		}


	}

	/**
	 * Inspired by https://stackoverflow.com/questions/29962395/how-to-write-a-keylistener-for-javafx
	 */
	@Override
	public void start(Stage stage) throws Exception {

		Scene scene = renderer.getScene();


		//Game loop
		timer = new AnimationTimer() {
			@Override
			public void handle(long now) {

				//Processing move commands
				int dx = 0, dy = 0;
				if (goUp) dy -= 1;
				if (goDown) dy += 1;
				if (goLeft)  dx -= 1;
				if (goRight)  dx += 1;

				Room oldRoom = currentLevel.getCurrentRoom();
				currentLevel.getCurrentRoom().tick(dx, dy, tickNumber);
				Room newRoom = currentLevel.getCurrentRoom();

				renderer.repaint();

				if (oldRoom != newRoom) {
					currentLevel.getCurrentRoom().tick(-dx, -dy, tickNumber);
					waitForRelease = true;
				}

				tickNumber++;
			}
		};

    	PauseMenu pm = new PauseMenu(this);

        //Key listening
		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				switch (event.getCode()) {
					case W : goUp = true && !waitForRelease; break;
					case S : goDown = true && !waitForRelease; break;
					case A : goLeft = true && !waitForRelease; break;
					case D : goRight = true && !waitForRelease; break;
					case DIGIT1 : player.selectItem(0); break;
					case DIGIT2 : player.selectItem(1); break;
					case DIGIT3 : player.selectItem(2); break;
					case E : currentLevel.getCurrentRoom().pickupItem(); break;
					case X : player.drop(); break;
					case ESCAPE :
	                	timer.stop();
	                	try {
							pm.start(stage);
						} catch (Exception e) {
							// TODO Auto-generated catch block
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
                waitForRelease = false;
            }
        });

		//TODO MOUSE HANDLING
		scene.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				currentLevel.getCurrentRoom().use((float) event.getX(), (float) event.getY());
			}


		});

		stage.setScene(scene);
		stage.show();


        timer.start();
	}

	public Renderer getRenderer() {
		return renderer;
	}

	public void setRenderer(Renderer renderer) {
		this.renderer = renderer;
	}

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
