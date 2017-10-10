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
	private final int playerWidth = 10, playerHeight = 10;
	
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
	 * Current level
	 */
	private Level currentLevel;
	
	/**
	 * Current Room
	 */
	private Room currentRoom;
	
	/**
	 * Constructs a new Game object
	 */
	public Game() {
		this.renderer = new Renderer();
		this.player = new Player(0,0, playerWidth, playerHeight, ImgResources.PLAYERDOWN.img);
		renderer.setPlayer(player);
		generateLevels();
		currentRoom.setPlayer(player);
		renderer.initialDraw();
	}
	
	/**
	 * Constructor that does not initialise the renderer, for testing purposes
	 * @param Differentiates from normal constructor 
	 */
	public Game(boolean x) {
		this.player = new Player(0,0, playerWidth, playerHeight, ImgResources.PLAYERDOWN.img);
		generateLevels();
		currentRoom.setPlayer(player);
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
		
		currentRoom = currentLevel.getRoom(1);
		
		if(currentRoom == null) {
			System.out.println("Room Null");
			System.exit(0);
		}
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
				
				//Processing move commands
				int dx = 0, dy = 0;
				if (goUp) dy -= 1;
				if (goDown) dy += 1;
				if (goLeft)  dx -= 1;
				if (goRight)  dx += 1;    
				
				
				//Checks for the current room and updates if necessary
				if(!currentLevel.getCurrentRoom().equals(currentRoom)) {
					currentRoom = currentLevel.getCurrentRoom();
				}
				
				currentRoom.tick(dx, dy);
				
				renderer.repaint();
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
					case DIGIT1 : player.selectItem(0); break;
					case DIGIT2 : player.selectItem(1); break;
					case DIGIT3 : player.selectItem(2); break;
					case E : currentRoom.pickupItem(); break;
					case X : player.drop();
					case ESCAPE : 
	                	timer.stop(); 
	                	try {
							pm.start(stage);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
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
		scene.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				currentRoom.attack((float) event.getX(), (float) event.getY());
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

	public Room getCurrentRoom() {
		return currentRoom;
	}

	public void setCurrentRoom(Room currentRoom) {
		this.currentRoom = currentRoom;
	}	
	
	
}
