package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.utils.ScreenUtils;


public class MainMenu implements Screen {
    PiazzaPanic game;
    OrthographicCamera camera;
    private Texture start;
    private Texture piazzaPanic;
    private Texture background2;

    public MainMenu(final PiazzaPanic game) {
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 840, 820);
        start = new Texture("zorar.png");

        piazzaPanic = new Texture("newtitle.png");
        background2 = new Texture("1kitchen.png");
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0.2f, 1);
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();
        game.batch.draw(background2, 0, 0);
        game.batch.draw(start, 220, 350);
        game.batch.draw(piazzaPanic, 30, 610);
        game.batch.end();

        if (Gdx.input.isTouched()) {
            game.setScreen(new GameScreen(game));
            dispose();
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
}
