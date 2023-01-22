package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

import java.util.ArrayList;
import java.util.Stack;

public class Entity {
    public ArrayList<String> stationInv;
    public Texture image;
    public Rectangle body;
    public Stack<String> inventory;
    public float prevx = 0;
    public float prevy = 0;
    public int stationType = 0;
    public String ingredient;

    public int trashScore;

    // Constructor for generic collidable objects
    Entity(Texture image, Rectangle body) {
        this.image = image;
        this.body = body;
    }

    // Constructor for chefs
    protected Entity(Texture image, Rectangle body, Stack<String> inventory) {
        this.image = image;
        this.body = body;
        this.inventory = inventory;
    }

    // Constructor for ingredient stations
    protected Entity(Texture image, Rectangle body, int stationType, String ingredient) {
        this.image = image;
        this.body = body;
        this.stationType = stationType;
        this.ingredient = ingredient;
    }

    // Constructor for trash can
    protected Entity(Texture image, Rectangle body, int stationType, int trashScore) {
        this.image = image;
        this.body = body;
        this.stationType = stationType;
        this.trashScore = trashScore;
    }

    protected Entity(Texture image, Rectangle body, int stationType, ArrayList<String> stationInv) {
        this.image = image;
        this.body = body;
        this.stationType = stationType;
        this.stationInv = stationInv;
    }


}
