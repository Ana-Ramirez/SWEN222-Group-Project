package viewTests;

import javafx.application.Application;
import javafx.stage.Stage;

public class TestApplication extends Application{
	private Stage primaryStage;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		this.primaryStage = primaryStage;
	}
	
	public Stage getStage(){
		return primaryStage;
	}

}
