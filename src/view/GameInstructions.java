package view;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Displays the instructions for controls and game in a new scene
 * 
 * @author Tim Gastrell
 *
 */
public class GameInstructions extends Application {
	
	private StackPane root;
	
	private Scene scene;
	
	private VBox titleVb, controlsVb, objectiveVb;
	

	@Override
	public void start(Stage primaryStage) throws Exception {
		root = new StackPane();

		Canvas canvas = new Canvas(800, 600);
		GraphicsContext g = canvas.getGraphicsContext2D();

		root.getChildren().add(canvas);

		
		drawBG(g);

		drawTitle();
	    
		drawControls();
	    
		drawObjective();		

		scene = new Scene(root, 800, 600);
		
		setInputHandling(primaryStage);

		primaryStage.setScene(scene);
		primaryStage.show();		
	}
	
	/**
	 * Initialises input handling
	 * 
	 * @param primaryStage
	 */
	private void setInputHandling(Stage primaryStage) {
		scene.setOnKeyReleased(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {	
				exitToMenu(primaryStage);
			}
			
		});
		
		scene.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				exitToMenu(primaryStage);
			}


		});
	}
	
	/**
	 * Exits current scene to main menu
	 * 
	 * @param primaryStage
	 */
	private void exitToMenu(Stage primaryStage) {		
		try {
			stop();
			MainMenu m = new MainMenu();
			m.start(primaryStage);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Draws title
	 */
	private void drawTitle() {
		titleVb = new VBox();
		titleVb.setSpacing(8);
		titleVb.setAlignment(Pos.TOP_CENTER);

		root.getChildren().add(titleVb);

		Text title = new Text("GAME INSTRUCTIONS");
	    title.setFont(Font.font("Arial", FontWeight.BOLD, 48));
	    title.setFill(Color.YELLOW);
	    titleVb.getChildren().add(title);
	}
	
	/**
	 * Draws the control information
	 */
	private void drawControls() {
		controlsVb = new VBox();
		controlsVb.setSpacing(8);
		controlsVb.setAlignment(Pos.CENTER_LEFT);
		controlsVb.setPadding(new Insets(15));
		
		root.getChildren().add(controlsVb);

		Text controlsHead = new Text("Controls:");
		controlsHead.setFont(Font.font("Arial", FontWeight.MEDIUM, 30));
		controlsHead.setFill(Color.YELLOW);
		controlsVb.getChildren().add(controlsHead);
		
		Text controls = new Text("Movement - W A S D\n"
								+"Attack/Use - Left Mouse Button\n"
								+ "Aim - Mouse Mouse\n"
								+ "Pickup Item - E\n"
								+ "Drop Item - X\n"
								+ "Equip Item - 1 and 2\n"
								+ "Pause - ESC\n");
		controls.setFont(Font.font("Arial", FontWeight.LIGHT, 20));
		controls.setFill(Color.YELLOW);
		controlsVb.getChildren().add(controls);
	}
	
	/**
	 * Draws the objective information
	 */
	private void drawObjective() {
		objectiveVb = new VBox();
		objectiveVb.setSpacing(8);
		objectiveVb.setAlignment(Pos.CENTER_RIGHT);
		objectiveVb.setPadding(new Insets(15));
		
		root.getChildren().add(objectiveVb);
		
		Text objectiveHead = new Text("Tips:                                 ");
		objectiveHead.setFont(Font.font("Arial", FontWeight.MEDIUM, 30));
		objectiveHead.setFill(Color.YELLOW);
		objectiveVb.getChildren().add(objectiveHead);
		
		Text objective = new Text("Stay Alive.\n"
								+ "Try Keys on multiple doors.\n"
								+ "You may only hold 3 items\n"
								+ "So choose wisely.\n"
								+ "Defeat re-animated cyber-Mao to win.\n\n\n"
								+ "Press any key to exit.");
		objective.setFont(Font.font("Arial", FontWeight.LIGHT, 20));
		objective.setFill(Color.YELLOW);
		objectiveVb.getChildren().add(objective);
	}
	
	/**
	 * Draws the background for the pause menu
	 * @param g
	 */
	private void drawBG(GraphicsContext g) {
		g.setFill(Color.BLACK);
		g.fillRect(0.0, 0.0, 800, 600);
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
