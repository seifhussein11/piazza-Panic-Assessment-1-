package com.mygdx.game.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.Entity;

import java.util.Stack;

public class Chef extends Entity {
    public Chef(Texture image, Rectangle body, Stack<String> inventory){
        super(image, body, inventory);
    }
}
