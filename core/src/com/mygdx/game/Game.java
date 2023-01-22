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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class Game extends ApplicationAdapter {
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private Chef chef1, chef2;
    private Station burgerStorage, lettuceStorage, bunStorage,
            trash1, grill, chopping, serve, prep;
    private List<Entity> entities;
    BitmapFont inventoryDisplay, timer;
    long startTime;

    @Override
    public void create() {
        startTime = System.currentTimeMillis();
        // Instantiating entities
        chef1 = new Chef(new Texture(Gdx.files.internal("chef1.png")),
                new Rectangle(0, 10, 48, 48), new Stack<String>(),
                300);

        chef2 = new Chef(new Texture(Gdx.files.internal("chef2.png")),
                new Rectangle(200, 10, 48, 48), new Stack<String>(),
                300);

        grill = new Station(new Texture(Gdx.files.internal("grill.png")),
                new Rectangle(570, 300, 90, 90), 2,
                new ArrayList<String>());

        chopping = new Station(new Texture(Gdx.files.internal("cooking.png")),
                new Rectangle(100, 300, 90, 90), 3,
                new ArrayList<String>());

        prep = new Station(new Texture(Gdx.files.internal("prep.jpg")),
                new Rectangle(800, 300, 90, 90), 4,
                new ArrayList<String>());

        serve = new Station(new Texture(Gdx.files.internal("Serve.png")),
                new Rectangle(1080, 300, 90, 90), 2,
                new ArrayList<String>());

        burgerStorage = new Station(new Texture(Gdx.files.internal("raw_burger.png")),
                new Rectangle(390, 100, 15, 20),
                0, "Raw Patty");

        lettuceStorage = new Station(new Texture(Gdx.files.internal("lettuce.png")),
                new Rectangle(830, 100, 17, 30),
                0, "Lettuce");

        bunStorage = new Station(new Texture(Gdx.files.internal("burger_bun.png")),
                new Rectangle(1080, 100, 25, 15), 0, "Burger Bun");

        trash1 = new Station(new Texture(Gdx.files.internal("trash.png")),
                new Rectangle(600, 640, 35, 60),
                1, 0);


        entities = Arrays.asList(chef1, chef2, grill, chopping, serve, prep,
                burgerStorage, lettuceStorage, bunStorage, trash1);

        inventoryDisplay = new BitmapFont();
        inventoryDisplay.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear,
                Texture.TextureFilter.Linear);

        timer = new BitmapFont();

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1280, 720);
        batch = new SpriteBatch();

    }

    @Override
    public void render() {

        // Drawing elements on screen
        ScreenUtils.clear(0, 0, 0.2f, 1);
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();

        batch.draw(chef1.image, chef1.body.x, chef1.body.y);
        batch.draw(chef2.image, chef2.body.x, chef2.body.y);

        batch.draw(grill.image, grill.body.x, grill.body.y);
        batch.draw(chopping.image, chopping.body.x, chopping.body.y);
        batch.draw(serve.image, serve.body.x, serve.body.y);
        batch.draw(prep.image, prep.body.x, prep.body.y);

        batch.draw(burgerStorage.image, burgerStorage.body.x, burgerStorage.body.y);
        batch.draw(lettuceStorage.image, lettuceStorage.body.x, lettuceStorage.body.y);
        batch.draw(bunStorage.image, bunStorage.body.x, bunStorage.body.y);
        batch.draw(trash1.image, trash1.body.x, trash1.body.y);

        inventoryDisplay.setColor(1.0f, 1.0f, 1.0f, 1.0f);
        inventoryDisplay.draw(batch, ("Chef 1: " + chef1.inventory.toString()
                + " " + (4 - chef1.inventory.size()) + "/4"
                + "\n\nChef 2: " + chef2.inventory.toString() +
                " " + (4 - chef2.inventory.size()) + "/4" +
                "\n\nItems trashed: " + trash1.trashScore), 15, 700);
        timer.draw(batch,("Time: "+(System.currentTimeMillis()-startTime)/1000),1200,700);
        batch.end();

        // Collision
        for (Entity e : entities) {
            chef1.collide(e);
            chef2.collide(e);
        }

        // Controls
        if (Gdx.input.isKeyPressed(Input.Keys.NUM_1)) {
            chef1.movement();

            for (Entity e : entities) {
                chef1.interact(e, e.stationType, e.ingredient);
            }

        } else if (Gdx.input.isKeyPressed(Input.Keys.NUM_2)) {
            chef2.movement();

            for (Entity e : entities) {
                chef2.interact(e, e.stationType, e.ingredient);
            }
        }
    }
}
