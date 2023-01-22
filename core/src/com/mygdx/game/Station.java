package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class Station extends Entity {
    Station(Texture image, Rectangle body, int stationType, String ingredient){
        super(image, body, stationType, ingredient);
    }

    Station(Texture image, Rectangle body, int stationType) {
        super(image, body, stationType);
    }
}
