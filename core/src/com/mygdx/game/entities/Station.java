package com.mygdx.game.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.Entity;

public class Station extends Entity {

    // Constructor for ingredient station
    public Station(Texture image, Rectangle body, int stationType, String ingredient){
        super(image, body, stationType, ingredient);
    }

    // Constructor for trash can
    public Station(Texture image, Rectangle body, int stationType) {
        super(image, body, stationType);
    }
}
