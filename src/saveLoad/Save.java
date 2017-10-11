package saveLoad;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * Functional class that writes the passed game object to a file.
 * @author Tim Gastrell
 *
 */
public class Save {
	/**
	 * Game object to be written to file
	 */
	private GameData gameData;
	
	/**
	 * Constructs a new Save object
	 * @param game to be written
	 */
	public Save(GameData gameData) {
		this.gameData = gameData;
	}
	
	/**
	 * Writes game to file
	 */
	public void saveGame(File file) {
		try {
			//Write file to disk
			FileOutputStream f_out = new FileOutputStream(file + ".txt", false);
			
			//Write object to file
			ObjectOutputStream obj_out = new ObjectOutputStream(f_out);
			
			//Pass object to object stream
			obj_out.writeObject(gameData);
			
			obj_out.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
