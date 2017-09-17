package saveLoad;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import game.Game;

/**
 * Functional class that loads a saved game from a file
 * @author Tim Gastrell
 *
 */
public class Load {
	
	/**
	 * Name of save game file
	 */
	private String fileName;
	
	/**
	 * Constructs a new Load object
	 * @param name of game save file
	 */
	public Load(String fileName) {
		this.fileName = fileName;
	}
	
	/**
	 * Loads a save game from a file
	 * @return save game
	 */
	public Game loadGame() {
		try {
			
			//Read file from disk
			FileInputStream f_in = new FileInputStream(fileName);
			
			//Read object from file
			ObjectInputStream obj_in = new ObjectInputStream(f_in);
			
			Object obj = obj_in.readObject();
			
			//Check object is valid
			if(obj instanceof Game) {
				return (Game) obj;
			}
			else {
				throw new IOException("Invalid File");
			}
			
		} catch (IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
}
