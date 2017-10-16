package view;

import game.Game;
import game.LoadHandler;
import game.QuitToMenuHandler;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Menu shown when you win
 * @author Patrick 
 *
 */
public class YouWinMenu extends Application{
	private Scene scene;
	private StackPane root;
	private VBox vb;
	private Stage primaryStage;
	private Game game;

	@Override
	public void start(Stage primaryStage) throws Exception {
		this.primaryStage = primaryStage;

		//For drawing the UI elements on top of the background
		root = new StackPane();

		Canvas canvas = new Canvas(800, 600);
		GraphicsContext g = canvas.getGraphicsContext2D();

		//Draws the black background
		drawBG(g);
		root.getChildren().add(canvas);

		//For drawing the title and buttons in one column
		vb = new VBox();
		vb.setSpacing(8);
		vb.setAlignment(Pos.CENTER);

		root.getChildren().add(vb);

		//Displays YOU WIN as the title
		Text title = new Text("YOU WIN");
	    title.setFont(Font.font("Arial", FontWeight.BOLD, 48));
	    title.setFill(Color.GREEN);
	    vb.getChildren().add(title);

		scene = new Scene(root, 800, 600);

		primaryStage.setScene(scene);
		primaryStage.show();

		//Adds the buttons
		quitToMenuButton();
	}

	/**
	 * Constructor must take a game object so that the quitToMenu button will work
	 * @param game
	 */
	public YouWinMenu(Game game){
		this.game = game;
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
	 * Draws the background for the you win menu (solid black)
	 * @param g
	 */
	private void drawBG(GraphicsContext g) {
		g.setFill(Color.BLACK);
		g.fillRect(0.0, 0.0, 800, 600);
	}
}
