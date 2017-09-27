package view;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.List;

import entities.*;
import javafx.scene.canvas.GraphicsContext;
import logic.*;
import resources.ImgResources;

public class Renderer {
	private GraphicsContext g;
	private List<Entity> entities;
	private Room room;

	public Renderer(GraphicsContext g, List<Entity> entities, Room room){
		this.g = g;
		this.entities = entities;
		this.room = room;
	}

	/**
	 * Draws the entities in the room
	 */
	public void drawEntities(){
		for (Entity e : entities){
			g.drawImage(ImgResources.?.img, , , , );
		}
	}

	/**
	 * Draws each door that leads to another room
	 */
	public void drawRoom(){
		for (Door d : room.getDoors()){
			g.drawImage(ImgResources.?.img, , , , );
		}
	}
}
