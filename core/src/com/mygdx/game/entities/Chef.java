package com.mygdx.game.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.Entity;

import java.util.Stack;

public class Chef extends Entity {
    public Chef(Texture image, Rectangle body, Stack<String> inventory) {
        super(image, body, inventory);
        this.prevx = body.x;
        this.prevy = body.y;
    }

    public void movement() {
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            this.prevx = this.body.x;
            this.body.x -= 300 * Gdx.graphics.getDeltaTime();
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            this.prevx = this.body.x;
            this.body.x += 300 * Gdx.graphics.getDeltaTime();
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            this.prevy = this.body.y;
            this.body.y += 300 * Gdx.graphics.getDeltaTime();
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            this.prevy = this.body.y;
            this.body.y -= 300 * Gdx.graphics.getDeltaTime();
        }
        if (this.body.x < 0) this.body.x = 0;
        if (this.body.x > 1280 - 48) this.body.x = 1280 - 48;
        if (this.body.y < 0) this.body.y = 0;
        if (this.body.y > 720 - 48) this.body.y = 720 - 48;
    }

    public void interact(Entity e, int stationType, String ingredient) {
        if (stationType == 0 && Gdx.input.isKeyJustPressed(Input.Keys.E)
                && this.inventory.size() < 4 && distance(this, e) < 100
                && ingredient != null) {

            this.inventory.push(ingredient);

        } else if (Gdx.input.isKeyJustPressed(Input.Keys.E) && e.stationType == 1
                && distance(this, e) < 100 && !(this.inventory.isEmpty())) {

            this.inventory.pop();
        }
    }

    public void collide(Entity e) {
        if ((e != this) & e.body.overlaps(this.body)) {
            this.body.x = this.prevx;
            this.body.y = this.prevy;
        }
    }

    private double distance(Chef e1, Entity e2) {
        // Manhattan distance
        return (Math.abs(e1.body.x - e2.body.x)
                + Math.abs(e1.body.y - e2.body.y));
    }
}
