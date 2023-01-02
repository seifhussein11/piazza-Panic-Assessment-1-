package com.piazzapanic;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public abstract class GameObject {

	protected Vector2 pos, dim;

	protected Texture tex;

	protected GameObject() {
		pos = new Vector2();
		dim = new Vector2();
	}

	protected void createGraphics(String location) {
		tex = new Texture(location);
	}

	// getters
	public Vector2 getPos() {
		return pos;
	}

	public Vector2 getDim() {
		return dim;
	}
	
	//hitbox
	protected Rectangle getArea() {
		return new Rectangle(pos.x, pos.y, dim.x, dim.y);
	}

	// setters
	public void setPos(Vector2 pos) {
		this.pos = pos;
	}

	public void setDim(Vector2 dim) {
		this.dim = dim;
	}
}
