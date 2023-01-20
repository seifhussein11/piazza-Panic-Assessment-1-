package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

import java.util.ArrayList;

public class entity {
    public Texture image;
    public Rectangle body;
    public ArrayList<String> items;
    public float prevx = 0;
    public float prevy = 0;
    public boolean collide = false;
    entity(Texture image, Rectangle body){
        this.image = image;
        this.body = body;
    }


}
