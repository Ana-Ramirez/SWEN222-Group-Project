package view;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.List;

import entities.*;
import logic.*;
import resources.ImgResources;

public class Renderer {
	private Graphics2D g;
	private List<Entity> entities;
	private Room room;

	public Renderer(Graphics2D g){
		this.g = g;
	}

	/**
	 * Draws the entities in the room
	 */
	public void drawEntities(){
		for (Entity e : entities){
			g.drawImage(Resources.PIG.img, (int) (px - d), (int) (py - d),
			          (int) (d * 2), (int) (d * 2), null);
		}
	}

	/**
	 * Draws each door that leads to another room
	 */
	public void drawRoom(){
		for (Door d : room.getDoors()){
			d.draw();
		}
	}
}
