package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MyGdxGame extends Game {
	SpriteBatch batch;
	Texture img;
	TelaRender renderer;
	Player player;
	
	@Override
	public void create () {
		player = new Player(50, 50);
		renderer = new TelaRender();
		batch = new SpriteBatch();
		renderer.createRender();
		//img = new Texture("badlogic.jpg");
	}
	
	@Override
	public void render() {
		
		Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		player.update();
		renderer.render();
		
		
		
	}

	
}
