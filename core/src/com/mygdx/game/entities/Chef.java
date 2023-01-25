package com.mygdx.game.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.Entity;

import java.util.Stack;

public class Chef extends Entity {
    private float speed;

    public Chef(Texture image, Rectangle body, Stack<String> inventory,
                float speed) {
        super(image, body, inventory);
        this.prevx = body.x;
        this.prevy = body.y;
        this.speed = speed;
    }

    public void movement() {
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            this.prevx = this.body.x;
            this.body.x -= this.speed * Gdx.graphics.getDeltaTime();
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            this.prevx = this.body.x;
            this.body.x += this.speed * Gdx.graphics.getDeltaTime();
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            this.prevy = this.body.y;
            this.body.y += this.speed * Gdx.graphics.getDeltaTime();
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            this.prevy = this.body.y;
            this.body.y -= this.speed * Gdx.graphics.getDeltaTime();
        }
        if (this.body.x < 36) this.body.x = 36;
        if (this.body.x > 804 - 48) this.body.x = 804 - 48;
        if (this.body.y < 36) this.body.y = 36;
        if (this.body.y > 652 - 48) this.body.y = 652 - 48;
    }

    public void interact(Entity e, int stationType, String ingredient) {
        if (stationType == 0 && Gdx.input.isKeyJustPressed(Input.Keys.E)
                && this.inventory.size() < 4 && distance(this, e) < 100
                && ingredient != null) {

            this.inventory.push(ingredient);

        } else if (Gdx.input.isKeyJustPressed(Input.Keys.E) && e.stationType == 1
                && distance(this, e) < 100 && !(this.inventory.isEmpty())) {

            this.inventory.pop();
            e.trashScore++;

        } else if (Gdx.input.isKeyJustPressed(Input.Keys.E) && e.stationType == 2
                && distance(this, e) < 100
                && !(this.inventory.isEmpty())
                && this.inventory.peek().equals("Raw Patty")) {

            this.inventory.pop();
            this.inventory.push("Cooked Patty");

        } else if (Gdx.input.isKeyJustPressed(Input.Keys.E) && e.stationType == 3
                && distance(this, e) < 100
                && !(this.inventory.isEmpty())
                && this.inventory.peek().equals("Lettuce")) {
            this.inventory.pop();
            this.inventory.push("Chopped Lettuce");

        } else if (Gdx.input.isKeyJustPressed(Input.Keys.E) && e.stationType == 4
                && distance(this, e) < 100
                && this.inventory.contains("Cooked Patty") &&
                this.inventory.contains("Chopped Lettuce") &&
                this.inventory.contains("Burger Bun")) {

            this.inventory.remove("Cooked Patty");
            this.inventory.remove("Chopped Lettuce");
            this.inventory.remove("Burger Bun");
            this.inventory.push("Burger");

        } else if (Gdx.input.isKeyJustPressed(Input.Keys.E) && e.stationType == 5
                && distance(this, e) < 90
                && !(this.inventory.isEmpty())
                && e.stationInv.size() < 4) {

            e.stationInv.push(this.inventory.pop());

        } else if (Gdx.input.isKeyJustPressed(Input.Keys.F) && e.stationType == 5
                && distance(this, e) < 90
                && this.inventory.size() < 4
                && !(e.stationInv.isEmpty())) {
            this.inventory.push(e.stationInv.pop());
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
