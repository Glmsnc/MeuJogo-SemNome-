package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
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

	
	public Enemy(float x, float y) {
		body.x = pos.x = x;
		body.y = pos.y = y;
		body.height = body.width = 32;
		estadoAtual = ESTADO_ACTORS.PARADO;
	}
	
	public void update(Player player){
		
		//movimentos = seguindo o jogador
		if(this.pos.x != player.pos.x){	
			if (this.pos.x < player.pos.x){
			this.pos.x += ( Gdx.graphics.getDeltaTime() *150);
			}
			else{
				this.pos.x -= ( Gdx.graphics.getDeltaTime() *150);
			}
		}
		if(this.pos.y != player.pos.y){
			if (this.pos.y < player.pos.y ){
				this.pos.y += ( Gdx.graphics.getDeltaTime() * 150);
			}
			else{
				this.pos.y -= ( Gdx.graphics.getDeltaTime() * 150);
			}
		}
	
		body.x = pos.x;
		body.y = pos.y;
		if(body.overlaps(player.body) && player.estadoAtual == ESTADO_ACTORS.CORRENDO) {
			this.pos.x = 0;
			this.pos.y = 0;
		}
		//fim dos movimento
	}
	
	

}
