package controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Controller implements KeyListener{
	
	public Controller(){
		
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();
		switch ( keyCode){
		case KeyEvent.VK_W:		//move up
			//do something
			break;
		case KeyEvent.VK_S:		//move down
			//do something
			break;
		case KeyEvent.VK_A:		//move left
			//do something
			break;
		case KeyEvent.VK_D:		//move right
			//do something
			break;
		}
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {}

	@Override
	public void keyTyped(KeyEvent arg0) {}

}
