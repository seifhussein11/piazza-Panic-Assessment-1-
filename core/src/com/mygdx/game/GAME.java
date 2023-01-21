package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.scenes.scene2d.ui.Button;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class GAME extends ApplicationAdapter {
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private entity chef1, chef2, grill, cooking, serve, burgerStorage, lettuceStorage, trash1;
    private List<entity> entities;

    @Override
    public void create() {
        // Instantiating entities
        chef1 = new entity(new Texture(Gdx.files.internal("chef1.png")),
                new Rectangle(0, 0, 64, 64), new Stack<String>());

        chef2 = new entity(new Texture(Gdx.files.internal("chef2.png")),
                new Rectangle(200, 0, 64, 64), new Stack<String>());

        grill = new entity(new Texture(Gdx.files.internal("grill.png")),
                new Rectangle(520, 150, 90, 90));

        cooking = new entity(new Texture(Gdx.files.internal("cooking.png")),
                new Rectangle(100, 150, 90, 90));

        serve = new entity(new Texture(Gdx.files.internal("Serve.png")),
                new Rectangle(700, 150, 90, 90));

        burgerStorage = new entity(new Texture(Gdx.files.internal("burger.png")),
                new Rectangle(300, 100, 20, 20),
                true, "burger");

        lettuceStorage = new entity(new Texture(Gdx.files.internal("lettuce.png")),
                new Rectangle(350, 200, 30, 30),
                true, "lettuce");

        trash1 = new entity(new Texture(Gdx.files.internal("trash.png")),
                new Rectangle(60, 300, 40, 40),
                true);


        entities = Arrays.asList(chef1, chef2, grill, cooking, serve,
                burgerStorage, lettuceStorage, trash1);

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
        batch = new SpriteBatch();
    }

    @Override
    public void render() {

        ScreenUtils.clear(0, 0, 0.2f, 1);
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(chef1.image, chef1.body.x, chef1.body.y);
        batch.draw(chef2.image, chef2.body.x, chef2.body.y);
        batch.draw(grill.image, grill.body.x, grill.body.y);
        batch.draw(cooking.image, cooking.body.x, cooking.body.y);
        batch.draw(serve.image, serve.body.x, serve.body.y);
        batch.draw(burgerStorage.image, burgerStorage.body.x, burgerStorage.body.y);
        batch.draw(lettuceStorage.image, lettuceStorage.body.x, lettuceStorage.body.y);
        batch.draw(trash1.image, trash1.body.x, trash1.body.y);
        batch.end();


        // Collisions
        for (entity e : entities) {
            if ((e != chef1) & e.body.overlaps(chef1.body)) {
                chef1.body.x = chef1.prevx;
                chef1.body.y = chef1.prevy;
            }
            // Adds ingredient to inventory if the chef does
            // not already have such ingredient (limit 1 at a time per chef)
            if (Gdx.input.isKeyJustPressed(Input.Keys.E) && e.isIngredientStation
                    && Gdx.input.isKeyPressed(Input.Keys.NUM_1)
                    && distance(e, chef1) < 100) {

                chef1.inventory.push(e.ingredient);
                System.out.println("Chef 1: " + chef1.inventory);

            } else if (Gdx.input.isKeyJustPressed(Input.Keys.E) && e.isTrashCan
                    && Gdx.input.isKeyPressed(Input.Keys.NUM_1)
                    && distance(e, chef1) < 100) {

                chef1.inventory.pop();
                System.out.println("Chef 1: " + chef1.inventory);
            }
        }

        for (entity e : entities) {
            if ((e != chef2) & e.body.overlaps(chef2.body)) {
                chef2.body.x = chef2.prevx;
                chef2.body.y = chef2.prevy;
            }

            if (Gdx.input.isKeyJustPressed(Input.Keys.E) && e.isIngredientStation
                    && Gdx.input.isKeyPressed(Input.Keys.NUM_2)
                    && distance(e, chef2) < 100) {

                chef2.inventory.push(e.ingredient);
                System.out.println("Chef 2: " + chef2.inventory);

            } else if (Gdx.input.isKeyJustPressed(Input.Keys.E) && e.isTrashCan
                    && Gdx.input.isKeyPressed(Input.Keys.NUM_2)
                    && distance(e, chef2) < 100 && !(chef2.inventory.isEmpty())) {

                chef2.inventory.pop();
                System.out.println("Chef 2: " + chef2.inventory);
            }

        }

        // Movement
        if (Gdx.input.isKeyPressed(Input.Keys.NUM_1)) {
            if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
                chef1.prevx = chef1.body.x;
                chef1.body.x -= 200 * Gdx.graphics.getDeltaTime();
            }
            if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
                chef1.prevx = chef1.body.x;
                chef1.body.x += 200 * Gdx.graphics.getDeltaTime();
            }
            if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
                chef1.prevy = chef1.body.y;
                chef1.body.y += 200 * Gdx.graphics.getDeltaTime();
            }
            if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
                chef1.prevy = chef1.body.y;
                chef1.body.y -= 200 * Gdx.graphics.getDeltaTime();
            }
            if (chef1.body.x < 0) chef1.body.x = 0;
            if (chef1.body.x > 800 - 64) chef1.body.x = 800 - 64;
            if (chef1.body.y < 0) chef1.body.y = 0;
            if (chef1.body.y > 480 - 64) chef1.body.y = 480 - 64;

        } else if (Gdx.input.isKeyPressed(Input.Keys.NUM_2)) {
            if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
                chef2.prevx = chef2.body.x;
                chef2.body.x -= 200 * Gdx.graphics.getDeltaTime();
            }
            if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
                chef2.prevx = chef2.body.x;
                chef2.body.x += 200 * Gdx.graphics.getDeltaTime();
            }
            if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
                chef2.prevy = chef2.body.y;
                chef2.body.y += 200 * Gdx.graphics.getDeltaTime();
            }
            if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
                chef2.prevy = chef2.body.y;
                chef2.body.y -= 200 * Gdx.graphics.getDeltaTime();
            }
            if (chef2.body.x < 0) chef2.body.x = 0;
            if (chef2.body.x > 800 - 64) chef2.body.x = 800 - 64;
            if (chef2.body.y < 0) chef2.body.y = 0;
            if (chef2.body.y > 480 - 64) chef2.body.y = 480 - 64;
        }

    }

    private double distance(entity e1, entity e2) {
        // Manhattan distance
        return (Math.abs(e1.body.x - e2.body.x)
                + Math.abs(e1.body.y - e2.body.y));
    }

}
