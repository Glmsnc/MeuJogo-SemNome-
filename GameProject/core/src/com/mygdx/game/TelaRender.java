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
	BitmapFont font = new BitmapFont();
	static int cont= 0;
	Animation<TextureRegion> playerLeft;
	Animation<TextureRegion> playerRight;
	
	public TelaRender() {
		player = new Player(400, 240);
		/*this.cam = new OrthographicCamera(24,16);
		this.cam.position.set(player.pos.x, player.pos.y, 0);*/
	}


	public void createRender(){
		
		Texture bobTexture = new Texture(Gdx.files.internal("bob.png"));
		TextureRegion[] split = new TextureRegion(bobTexture).split(19, 19)[0];
		TextureRegion[] mirror = new TextureRegion(bobTexture).split(19, 19)[0];
		//inverte o sprite
		for (TextureRegion region : mirror)
			region.flip(true, false);
		//spikes = split[5];
		playerLeft =  new Animation(0.1f, mirror[0], mirror[1]);
		playerRight = new Animation(0.1f, split[0], split[1]);
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 480);

		
	
	}
	
	
	public void render() {
		 camera.update();
		 batch.setProjectionMatrix(camera.combined);
		batch.begin();
		playerRender();
		batch.end();


	}
	
	   
	public void playerRender() {
		Animation<TextureRegion> anim = playerLeft;
		
		cont++;
		if(cont >= 16) {
			cont =0;
		}
		boolean loop = true;
		player.update();
		if( Gdx.input.isKeyPressed(Keys.A)  || player.estadoAtual == ESTADO_PLAYER.CORRENDO) {
			anim  = playerLeft;
		}
		
		if(Gdx.input.isKeyPressed(Keys.D)  || player.estadoAtual == ESTADO_PLAYER.CORRENDO){
			player.estadoAtual = ESTADO_PLAYER.CORRENDO;
			anim =playerRight;
					
			
		}
		batch.draw(anim.getKeyFrame(cont, true), player.pos.x, player.pos.y, 32, 32);
		font.draw(batch, "contador"+cont, 400,400);
	}
	}

