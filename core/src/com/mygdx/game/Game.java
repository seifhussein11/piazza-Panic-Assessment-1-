package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
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
    private Entity wall1, wall2, wall3, wall4, tableCollider;
    private Chef chef1, chef2;
    private Station burgerStorage, lettuceStorage, bunStorage,
            trash, grill, chopping, serve, prep, tableInv;
    private List<Entity> entities;
    private static Sprite backgroundSprite;
    BitmapFont inventoryDisplay, timer;
    static float deltaTime = 0.0f;
    float startInteractc1 = -5, startInteractc2 = -5;
    long startTime;
    String inventoryUIString;

    @Override
    public void create() {
        startTime = System.currentTimeMillis();
        // Instantiating entities
        chef1 = new Chef(new Texture(Gdx.files.internal("chef1.png")),
                new Rectangle(45, 50, 48, 48), new Stack<String>(),
                300);

        chef2 = new Chef(new Texture(Gdx.files.internal("chef2.png")),
                new Rectangle(200, 50, 48, 48), new Stack<String>(),
                300);

        grill = new Station(new Texture(Gdx.files.internal("grill.png")),
                new Rectangle(493, 490, 144, 36), 2,
                new Stack<String>());

        chopping = new Station(new Texture(Gdx.files.internal("cooking.png")),
                new Rectangle(100, 300, 90, 90), 3,
                new Stack<String>());

        prep = new Station(new Texture(Gdx.files.internal("prep.jpg")),
                new Rectangle(350, 200, 90, 90), 4,
                new Stack<String>());

        serve = new Station(new Texture(Gdx.files.internal("Serve.png")),
                new Rectangle(600, 300, 90, 90), 2,
                new Stack<String>());

        burgerStorage = new Station(new Texture(Gdx.files.internal("raw_burger.png")),
                new Rectangle(300, 50, 15, 20),
                0, "Raw Patty");

        lettuceStorage = new Station(new Texture(Gdx.files.internal("lettuce.png")),
                new Rectangle(454, 50, 17, 30),
                0, "Lettuce");

        bunStorage = new Station(new Texture(Gdx.files.internal("burger_bun.png")),
                new Rectangle(600, 50, 25, 15), 0, "Burger Bun");

        trash = new Station(new Texture(Gdx.files.internal("trash.png")),
                new Rectangle(72, 384, 35, 55),
                1, 0);

        wall1 = new Entity(new Rectangle(36, 350, 248, 36));

        wall2 = new Entity(new Rectangle(380, 350, 470, 36));

        wall3 = new Entity(new Rectangle(36, 386, 36, 220));

        wall4 = new Entity(new Rectangle(768, 386, 36, 220));

        tableCollider = new Entity(new Rectangle(110, 454, 69, 98));

        tableInv = new Station(new Rectangle(146, 490, 10, 10),
                5, new Stack<String>());

        // MUST ADD ENTITIES TO THIS LIST IN ORDER TO ENABLE INTERACTION/COLLISIONS
        entities = Arrays.asList(wall1, wall2, wall3, wall4, tableCollider, chef1, chef2, grill,
                chopping, serve, prep, burgerStorage, lettuceStorage, bunStorage, trash, tableInv);

        inventoryDisplay = new BitmapFont();
        inventoryDisplay.getData().markupEnabled = true;
        inventoryDisplay.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear,
                Texture.TextureFilter.Linear);

        timer = new BitmapFont();

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 840, 820);
        batch = new SpriteBatch();

        Texture background = new Texture(Gdx.files.internal("PiazzaPanicMap.png"));
        backgroundSprite = new Sprite(background);
        backgroundSprite.setPosition(0, -33);

    }

    @Override
    public void render() {

        // Updates and displays correct information on the UI at all times
        if ((startInteractc1 + 5 - deltaTime) < 0
                && (startInteractc2 + 5 - deltaTime) < 0) {

            inventoryUIString = ("Chef 1: " + chef1.inventory.toString()
                    + " " + (4 - chef1.inventory.size()) + "/4" + "\n"
                    + "Chef 1 is not using a cooking station" +

                    "\n\nChef 2: " + chef2.inventory.toString() +
                    " " + (4 - chef2.inventory.size()) + "/4" + "\n"
                    + "Chef 2 is not using a cooking station" +

                    "\nTable contains: " + tableInv.stationInv.toString() + " " +
                    (4 - tableInv.stationInv.size()) + "/4");

        } else if ((startInteractc1 + 5 - deltaTime > 0)
                && (startInteractc2 + 5 - deltaTime < 0)) {

            inventoryUIString = ("Chef 1: " + chef1.inventory.toString()
                    + " " + (4 - chef1.inventory.size()) + "/4" + "\n"
                    + "Chef 1 cooking: " + (startInteractc1 + 5 - deltaTime) +

                    "\n\nChef 2: " + chef2.inventory.toString() +
                    " " + (4 - chef2.inventory.size()) + "/4" + "\n"
                    + "Chef 2 is not using a cooking station" +

                    "\nTable contains: " + tableInv.stationInv.toString() + " " +
                    (4 - tableInv.stationInv.size()) + "/4");

        } else if ((startInteractc1 + 5 - deltaTime < 0)
                && (startInteractc2 + 5 - deltaTime > 0)) {

            inventoryUIString = ("Chef 1: " + chef1.inventory.toString()
                    + " " + (4 - chef1.inventory.size()) + "/4" + "\n"
                    + "Chef 1 is not using a cooking station" +

                    "\n\nChef 2: " + chef2.inventory.toString() +
                    " " + (4 - chef2.inventory.size()) + "/4" + "\n"
                    + "Chef 2 cooking: " + (startInteractc2 + 5 - deltaTime) +

                    "\nTable contains: " + tableInv.stationInv.toString() + " " +
                    (4 - tableInv.stationInv.size()) + "/4");

        } else {
            inventoryUIString = ("Chef 1: " + chef1.inventory.toString()
                    + " " + (4 - chef1.inventory.size()) + "/4" + "\n"
                    + "Chef 1 cooking: " + (startInteractc1 + 5 - deltaTime) +

                    "\n\nChef 2: " + chef2.inventory.toString() +
                    " " + (4 - chef2.inventory.size()) + "/4" + "\n"
                    + "Chef 2 cooking: " + (startInteractc2 + 5 - deltaTime) +

                    "\nTable contains: " + tableInv.stationInv.toString() + " " +
                    (4 - tableInv.stationInv.size()) + "/4");
        }
        // Drawing elements on screen
        ScreenUtils.clear(0.4255f, 0.4255f, 0.4255f, 1);
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();

        backgroundSprite.draw(batch);

        batch.draw(chef1.image, chef1.body.x, chef1.body.y);
        batch.draw(chef2.image, chef2.body.x, chef2.body.y);

        batch.draw(chopping.image, chopping.body.x, chopping.body.y);
        batch.draw(serve.image, serve.body.x, serve.body.y);
        batch.draw(prep.image, prep.body.x, prep.body.y);

        batch.draw(burgerStorage.image, burgerStorage.body.x, burgerStorage.body.y);
        batch.draw(lettuceStorage.image, lettuceStorage.body.x, lettuceStorage.body.y);
        batch.draw(bunStorage.image, bunStorage.body.x, bunStorage.body.y);
        batch.draw(trash.image, trash.body.x, trash.body.y);

        inventoryDisplay.setColor(1, 1, 1, 1);
        inventoryDisplay.draw(batch, inventoryUIString, 15, 812);

        timer.draw(batch, ("Time: " + ((System.currentTimeMillis() - startTime) / 1000) +
                "\n\nItems trashed: " + trash.trashScore), 705, 810);
        timer.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear,
                Texture.TextureFilter.Linear);
        batch.end();

        // Internal clock
        deltaTime += Gdx.graphics.getDeltaTime();
        System.out.println(deltaTime);


        // Collision
        for (Entity e : entities) {
            chef1.collide(e);
            chef2.collide(e);
        }


        // Chef 1 controls
        if (Gdx.input.isKeyPressed(Input.Keys.NUM_1)) {
            chef1.movement();

            for (Entity e : entities) {
                if (Gdx.input.isKeyJustPressed(Input.Keys.E) && distance(chef1, e) < 100
                        && !(chef1.inventory.isEmpty()) && e.stationType != 0
                        && (e.stationType == 2 || e.stationType == 3
                        || e.stationType == 4)) {
                    chef1.interact(e, e.stationType, e.ingredient);
                    startInteractc1 = deltaTime;
                } else if (e.stationType == 0 || e.stationType == 1
                        || e.stationType == 5) {
                    chef1.interact(e, e.stationType, e.ingredient);
                }
            }

        // Chef 2 controls
        } else if (Gdx.input.isKeyPressed(Input.Keys.NUM_2)) {
            chef2.movement();

            for (Entity e : entities) {
                if (Gdx.input.isKeyJustPressed(Input.Keys.E) && distance(chef2, e) < 100
                        && !(chef2.inventory.isEmpty()) && e.stationType != 0
                        && (e.stationType == 2 || e.stationType == 3
                        || e.stationType == 4)) {
                    chef2.interact(e, e.stationType, e.ingredient);
                    startInteractc2 = deltaTime;
                } else if (e.stationType == 0 || e.stationType == 1
                        || e.stationType == 5) {
                    chef2.interact(e, e.stationType, e.ingredient);
                }
            }
        }

        if (deltaTime > startInteractc1 + 5) {
            chef1.setSpeed(300);
        }

        if (deltaTime > startInteractc2 + 5) {
            chef2.setSpeed(300);
        }

    }

    private double distance(Chef e1, Entity e2) {
        // Manhattan distance
        return (Math.abs(e1.body.x - e2.body.x)
                + Math.abs(e1.body.y - e2.body.y));

    }
}
