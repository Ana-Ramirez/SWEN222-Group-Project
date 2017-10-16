package saveLoad;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import game.GameException;
import interfaces.LoadInterface;

/**
 * Functional class that loads a saved game from a file
 * @author Tim Gastrell
 *
 */
public class Load implements LoadInterface{

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
	 */
	public GameData loadGame() {
		try {

			//Read file from disk
			FileInputStream f_in = new FileInputStream(fileName);

			//Read object from file
			ObjectInputStream obj_in = new ObjectInputStream(f_in);

			Object obj = obj_in.readObject(); 

			obj_in.close();

			//Check object is valid
			if(obj instanceof GameData) {
				return (GameData) obj;
			}
			else {	
				throw new GameException("Invalid File");		
			}
			
		} catch (IOException | ClassNotFoundException | GameException e) {
			e.printStackTrace();
		}	
		return null;
	}

}
