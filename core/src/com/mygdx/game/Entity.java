package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

import java.util.Stack;

public class Entity {
    public Texture image;
    public Rectangle body;
    public Stack<String> inventory;
    public float prevx = 0;
    public float prevy = 0;
    public boolean collide = false;
    public int stationType = 0;
    public String ingredient;

    Entity(){
        this.image = null;
        this.body = null;
    }
    // Constructor for generic collidable objects
    Entity(Texture image, Rectangle body){
        this.image = image;
        this.body = body;
    }

    // Constructor for chefs
    protected Entity(Texture image, Rectangle body, Stack<String> inventory){
        this.image = image;
        this.body = body;
        this.inventory = inventory;
    }

    // Constructor for ingredient stations
    protected Entity(Texture image, Rectangle body, int stationType, String ingredient){
        this.image = image;
        this.body = body;
        this.stationType = stationType;
        this.ingredient = ingredient;
    }

    // Constructor for trash can
    protected Entity(Texture image, Rectangle body, int stationType){
        this.image = image;
        this.body = body;
        this.stationType = stationType;
    }


}
