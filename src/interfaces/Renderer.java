package interfaces;

import javafx.scene.Scene;

public interface Renderer{
	
	/**
	 * Actually draws the room and HUD. Should only need to be called once at the start,
	 * after the renderer has been initialised
	 */
	public void initialDraw();
	
	/**
	 * 
	 * @param fromGame
	 */
	public void animateSword(boolean fromGame);
	
	/**
	 * Repaints the information that could change in a frame
	 */
	public void repaint();

	/**
	 * 
	 * @return
	 */
	public Scene getScene();
	
}
