package view;

import game.GameInstructionHandler;
import game.LoadHandler;
import game.QuitHandler;
import game.StartHandler;
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
import resources.ImgResources;

/**
 * Main menu shown to the player before they enter the game
 * @author Patrick
 *
 */
public class MainMenu extends Application{
	private Scene scene;
	private StackPane root;
	private VBox vb;
	private Stage primaryStage;

	/**
	 * Start method. Required by JavaFX applications.
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		this.primaryStage = primaryStage;
		primaryStage.setTitle("Trouble in ChinaTown");

		//Initialises the values for the ImgResources class so the images will display
		for (int i = 0; i < ImgResources.values().length; i++) {
			ImgResources.values()[i].setImage();
		}

		//A stackpane is used to stack the UI elements on top of the background
		root = new StackPane();

		Canvas canvas = new Canvas(816, 480);
		GraphicsContext g = canvas.getGraphicsContext2D();

		//Draw the background
		drawBG(g);
		root.getChildren().add(canvas);

		//VBox for 1 column of title and buttons
		vb = new VBox();
		vb.setSpacing(8);
		vb.setAlignment(Pos.CENTER);

		root.getChildren().add(vb);

		//Title for the game
		Text title = new Text("TROUBLE IN CHINATOWN");
	    title.setFont(Font.font("Arial", FontWeight.BOLD, 48));
	    title.setFill(Color.WHITE);
	    vb.getChildren().add(title);

		scene = new Scene(root, 816, 480);

		primaryStage.setScene(scene);
		primaryStage.show();

		//Add the buttons
		startGameButton();
		loadGameButton();
		gameInstructionsButton();
		quitGameButton();
	}

	/**
	 * Adds a start game button
	 */
	private void startGameButton(){
		Button btn = new Button();
		btn.setText("Start New Game");
		btn.setOnAction(new StartHandler<ActionEvent>(primaryStage));
		vb.getChildren().add(btn);
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
	 * Adds a quit game button
	 */
	private void quitGameButton(){
		Button btn = new Button();
		btn.setText("Quit");
		btn.setOnAction(new QuitHandler<ActionEvent>());
		vb.getChildren().add(btn);
	}
	
	/**
	 * Adds a game instructions button
	 */
	private void gameInstructionsButton() {
		Button btn = new Button();
		btn.setText("Game Instructions");
		btn.setOnAction(new GameInstructionHandler<ActionEvent>(primaryStage));
		vb.getChildren().add(btn);
	}

	/**
	 * Draws the menu background
	 */
	private void drawBG(GraphicsContext g) {
		g.drawImage(ImgResources.MAINMENUBG.img, 0.0, 0.0, 816.0, 480.0);
	}

	/**
	 * Main method for the application. Calls the Start() method indirectly.
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);
	}
}
