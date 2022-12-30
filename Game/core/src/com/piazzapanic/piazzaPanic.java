package com.piazzapanic;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.ScreenUtils;

public class PiazzaPanic extends ApplicationAdapter {
	private SpriteBatch batch;
	private OrthographicCamera camera;
	private Texture placeholderSprite;
	private Rectangle character;
	
	@Override
	public void create () {
		placeholderSprite = new Texture(Gdx.files.internal("player.png"));
		batch = new SpriteBatch();
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 1280, 720);
		
		character = new Rectangle();
		character.x = 1280/2 - 64/2;
		character.y = 40;
		character.width = 0;
		character.height = 0;
	}

	@Override
	public void render () {
		ScreenUtils.clear(0, 0.3f, 0.2f, 1);
		camera.update();
		
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		batch.draw(placeholderSprite, character.x, character.y, 50, 60);
		batch.end();
		
	      if(Gdx.input.isKeyPressed(Keys.LEFT)) {
	    	  character.x -= 200 * Gdx.graphics.getDeltaTime();
	      }
	      if(Gdx.input.isKeyPressed(Keys.RIGHT)) {
	    	  character.x += 200 * Gdx.graphics.getDeltaTime();
	      }
	      if(Gdx.input.isKeyPressed(Keys.UP)) {
	    	  character.y += 200 * Gdx.graphics.getDeltaTime();
	      }
	      if(Gdx.input.isKeyPressed(Keys.DOWN)) {
	    	  character.y -= 200 * Gdx.graphics.getDeltaTime();
	      }
	}
	
	@Override
	public void dispose () {
		placeholderSprite.dispose();
		batch.dispose();
	}
}
