package saveLoad;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import game.Game;

/**
 * Functional class that writes the passed game object to a file.
 * @author Tim Gastrell
 *
 */
public class Save<T extends Object> {
	/**
	 * Game object to be written to file
	 */
	private T game;
	
	/**
	 * Constructs a new Save object
	 * @param game to be written
	 */
	public Save(T game) {
		this.game = game;
	}
	
	/**
	 * Writes game to file
	 */
	public void saveGame() {
		try {
			//Write file to disk
			FileOutputStream f_out = new FileOutputStream(new File("game_save"));
			
			//Write object to file
			ObjectOutputStream obj_out = new ObjectOutputStream(f_out);
			
			//Pass object to object stream
			obj_out.writeObject(game);
			
			obj_out.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
