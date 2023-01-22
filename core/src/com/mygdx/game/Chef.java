package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

import java.util.Stack;

public class Chef extends Entity {
    Chef(Texture image, Rectangle body, Stack<String> inventory){
        super(image, body, inventory);
    }
}
