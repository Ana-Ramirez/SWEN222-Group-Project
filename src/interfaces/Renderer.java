package interfaces;

import javafx.scene.Scene;

public interface Renderer{
	
	/**
	 * Actually draws the room and HUD. Should only need to be called once at the start,
	 * after the renderer has been initialised
	 */
	public void initialDraw();
	
	/**
	 * Animates the sword in the player's hand
	 * @param fromGame true if this method is called from the game class, false otherwise
	 */
	public void animateSword(boolean fromGame);
	
	/**
	 * Repaints the information that could change in a frame
	 */
	public void repaint();

	/**
	 * @return the scene the renderer is using to draw images
	 */
	public Scene getScene();
	
}
