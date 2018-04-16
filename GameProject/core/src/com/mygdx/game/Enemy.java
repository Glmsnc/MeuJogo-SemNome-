package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;



public class Enemy {
	
	public ESTADO_ACTORS estadoAtual;
	public Vector2 pos = new Vector2();
	public Vector2 accel = new Vector2();
	public Vector2 vel = new Vector2();
	SpriteBatch batch = new SpriteBatch();
	BitmapFont font = new BitmapFont();
	Rectangle body = new Rectangle();
	DIRECAO dir;
	Circle area = new Circle();
	float delay = 0;
	int velocidade  = 150;
	
	public Enemy(float x, float y) {
		this.area.x = this.body.x = this.pos.x = x;
		this.area.y = this.body.y = this.pos.y = y;
		this.area.radius = 50; 
		this.body.height = this.body.width = 32;
		estadoAtual = ESTADO_ACTORS.ANDANDO;
		dir = DIRECAO.NONE;
		
	}
	
	public void update(Player player){
		//movimentos = seguindo o jogador
		if(this.estadoAtual != ESTADO_ACTORS.PARADO) {
			if(this.pos.x != player.pos.x){	
				if (this.pos.x < player.pos.x){
					direcao(dir.DIREITA);
				}
				else{
					direcao(dir.ESQUERDA);
				}
			}
			if(this.pos.y != player.pos.y){
				if (this.pos.y < player.pos.y ){
					direcao(dir.CIMA);
				}
				else{
					direcao(dir.BAIXO);
				}
			}
		}
		if(this.area.overlaps(player.area)) {
			estadoAtual = ESTADO_ACTORS.PARADO;
		
		}
		this.area.x = body.x = pos.x;
		this.area.y = body.y = pos.y;
		if(body.overlaps(player.body) && player.estadoAtual == ESTADO_ACTORS.CORRENDO) {
			this.pos.x = 0;
			this.pos.y = 0;
			
		}
		
		
		delay += Gdx.graphics.getDeltaTime();
		if(delay > 1.5){
			//System.out.println("wait test");
			//estadoAtual = ESTADO_ACTORS.ANDANDO;
			velocidade = 150;
			delay = 0;
		}
		
		while(delay > 1 && this.estadoAtual == ESTADO_ACTORS.PARADO){
			estadoAtual = ESTADO_ACTORS.ANDANDO;
			// velocidade = 400;
			delay = 0;
		}
		//fim dos movimento
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
