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
	TelaRender render;
	
	@Override
	public void create () {
		render = new TelaRender();
		batch = new SpriteBatch();
		//img = new Texture("badlogic.jpg");
	}
	
	@Override
	public void render() {
		render.render();
	}

	
}
