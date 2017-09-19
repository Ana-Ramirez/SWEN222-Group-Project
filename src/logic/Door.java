package logic;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class Door {
	
	private Room connection1, connection2;
	
	public Door(Room rm1, Room rm2){
		this.connection1 = rm1;
		this.connection2 = rm2;
	}
	
	public Room getRoom1(){
		throw new NotImplementedException();
	}
	
	public Room getRoom2(){
		throw new NotImplementedException();
	}
	
	public void setRoom1(){
		throw new NotImplementedException();
	}
	
	public void setRoom2(){
		throw new NotImplementedException();
	}
}
