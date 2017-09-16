package saveLoad;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import game.Game;

public class Save {
	static Game game;
	
	public Save(Game game) {
		this.game = game;
	}
	
	private void saveGame() {
		try {
			//Write file to disk
			FileOutputStream f_out = new FileOutputStream(new File("game_save"));
			
			//Write object to file
			ObjectOutputStream obj_out = new ObjectOutputStream(f_out);
			
			//Pass object to object stream
			obj_out.writeObject(game);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
