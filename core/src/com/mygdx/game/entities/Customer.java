package com.mygdx.game.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.Entity;

import java.util.Random;
public class Customer extends Entity {

    public static int state;  // State: Entering = 1, Waiting = 2, Exiting = 3
    private float speed;
    public static String order;
    String[] orders = new String[] {"Burger", "Salad"};
    String[] sprites = new String[] {"Witch-Hat.png", "Cowboy-Hat.png"};

    public Customer(Texture image, Rectangle body, float speed) {
        super(body);
        this.prevx = body.x;
        this.prevy = body.y;
        this.speed = speed;
        this.state = 1;
        this.order = Random(orders);
        this.image = new Texture(Gdx.files.internal(Random(sprites)));

    }

    public String Random(String[] list) {
        Random random = new Random();
        int index = random.nextInt(list.length);
        return list[index];
    }

    public void movement() {
        if(state == 1) {
            this.prevy = this.body.y;
            this.body.y += this.speed * Gdx.graphics.getDeltaTime();
        }
        if(state == 3) {
            this.prevy = this.body.y;
            this.body.y -= this.speed * Gdx.graphics.getDeltaTime();
        }

        if (this.body.y > 200) {
            this.body.y = 200;
            state = 2;
            this.image = new Texture(Gdx.files.internal(Random(sprites)));
        }
        if (this.body.y < -80) {
            this.state = 1;
            this.order = Random(orders);
        }
    }
}
