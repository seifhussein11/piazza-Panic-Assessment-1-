package com.mygdx.game;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

import com.badlogic.gdx.utils.ScreenUtils;

import java.util.Arrays;
import java.util.List;


public class GAME extends ApplicationAdapter {
	private OrthographicCamera camera;
	private SpriteBatch batch;
	private entity chef1, chef2, chef3, grill, cooking, serve;
	private List<entity> entities;
	@Override
	public void create() {
		chef1 = new entity(new Texture(Gdx.files.internal("chef1.png")), new Rectangle(0, 0, 64, 64));
		chef2 = new entity(new Texture(Gdx.files.internal("chef2.png")), new Rectangle(200, 0, 64, 64));
		chef3 = new entity(new Texture(Gdx.files.internal("chef3.png")), new Rectangle(500, 0, 64, 64));
		grill = new entity(new Texture(Gdx.files.internal("grill.png")), new Rectangle(500, 150, 100, 100));
		cooking = new entity(new Texture(Gdx.files.internal("cooking.png")), new Rectangle(100, 150, 100, 100));
		serve = new entity(new Texture(Gdx.files.internal("Serve.png")), new Rectangle(700, 150, 100, 100));

		entities = Arrays.asList(chef1,chef2,chef3,grill,cooking,serve);

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
		batch.draw(chef3.image, chef3.body.x, chef3.body.y);
		batch.draw(grill.image, grill.body.x, grill.body.y);
		batch.draw(cooking.image, cooking.body.x, cooking.body.y);
		batch.draw(serve.image, serve.body.x, serve.body.y);
		batch.end();

		for(entity e:entities){
			if((e != chef1) & e.body.overlaps(chef1.body)){
				chef1.body.x = chef1.prevx;
				chef1.body.y = chef1.prevy;
			}
		}

		for(entity e:entities){
			if((e != chef2) & e.body.overlaps(chef2.body)){
				chef2.body.x = chef2.prevx;
				chef2.body.y = chef2.prevy;
			}
		}

		for(entity e:entities){
			if((e != chef3) & e.body.overlaps(chef3.body)){
				chef3.body.x = chef3.prevx;
				chef3.body.y = chef3.prevy;
			}
		}


		if (Gdx.input.isKeyPressed(Input.Keys.NUM_1)) {
			if (Gdx.input.isKeyPressed(Input.Keys.LEFT)){
				chef1.prevx = chef1.body.x;
				chef1.body.x -= 200 * Gdx.graphics.getDeltaTime();
			}
			if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
				chef1.prevx = chef1.body.x;
				chef1.body.x += 200 * Gdx.graphics.getDeltaTime();
			}
			if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
				chef1.prevy = chef1.body.y;
				chef1.body.y += 200 * Gdx.graphics.getDeltaTime();}
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
		} else if (Gdx.input.isKeyPressed(Input.Keys.NUM_3)) {
			if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
				chef3.prevx = chef3.body.x;
				chef3.body.x -= 200 * Gdx.graphics.getDeltaTime();
			}
			if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
				chef3.prevx = chef3.body.x;
				chef3.body.x += 200 * Gdx.graphics.getDeltaTime();
			}
			if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
				chef3.prevy = chef3.body.y;
				chef3.body.y += 200 * Gdx.graphics.getDeltaTime();
			}
			if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
				chef3.prevy = chef3.body.y;
				chef3.body.y -= 200 * Gdx.graphics.getDeltaTime();
			}
			if (chef3.body.x < 0) chef3.body.x = 0;
			if (chef3.body.x > 800 - 64) chef3.body.x = 800 - 64;
			if (chef3.body.y < 0) chef3.body.y = 0;
			if (chef3.body.y > 480 - 64) chef3.body.y = 480 - 64;
		}

	}

}
