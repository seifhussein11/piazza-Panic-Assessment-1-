package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

import java.util.ArrayList;
import java.util.Stack;

public class Entity{
    public Stack<String> stationInv;
    public Texture image;
    public Rectangle body;
    public Stack<String> inventory;
    public float prevx = 0;
    public float prevy = 0;
    public int stationType = 0;
    public String ingredient;
    public int score;

    // Constructor for invisible walls (clips)
    Entity(Rectangle body) {
        this.body = body;
    }

    // Constructor for generic collidable objects (static props)
    protected Entity(Texture image, Rectangle body) {
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

    // Constructor for trash can and serving area
    protected Entity(Texture image, Rectangle body, int stationType, int score) {
        this.image = image;
        this.body = body;
        this.stationType = stationType;
        this.score = score;
    }

    protected Entity(Texture image, Rectangle body, int stationType, Stack<String> stationInv) {
        this.image = image;
        this.body = body;
        this.stationType = stationType;
        this.stationInv = stationInv;
    }

    protected Entity(Rectangle body, int stationType, Stack<String> stationInv) {
        this.body = body;
        this.stationType = stationType;
        this.stationInv = stationInv;
    }

}
