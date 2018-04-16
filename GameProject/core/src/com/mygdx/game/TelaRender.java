package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.SpriteCache;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ImmediateModeRenderer20;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.Game;

public class TelaRender {
	
	OrthographicCamera camera;
	SpriteCache cache;
	SpriteBatch batch = new SpriteBatch();
	Player player;
	Enemy enemy;
	BitmapFont font = new BitmapFont();
	static int cont= 0;
	Animation<TextureRegion> playerLeft;
	Animation<TextureRegion> playerRight;
	Animation<TextureRegion> playerIdle;
	Texture ctrl;
	public TelaRender() {
		player = new Player(400, 240);
		enemy = new Enemy (240,350);
		/*this.cam = new OrthographicCamera(24,16);
		this.cam.position.set(player.pos.x, player.pos.y, 0);*/
	}


	public void createRender(){
		ctrl = new Texture(Gdx.files.internal("circlecontrol.png"));
		Texture bobTexture = new Texture(Gdx.files.internal("bob.png"));
		TextureRegion[] split = new TextureRegion(bobTexture).split(19, 19)[0];
		TextureRegion[] mirror = new TextureRegion(bobTexture).split(19, 19)[0];
		//inverte o sprite
		for (TextureRegion region : mirror)
			region.flip(true, false);
		//spikes = split[5];
		playerLeft =  new Animation(0.1f, mirror[0], mirror[1]);
		playerIdle =  new Animation(0.1f, mirror[3], mirror[4]);
		playerRight = new Animation(0.1f, split[0], split[1]);
		//camera = new OrthographicCamera();
		//camera.setToOrtho(true, 800, 480);

		
	
	}
	
	
	public void render() {
		//camera.update();
		//batch.setProjectionMatrix(camera.combined);
		batch.begin();
		playerRender();
		enemyRender();
		controlRender();
		batch.end();


	}
	
	   
	public void playerRender() {
		Animation<TextureRegion> anim = playerIdle;	
		cont++;
		if(cont >= 16) {
			cont =0;
		}
		
		player.update();
		if( player.dirX == DIRECAO.ESQUERDA) {
			//player.estadoAtual = ESTADO_ACTORS.CORRENDO;
			anim  = playerLeft;
		}
		
		if(player.dirX == DIRECAO.DIREITA){
			//player.estadoAtual = ESTADO_ACTORS.CORRENDO;
			anim =playerRight;
					
			
		}
		batch.draw(anim.getKeyFrame(cont, true), player.pos.x, player.pos.y, 32, 32);
		font.draw(batch,""+player.estadoAtual	, 400,400 );
		
	}
	
	
	public void enemyRender() {
		
		
		Animation<TextureRegion> anim = playerLeft;
		boolean loop = true;
		enemy.update(player);
		cont++;
		if(cont >= 16) {
			cont =0;
		}
		if(enemy.estadoAtual != ESTADO_ACTORS.MORTO)
			batch.draw(anim.getKeyFrame(1), enemy.pos.x, enemy.pos.y, 32, 32);

		
	}
	
	public void controlRender() {
		if(Gdx.input.isTouched()) {
		batch.draw(ctrl, player.raiz.x-20, player.raiz.y-20, 40,40);
		}
	}
	
	}

