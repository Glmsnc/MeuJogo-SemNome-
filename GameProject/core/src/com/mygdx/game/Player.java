package com.mygdx.game;

import javax.management.timer.Timer;

//import com.badlogic.cubocy.Vector2;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.DelayAction;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.Timer.Task;

public class Player {
	
	static int life = 3;
	static int velocidade = 200;
	ESTADO_ACTORS estadoAtual;
	DIRECAO dir;
	Rectangle body = new Rectangle();
	Vector2 pos = new Vector2();
	Vector2 accel = new Vector2();
	Vector2 vel = new Vector2();
	long time = 0;
	DIRECAO BAIXO = dir.BAIXO, CIMA = dir.CIMA, ESQUERDA = dir.ESQUERDA, DIREITA = dir.DIREITA;
	
	public Player(float x, float y) {
		this.body.x = this.pos.x = x;
		this.body.y = this.pos.y = y;
		this.body.height = this.body.width = 32;
		this.estadoAtual = ESTADO_ACTORS.PARADO;
	}
	
	
	public void update() {
		checkKeys();
		Gdx.input.setInputProcessor(createInputAdapter());
		System.out.println(DIREITA.v +" "+ ESQUERDA.v +" "+ CIMA.v+" "+ BAIXO.v);
		boolean teste = (DIREITA.v || ESQUERDA.v) || (CIMA.v || BAIXO.v);
		if(!teste) {
			this.estadoAtual = ESTADO_ACTORS.PARADO;
		}
	}
	public void checkKeys() {
	if(this.estadoAtual != ESTADO_ACTORS.MORTO) {
		if(Gdx.input.isKeyPressed(Keys.W)) {
			dir = direcao(dir.CIMA);
		}else {
			CIMA.v = false;
		}
		if(Gdx.input.isKeyPressed(Keys.A)) {
			dir = direcao(dir.ESQUERDA);	
		}else {
			ESQUERDA.v = false;
		}
		if(Gdx.input.isKeyPressed(Keys.S)) {
			dir = direcao(dir.BAIXO);	
		}else {
			BAIXO.v = false;
		}
		
		if(Gdx.input.isKeyPressed(Keys.D)) {
			dir = direcao(dir.DIREITA);	
		}else {
			DIREITA.v = false;
		}
		
	}
	



		 velocidade = 200;
		this.body.x = this.pos.x;
		this.body.y = this.pos.y;
		//if(!Gdx.input.isKeyJustPressed(Keys.ANY_KEY)) this.estadoAtual = ESTADO_ACTORS.PARADO;
	}
	
	public void dash(){
		this.estadoAtual = ESTADO_ACTORS.CORRENDO;
		velocidade = 400;
		for(int i = 0; i<5; i++) {
		if(DIREITA.v) {
			direcao(DIREITA);
		}
		if(ESQUERDA.v) {
			direcao(ESQUERDA);
		}
		if(CIMA.v) {
			direcao(CIMA);
		}
		if(BAIXO.v) {
			direcao(BAIXO);
		}
		}
	}
	
	public InputAdapter createInputAdapter() {
		InputAdapter tsAdapter = new InputAdapter();
	
		return new InputAdapter() {
			@Override
			public boolean touchDown(int screenX, int screenY, int pointer, int button) {
				System.out.println("X: "+ screenX+" Y: "+screenY);
				return super.touchDown(screenX, screenY, pointer, button);
			}
			
			@Override
			public boolean touchUp(int screenX, int screenY, int pointer, int button) {
				dash();
				return super.touchUp(screenX, screenY, pointer, button);
			}
			
			
			
		};
		
	}
	
	public DIRECAO direcao(DIRECAO dir) {
	switch(dir) {
		case BAIXO:
			BAIXO.v = true; CIMA.v = false;
			this.pos.y -= velocidade*Gdx.graphics.getDeltaTime();
			break;
		case CIMA:
			CIMA.v = true; BAIXO.v = false;
			this.pos.y += velocidade*Gdx.graphics.getDeltaTime();
			break;
		case ESQUERDA:
			ESQUERDA.v = true; DIREITA.v = false;
			this.pos.x -= velocidade*Gdx.graphics.getDeltaTime();
			break;
		case DIREITA:
			DIREITA.v = true; ESQUERDA.v = false;
			this.pos.x += velocidade*Gdx.graphics.getDeltaTime();
			break;
		}
		
		return dir;
		
	
		
	}

}
