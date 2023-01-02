package com.piazzapanic;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.ScreenUtils;
import com.piazzapanic.entities.Player;

public class PiazzaPanic extends ApplicationAdapter {
	private SpriteBatch batch;
	private OrthographicCamera camera;
	private Texture placeholderSprite;
	private Player character;
	
	@Override
	public void create () {
		placeholderSprite = new Texture(Gdx.files.internal("player.png"));
		batch = new SpriteBatch();
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 1280, 720);
		
		character = new Player();
	}

	@Override
	public void render () {
		ScreenUtils.clear(0, 0.3f, 0.2f, 1);
		camera.update();
		
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		batch.draw(placeholderSprite, character.pos.x, character.pos.y, 50, 60);
		batch.end();
		
	      if(Gdx.input.isKeyPressed(Keys.LEFT)) {
	    	  character.pos.x -= 220 * Gdx.graphics.getDeltaTime();
	      }
	      if(Gdx.input.isKeyPressed(Keys.RIGHT)) {
	    	  character.pos.x += 220 * Gdx.graphics.getDeltaTime();
	      }
	      if(Gdx.input.isKeyPressed(Keys.UP)) {
	    	  character.pos.y += 220 * Gdx.graphics.getDeltaTime();
	      }
	      if(Gdx.input.isKeyPressed(Keys.DOWN)) {
	    	  character.pos.y -= 220 * Gdx.graphics.getDeltaTime();
	      }
	}
	
	@Override
	public void dispose () {
		placeholderSprite.dispose();
		batch.dispose();
	}
}
