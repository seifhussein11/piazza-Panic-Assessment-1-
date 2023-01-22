package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.entities.Chef;
import com.mygdx.game.entities.Station;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class Game extends ApplicationAdapter {
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private Chef chef1, chef2;
    private Entity grill, cooking, serve;
    private Station burgerStorage, lettuceStorage, trash1;
    private List<Entity> entities;
    BitmapFont inventoryDisplay;

    @Override
    public void create() {
        // Instantiating entities
        chef1 = new Chef(new Texture(Gdx.files.internal("chef1.png")),
                new Rectangle(0, 10, 48, 48), new Stack<String>());

        chef2 = new Chef(new Texture(Gdx.files.internal("chef2.png")),
                new Rectangle(200, 10, 48, 48), new Stack<String>());

        grill = new Entity(new Texture(Gdx.files.internal("grill.png")),
                new Rectangle(570, 300, 90, 90));

        cooking = new Entity(new Texture(Gdx.files.internal("cooking.png")),
                new Rectangle(100, 300, 90, 90));

        serve = new Entity(new Texture(Gdx.files.internal("Serve.png")),
                new Rectangle(1080, 300, 90, 90));

        burgerStorage = new Station(new Texture(Gdx.files.internal("burger.png")),
                new Rectangle(390, 100, 15, 20),
                0, "burger");

        lettuceStorage = new Station(new Texture(Gdx.files.internal("lettuce.png")),
                new Rectangle(830, 100, 17, 30),
                0, "lettuce");

        trash1 = new Station(new Texture(Gdx.files.internal("trash.png")),
                new Rectangle(600, 640, 35, 60),
                1);


        entities = Arrays.asList(chef1, chef2, grill, cooking, serve,
                burgerStorage, lettuceStorage, trash1);

        inventoryDisplay = new BitmapFont();
        inventoryDisplay.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear,
                Texture.TextureFilter.Linear);

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1280, 720);
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

        inventoryDisplay.setColor(1.0f, 1.0f, 1.0f, 1.0f);
        inventoryDisplay.draw(batch, ("Chef 1: " + chef1.inventory.toString()
                + " " + (4 - chef1.inventory.size()) + "/4"
                + "\n\nChef 2: " + chef2.inventory.toString() +
                " " + (4 - chef2.inventory.size()) + "/4"), 15, 700);

        batch.end();


        // Collisions
        for (Entity e : entities) {
            if ((e != chef1) & e.body.overlaps(chef1.body)) {
                chef1.body.x = chef1.prevx;
                chef1.body.y = chef1.prevy;
            }
        }

        for (Entity e : entities) {
            if ((e != chef2) & e.body.overlaps(chef2.body)) {
                chef2.body.x = chef2.prevx;
                chef2.body.y = chef2.prevy;
            }

            if (Gdx.input.isKeyPressed(Input.Keys.NUM_1)) {
                chef1.movement();
                chef1.interact(e, e.stationType, e.ingredient);

            } else if (Gdx.input.isKeyPressed(Input.Keys.NUM_2)) {
                chef2.movement();
                chef2.interact(e, e.stationType, e.ingredient);
            }

        }
    }
}

