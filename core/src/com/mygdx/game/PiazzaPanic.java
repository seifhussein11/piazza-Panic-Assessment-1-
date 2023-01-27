package com.mygdx.game;

import com.badlogic.gdx.Game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.OrthographicCamera;

import com.badlogic.gdx.graphics.g2d.BitmapFont;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class PiazzaPanic extends Game {
    private OrthographicCamera camera;
    SpriteBatch batch;

    public BitmapFont font;

    @Override
    public void create() {
        batch = new SpriteBatch();
        font = new BitmapFont();
        this.setScreen(new MainMenu(this));
    }

    @Override
    public void render() {
        super.render();




    }

    public void dispose(){
        batch.dispose();
        font.dispose();
    }
}
