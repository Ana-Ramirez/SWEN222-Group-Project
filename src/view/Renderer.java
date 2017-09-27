package view;

import java.awt.Graphics2D;
import java.util.List;

import entities.*;
import logic.*;

public class Renderer {
	private Graphics2D g;
	private List<Entity> entities;
	private Room room;
	
	public Renderer(){
		
	}
	
	/**
	 * Draws the entities in the room
	 */
	public void drawEntities(){
		for (Entity e : entities){
			e.draw();
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
