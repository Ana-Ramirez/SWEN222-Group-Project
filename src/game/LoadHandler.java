package game;

import java.io.File;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import saveLoad.GameData;
import saveLoad.Load;

public class LoadHandler<T extends Event> implements EventHandler<ActionEvent>{

	private Stage stage;
	
	public LoadHandler(Stage stage) {
		this.stage = stage;
	}
	
	@Override
	public void handle(ActionEvent event) {
		FileChooser fc = new FileChooser();
		
		File file = fc.showOpenDialog(new Stage());
		
		if(file == null) {
			return;
		}
		Load load = new Load(file.getAbsolutePath());
		GameData gameData = (GameData) load.loadGame();
		
		Game game = new Game();
		game.setLevels(gameData.getLevels());
		game.setPlayer(gameData.getPlayer());
		game.setCurrentLevel(gameData.getCurrentLevel());
		
		try {
			game.start(stage);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
