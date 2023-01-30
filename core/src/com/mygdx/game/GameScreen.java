package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.entities.Chef;
import com.mygdx.game.entities.Customer;
import com.mygdx.game.entities.Station;

import java.util.*;

public class GameScreen implements Screen {
    private final OrthographicCamera camera;
    private final SpriteBatch batch;
    private final Chef chef1, chef2;
    private final Customer customer;
    private final Station burgerStorage, lettuceStorage,
            bunStorage, tomatoStorage, onionStorage,
            trash, chopping, serve, prep, kitchenTable;
    private final List<Entity> entities;
    private static Sprite backgroundSprite, helpMenu;
    private final List<String> burgerRecipe, saladRecipe;
    BitmapFont inventoryDisplay, timer, orderRequest;
    static float deltaTime = 0.0f;
    float startInteractc1 = -5, startInteractc2 = -5;
    long startTime;
    String inventoryUIString;
    private final int maxOrders;


    public GameScreen(PiazzaPanic game) {
        startTime = System.currentTimeMillis();
        // Instantiating entities
        chef1 = new Chef(new Texture(Gdx.files.internal("chef1.png")),
                new Rectangle(200, 500, 48, 48), new Stack<String>(),
                300);

        chef2 = new Chef(new Texture(Gdx.files.internal("chef2.png")),
                new Rectangle(300, 500, 48, 48), new Stack<String>(),
                300);

        customer = new Customer(new Texture(Gdx.files.internal("chef3.png")),
                new Rectangle(180, 0, 48, 48), 100);

        Station grill = new Station(new Texture(Gdx.files.internal("grill.png")),
                new Rectangle(493, 490, 144, 36), 2,
                new Stack<String>());

        chopping = new Station(new Texture(Gdx.files.internal("cutting-Board.png")),
                new Rectangle(650, 345, 48, 40), 3,
                new Stack<String>());

        prep = new Station(new Texture(Gdx.files.internal("prep.png")),
                new Rectangle(440, 352, 64, 32), 4,
                new Stack<String>());

        serve = new Station(new Rectangle(108, 250, 108, 36),
                6, new Stack<String>());

        burgerStorage = new Station(new Texture(Gdx.files.internal("raw_burger.png")),
                new Rectangle(270, 650, 15, 20),
                0, "Raw Patty");

        lettuceStorage = new Station(new Texture(Gdx.files.internal("lettuce.png")),
                new Rectangle(520, 650, 15, 20),
                0, "Lettuce");

        bunStorage = new Station(new Texture(Gdx.files.internal("burger_bun.png")),
                new Rectangle(640, 650, 25, 15),
                0, "Burger Bun");

        tomatoStorage = new Station(new Texture(Gdx.files.internal("tomato.png")),
                new Rectangle(760, 650, 20, 20),
                0, "Tomato");

        onionStorage = new Station(new Texture(Gdx.files.internal("onion.png")),
                new Rectangle(768, 380, 30, 30),
                0, "Onion");

        trash = new Station(new Texture(Gdx.files.internal("trash.png")),
                new Rectangle(72, 384, 35, 55),
                1, 0);

        Entity wall1 = new Entity(new Rectangle(36, 350, 248, 36));

        Entity wall2 = new Entity(new Rectangle(380, 350, 470, 36));

        Entity wall3 = new Entity(new Rectangle(36, 386, 36, 220));

        Entity wall4 = new Entity(new Rectangle(768, 386, 36, 220));

        Entity wall5 = new Entity(new Rectangle(462, 520, 200, 18));

        Entity tableCollider = new Entity(new Rectangle(110, 454, 69, 98));

        kitchenTable = new Station(new Rectangle(146, 490, 10, 10),
                5, new Stack<String>());


        burgerRecipe = Arrays.asList("Burger Bun", "Chopped Lettuce", "Cooked Patty");
        saladRecipe = Arrays.asList("Chopped Lettuce", "Chopped Tomato", "Chopped Onion");


        // MUST ADD ENTITIES TO THIS LIST IN ORDER TO ENABLE INTERACTION/COLLISIONS
        entities = Arrays.asList(wall1, wall2, wall3, wall4, wall5, tableCollider, chef1, chef2,
                customer, grill, chopping, serve, prep, burgerStorage,
                lettuceStorage, bunStorage, tomatoStorage,
                onionStorage, trash, kitchenTable);

        inventoryDisplay = new BitmapFont();
        inventoryDisplay.getData().markupEnabled = true;
        inventoryDisplay.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear,
                Texture.TextureFilter.Linear);

        Texture help = new Texture(Gdx.files.internal("Help Menu.png"));
        helpMenu = new Sprite(help);
        helpMenu.setPosition(100, 100);

        orderRequest = new BitmapFont();

        timer = new BitmapFont();

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 840, 820);
        batch = new SpriteBatch();

        Texture background = new Texture(Gdx.files.internal("PiazzaPanicMap.png"));
        backgroundSprite = new Sprite(background);
        backgroundSprite.setPosition(0, -33);

        maxOrders = 5;

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        if (serve.score < maxOrders || customer.body.y > -60) {
            // Updates and displays correct information on the UI at all times
            if ((startInteractc1 + 5 - deltaTime) < 0
                    && (startInteractc2 + 5 - deltaTime) < 0) {

                inventoryUIString = ("Chef 1: " + chef1.inventory.toString()
                        + " " + (4 - chef1.inventory.size()) + "/4" + "\n"
                        + "Chef 1 is not using a cooking station" +

                        "\n\nChef 2: " + chef2.inventory.toString() +
                        " " + (4 - chef2.inventory.size()) + "/4" + "\n"
                        + "Chef 2 is not using a cooking station" +

                        "\nTable contains: " + kitchenTable.stationInv.toString() + " " +
                        (4 - kitchenTable.stationInv.size()) + "/4");

            } else if ((startInteractc1 + 5 - deltaTime > 0)
                    && (startInteractc2 + 5 - deltaTime < 0)) {

                inventoryUIString = ("Chef 1: " + chef1.inventory.toString()
                        + " " + (4 - chef1.inventory.size()) + "/4" + "\n"
                        + "Chef 1 cooking: " + (startInteractc1 + 5 - deltaTime) +

                        "\n\nChef 2: " + chef2.inventory.toString() +
                        " " + (4 - chef2.inventory.size()) + "/4" + "\n"
                        + "Chef 2 is not using a cooking station" +

                        "\nTable contains: " + kitchenTable.stationInv.toString() + " " +
                        (4 - kitchenTable.stationInv.size()) + "/4");

            } else if ((startInteractc1 + 5 - deltaTime < 0)
                    && (startInteractc2 + 5 - deltaTime > 0)) {

                inventoryUIString = ("Chef 1: " + chef1.inventory.toString()
                        + " " + (4 - chef1.inventory.size()) + "/4" + "\n"
                        + "Chef 1 is not using a cooking station" +

                        "\n\nChef 2: " + chef2.inventory.toString() +
                        " " + (4 - chef2.inventory.size()) + "/4" + "\n"
                        + "Chef 2 cooking: " + (startInteractc2 + 5 - deltaTime) +

                        "\nTable contains: " + kitchenTable.stationInv.toString() + " " +
                        (4 - kitchenTable.stationInv.size()) + "/4");

            } else {
                inventoryUIString = ("Chef 1: " + chef1.inventory.toString()
                        + " " + (4 - chef1.inventory.size()) + "/4" + "\n"
                        + "Chef 1 cooking: " + (startInteractc1 + 5 - deltaTime) +

                        "\n\nChef 2: " + chef2.inventory.toString() +
                        " " + (4 - chef2.inventory.size()) + "/4" + "\n"
                        + "Chef 2 cooking: " + (startInteractc2 + 5 - deltaTime) +

                        "\nTable contains: " + kitchenTable.stationInv.toString() + " " +
                        (4 - kitchenTable.stationInv.size()) + "/4");
            }
            // Drawing elements on screen
            ScreenUtils.clear(0.4255f, 0.4255f, 0.4255f, 1);
            camera.update();
            batch.setProjectionMatrix(camera.combined);
            batch.begin();

            backgroundSprite.draw(batch);

            batch.draw(chef1.image, chef1.body.x, chef1.body.y);
            batch.draw(chef2.image, chef2.body.x, chef2.body.y);

            batch.draw(customer.image, customer.body.x, customer.body.y);

            batch.draw(chopping.image, chopping.body.x, chopping.body.y);
            batch.draw(prep.image, prep.body.x, prep.body.y);

            batch.draw(burgerStorage.image, burgerStorage.body.x, burgerStorage.body.y);
            batch.draw(lettuceStorage.image, lettuceStorage.body.x, lettuceStorage.body.y);
            batch.draw(bunStorage.image, bunStorage.body.x, bunStorage.body.y);
            batch.draw(tomatoStorage.image, tomatoStorage.body.x, tomatoStorage.body.y);
            batch.draw(onionStorage.image, onionStorage.body.x, onionStorage.body.y);
            batch.draw(trash.image, trash.body.x, trash.body.y);

            if (Gdx.input.isKeyPressed(Input.Keys.TAB)) {
                helpMenu.draw(batch);
            }

            inventoryDisplay.setColor(1, 1, 1, 1);
            inventoryDisplay.draw(batch, inventoryUIString, 15, 812);

            orderRequest.draw(batch, ("\n\nCustomer order: " + Customer.order), 350, 810);
            orderRequest.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear,
                    Texture.TextureFilter.Linear);

            timer.draw(batch, ("Time: " + formatTime(deltaTime) +
                            "\n\nItems trashed: " + trash.score +
                            "\n\nOrders served: " + serve.score + "/" + maxOrders),
                    705, 810);
            timer.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear,
                    Texture.TextureFilter.Linear);
            batch.end();

            // Internal clock
            deltaTime += Gdx.graphics.getDeltaTime();


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
                            && ((e.stationType == 2 && chef1.inventory.peek().equals("Raw Patty"))
                            || (e.stationType == 2 && chef1.inventory.peek().equals("Half-Cooked Patty"))
                            || (e.stationType == 3 && chef1.inventory.peek().equals("Lettuce"))
                            || (e.stationType == 3 && chef1.inventory.peek().equals("Tomato"))
                            || (e.stationType == 3 && chef1.inventory.peek().equals("Onion"))
                            || (e.stationType == 4 && new HashSet<>(chef1.inventory).containsAll(burgerRecipe))
                            || (e.stationType == 4
                            && new HashSet<>(chef1.inventory).containsAll(saladRecipe)))) {

                        chef1.interact(e, e.stationType, e.ingredient);
                        startInteractc1 = deltaTime;

                    } else if (e.stationType == 0 || e.stationType == 1
                            || e.stationType == 5 || e.stationType == 6) {
                        chef1.interact(e, e.stationType, e.ingredient);
                    }
                }

                // Chef 2 controls
            }
            if (Gdx.input.isKeyPressed(Input.Keys.NUM_2)) {
                chef2.movement();

                for (Entity e : entities) {
                    if (Gdx.input.isKeyJustPressed(Input.Keys.E) && distance(chef2, e) < 100
                            && !(chef2.inventory.isEmpty()) && e.stationType != 0
                            && ((e.stationType == 2 && chef2.inventory.peek().equals("Raw Patty"))
                            || (e.stationType == 2 && chef2.inventory.peek().equals("Half-Cooked Patty"))
                            || (e.stationType == 3 && chef2.inventory.peek().equals("Lettuce"))
                            || (e.stationType == 3 && chef2.inventory.peek().equals("Tomato"))
                            || (e.stationType == 3 && chef2.inventory.peek().equals("Onion"))
                            || (e.stationType == 4 && new HashSet<>(chef2.inventory).containsAll(burgerRecipe))
                            || (e.stationType == 4
                            && new HashSet<>(chef2.inventory).containsAll(saladRecipe)))) {

                        chef2.interact(e, e.stationType, e.ingredient);
                        startInteractc2 = deltaTime;

                    } else if (e.stationType == 0 || e.stationType == 1
                            || e.stationType == 5 || e.stationType == 6) {
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

            customer.movement();
        } else {
            ScreenUtils.clear(0.4255f, 0.4255f, 0.4255f, 1);
            batch.begin();
            orderRequest.draw(batch, ("\n\n Scenario Complete!" + "\n\n Final Time: "
                    + formatTime(deltaTime)
                    + "\n\n Items trashed: " + trash.score
                    + "\n\n Orders served: " + serve.score), 350, 810);
            batch.end();
        }

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {


    }

    private double distance(Chef e1, Entity e2) {
        // Pythagoras distance
        return (Math.sqrt(Math.pow((e1.body.x - e2.body.x), 2)
                + Math.pow((e1.body.y - e2.body.y), 2)));

    }

    private String formatTime(float time) {
        return Math.round(Math.floor(time/60)) + "m " + Math.round(time%60) + "s";
    }
}
