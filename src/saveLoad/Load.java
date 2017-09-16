package saveLoad;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import game.Game;

public class Load {
	
	private String fileName;
	
	public Load(String fileName) {
		this.fileName = fileName;
	}
	
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
