package com.mygdx.game.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.Entity;

import java.util.ArrayList;
import java.util.Stack;

public class Station extends Entity {

    // Constructor for ingredient station
    // stationType = 0 for ingredient station
    public Station(Texture image, Rectangle body, int stationType, String ingredient) {
        super(image, body, stationType, ingredient);
    }

    // Constructor for trash can
    // stationType = 1 for trash can
    public Station(Texture image, Rectangle body, int stationType, int trashScore) {
        super(image, body, stationType, trashScore);
    }

    // Constructor for cooking station
    // stationType = 2 for grill,
    // 3 for chopping board, 4 for prep station,
    public Station(Texture image, Rectangle body, int stationType,
                   Stack<String> stationInv) {
        super(image, body, stationType, stationInv);
    }

    public Station(Rectangle body, int stationType,
                   Stack<String> stationInv) {
        super(body,stationType,stationInv);
    }
}
