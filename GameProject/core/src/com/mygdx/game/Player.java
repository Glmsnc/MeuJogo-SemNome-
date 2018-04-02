package com.mygdx.game;

//import com.badlogic.cubocy.Vector2;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.Vector2;

public class Player {
	
	static int life = 3;
	static final int velocidade = 20;
	ESTADO_PLAYER estadoAtual;
	
	
	Vector2 pos = new Vector2();
	Vector2 accel = new Vector2();
	Vector2 vel = new Vector2();
	
	public Player(float x, float y) {
		pos.x = x;
		pos.y = y;
		estadoAtual = ESTADO_PLAYER.PARADO;
	}
	
	
	public void update() {
		
	}
	public void checkKeys() {
		
		if(Gdx.input.isButtonPressed(Keys.W)) {
			pos.x = velocidade*Gdx.graphics.getDeltaTime();
			estadoAtual = ESTADO_PLAYER.CORRENDO;
			
		}
		if(Gdx.input.isButtonPressed(Keys.A)) {
			pos.y = velocidade*Gdx.graphics.getDeltaTime();
			
		}
		if(Gdx.input.isButtonPressed(Keys.S)) {
			pos.x = -velocidade*Gdx.graphics.getDeltaTime();
			
		}
		
		if(Gdx.input.isButtonPressed(Keys.D)) {
			pos.y = -velocidade*Gdx.graphics.getDeltaTime();
			
		}
	}

}
