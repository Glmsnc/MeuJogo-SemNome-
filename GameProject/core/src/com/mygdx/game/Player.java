package com.mygdx.game;

import javax.management.timer.Timer;

//import com.badlogic.cubocy.Vector2;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Player {
	
	
	static int velocidade = 200;
	ESTADO_ACTORS estadoAtual;
	DIRECAO dirX, dirY;
	Rectangle body = new Rectangle();
	Circle area = new Circle();
	Vector2 pos = new Vector2();
	Vector2 raiz = new Vector2();
	Vector2 vel = new Vector2();
	long time = 0;
	DIRECAO dAnt = null;
	boolean dashing = false;
	
	public Player(float x, float y) {
		this.area.x = this.body.x = this.pos.x = x;
		this.area.y = this.body.y = this.pos.y = y;
		this.area.radius = 50; 
		this.body.height = this.body.width = 32;
		this.estadoAtual = ESTADO_ACTORS.PARADO;
		dirX = dirX.NONE;
		dirY = dirY.NONE;
	}
	
	
	public void update() {
		if(!dashing){
			checkKeys();
			Gdx.input.setInputProcessor(createInputAdapter());
		}
		else if(time < 0.5){
			dash();
		}else{
			time = 0;
			dashing = false;
		}

		comandos();
		
	}
	public void checkKeys() {
	if(this.estadoAtual != ESTADO_ACTORS.MORTO) {
		if(Gdx.input.isKeyPressed(Keys.W)) {
			dirY = direcao(dirY.CIMA);
		}
		if(Gdx.input.isKeyPressed(Keys.A)) {
			dirX = direcao(dirX.ESQUERDA);	
		}
		if(Gdx.input.isKeyPressed(Keys.S)) {
			dirY = direcao(dirY.BAIXO);	
		}
		
		if(Gdx.input.isKeyPressed(Keys.D)) {
			dirY = direcao(dirY.DIREITA);	
		}
		estadoAtual = ESTADO_ACTORS.ANDANDO;
	}
	
		velocidade = 200;
		this.area.x =this.body.x = this.pos.x;
		this.area.y =this.body.y = this.pos.y;
		//if(!Gdx.input.isKeyJustPressed(Keys.ANY_KEY)) this.estadoAtual = ESTADO_ACTORS.PARADO;
	}
	
	public void dash(){
		this.estadoAtual = ESTADO_ACTORS.CORRENDO;
		velocidade = 400;
			if(dirX.DIREITA.v) {
				direcao(dirX.DIREITA);
			}
			if(dirX.ESQUERDA.v) {
				direcao(dirX.ESQUERDA);
			}
			if(dirY.CIMA.v) {
				direcao(dirY.CIMA);
			}
			if(dirY.BAIXO.v) {
				direcao(dirY.BAIXO);
			

			time += Gdx.graphics.getDeltaTime();
		}
		
	}
	
	public InputAdapter createInputAdapter() {
	
		return new InputAdapter() {
			@Override
			public boolean touchDown(int screenX, int screenY, int pointer, int button) {
				raiz.x = screenX;
				raiz.y = screenY;
				System.out.println("Entrando");
				return super.touchDown(screenX, screenY, pointer, button);
			}
			
			@Override
			public boolean touchUp(int screenX, int screenY, int pointer, int button) {
				dashing = true;
				return super.touchUp(screenX, screenY, pointer, button);
			}

			@Override
			public boolean touchDragged(int screenX, int screenY, int pointer) {
			
				if(raiz.x < screenX) dirX = dirX.DIREITA;
				else if(raiz.x > screenX) dirX = dirX.ESQUERDA;
				else dirX = dirX.NONE;
				if(raiz.y > screenY) dirX = dirY.CIMA;
				else if(raiz.y < screenY) dirX = dirY.BAIXO;
				else dirY = dirY.NONE;
				zeraControle(screenX, screenY);
				return super.touchDragged(screenX, screenY, pointer);
			}
			
			
		};
		
	}
	public void comandos() {
		if(Gdx.input.isTouched()) {
			direcao(dirX);
			direcao(dirY);
		}
		else {
			dirX = dirX.NONE;
			dirY = dirY.NONE;
			this.estadoAtual = ESTADO_ACTORS.PARADO;
			//dir.DIREITA.v = false; dir.ESQUERDA.v = false; dir.CIMA.v = false; dir.BAIXO.v = false;
		}
	}
	
	public void zeraControle(int x, int y) {
		if(raiz.x+30 < x ) raiz.x = x-15;
		if(raiz.x-30 > x ) raiz.x = x+15;
		if(raiz.y+30 < y) raiz.y = y-15;
		if(raiz.y-30 > y ) raiz.y = y+15;
		
	}
	
	public DIRECAO direcao(DIRECAO dir) {
	switch(dir) {
		case BAIXO:
			dir.BAIXO.v = true; dir.CIMA.v = false;
			this.pos.y -= velocidade*Gdx.graphics.getDeltaTime();
			break;
		case CIMA:
			dir.CIMA.v = true; dir.BAIXO.v = false;
			this.pos.y += velocidade*Gdx.graphics.getDeltaTime();
			break;
		case ESQUERDA:
			dir.ESQUERDA.v = true; dir.DIREITA.v = false;
			this.pos.x -= velocidade*Gdx.graphics.getDeltaTime();
			break;
		case DIREITA:
			dir.DIREITA.v = true; dir.ESQUERDA.v = false;
			this.pos.x += velocidade*Gdx.graphics.getDeltaTime();
			break;
		case NONE:
			dir.DIREITA.v = false; dir.ESQUERDA.v = false; dir.CIMA.v = false; dir.BAIXO.v = false;
			break;
		}
		
		return dir;	
	}

}
