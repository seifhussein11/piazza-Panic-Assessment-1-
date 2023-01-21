package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

import java.util.Stack;

public class entity {
    public Texture image;
    public Rectangle body;
    public Stack<String> inventory;
    public float prevx = 0;
    public float prevy = 0;
    public boolean collide = false;

    public boolean isIngredientStation = false;

    public boolean isTrashCan = false;

    public String ingredient;

    // Constructor for generic collidable objects
    entity(Texture image, Rectangle body){
        this.image = image;
        this.body = body;
    }

    // Constructor for chefs
    entity(Texture image, Rectangle body, Stack<String> inventory){
        this.image = image;
        this.body = body;
        this.inventory = inventory;
    }

    // Constructor for ingredient stations
    entity(Texture image, Rectangle body, boolean isIngredientStation, String ingredient){
        this.image = image;
        this.body = body;
        this.isIngredientStation = isIngredientStation;
        this.ingredient = ingredient;
    }

    // Constructor for trash can
    entity(Texture image, Rectangle body, boolean isTrashCan){
        this.image = image;
        this.body = body;
        this.isTrashCan = isTrashCan;
    }


}
