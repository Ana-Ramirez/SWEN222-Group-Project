package view;

import game.Game;
import game.QuitToMenuHandler;
import game.ResumeHandler;
import game.SaveHandler;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Menu shown while game is paused
 * @author Patrick
 *
 */
public class PauseMenu extends Application{
	private Scene scene;
	private StackPane root;
	private VBox vb;
	private Stage primaryStage;
	private Game game;

	@Override
	public void start(Stage primaryStage) throws Exception {
		this.primaryStage = primaryStage;

		root = new StackPane();

		Canvas canvas = new Canvas(800, 600);
		GraphicsContext g = canvas.getGraphicsContext2D();

		//Draws the black background
		drawBG(g);
		root.getChildren().add(canvas);

		//VBox for title and buttons in one column
		vb = new VBox();
		vb.setSpacing(8);
		vb.setAlignment(Pos.CENTER);

		root.getChildren().add(vb);

		//Writes PAUSED at the top of the screen
		Text title = new Text("PAUSED");
	    title.setFont(Font.font("Arial", FontWeight.BOLD, 48));
	    title.setFill(Color.WHITE);
	    vb.getChildren().add(title);

		scene = new Scene(root, 800, 600);

		primaryStage.setScene(scene);
		primaryStage.show();
		
		//Resumes the game when the escape key is pressed again
		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				switch(event.getCode()) {
				case ESCAPE : try {
					game.start(primaryStage);
				} catch (Exception e) {
					e.printStackTrace();
				} 
				break;
				default:
					break;
				}			
			}			
		});

		//Adds the buttons
		resumeGameButton();
		saveGameButton();
		quitToMenuButton();
	}

	/**
	 * Constructor
	 * @param game to return to when game is resumed
	 */
	public PauseMenu(Game game){
		this.game = game;
	}

	/**
	 * Adds a resume game button
	 */
	private void resumeGameButton(){
		Button btn = new Button();
		btn.setText("Resume Game");
		btn.setOnAction(new ResumeHandler<ActionEvent>(game, primaryStage));
		vb.getChildren().add(btn);
	}

	/**
	 * Adds a save game button
	 * @param game
	 */
	private void saveGameButton(){
		Button btn = new Button();
		btn.setText("Save Game");
		btn.setOnAction(new SaveHandler<ActionEvent>(game));
		vb.getChildren().add(btn);
	}

	/**
	 * Adds a quit to menu button
	 */
	private void quitToMenuButton(){
		Button btn = new Button();
		btn.setText("Quit to Main Menu");
		btn.setOnAction(new QuitToMenuHandler<ActionEvent>(primaryStage, game));
		vb.getChildren().add(btn);
	}

	/**
	 * Draws the background for the pause menu (solid black)
	 * @param g
	 */
	private void drawBG(GraphicsContext g) {
		g.setFill(Color.BLACK);
		g.fillRect(0.0, 0.0, 800, 600);
	}
}
