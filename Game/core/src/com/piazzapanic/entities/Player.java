package com.piazzapanic.entities;

import com.badlogic.gdx.math.Rectangle;
import com.piazzapanic.GameObject;

public class Player extends GameObject {
	
	public Player() {
		pos.x = 1280/2;
		pos.y = 720/2;
		dim.x = 8;
		dim.y = 8;
		
		createGraphics("player.png");
	}
}
