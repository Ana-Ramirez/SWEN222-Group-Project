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
 * Menu shown when you lose
 * @author Patrick / Tim
 */
public class GameOverMenu extends Application{
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

		drawBG(g);
		root.getChildren().add(canvas);

		//Using a vbox to have each element display in a single column
		vb = new VBox();
		vb.setSpacing(8);
		vb.setAlignment(Pos.CENTER);

		root.getChildren().add(vb);

		//Adds GAME OVER to the top of the screen
		Text title = new Text("GAME OVER");
	    title.setFont(Font.font("Arial", FontWeight.BOLD, 48));
	    title.setFill(Color.RED);
	    vb.getChildren().add(title);

		scene = new Scene(root, 800, 600);

		primaryStage.setScene(scene);
		primaryStage.show();

		//Add the buttons
		loadGameButton();
		quitToMenuButton();
	}

	public GameOverMenu(Game game){
		this.game = game;
	}

	/**
	 * Adds a load game button
	 */
	private void loadGameButton(){
		Button btn = new Button();
		btn.setText("Load Game");
		btn.setOnAction(new LoadHandler<ActionEvent>(primaryStage));
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
	 * Draws the background for the game over menu
	 * @param g
	 */
	private void drawBG(GraphicsContext g) {
		g.setFill(Color.BLACK);
		g.fillRect(0.0, 0.0, 800, 600);
	}
}
