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
	SpriteBatch batch = new SpriteBatch(5460);
	Player player;
	BitmapFont font = new BitmapFont();
	static int cont= 0;
	Animation<TextureRegion> playerLeft;
	Animation<TextureRegion> playerRight;
	
	public TelaRender() {
		player = new Player(50, 50);
		/*this.cam = new OrthographicCamera(24,16);
		this.cam.position.set(player.pos.x, player.pos.y, 0);*/
	}


	public void createRender(){
		
		Texture bobTexture = new Texture(Gdx.files.internal("spriteSheetLink.png"));
		TextureRegion[] split = new TextureRegion(bobTexture).split(17, 17)[0];
		TextureRegion[] mirror = new TextureRegion(bobTexture).split(17, 17)[0];
		//inverte o sprite
		for (TextureRegion region : mirror)
			region.flip(true, false);
		//spikes = split[5];
		playerLeft = new Animation(0.3f, mirror[9], mirror[10], mirror[11], mirror[12], mirror[13]);
		playerRight = new Animation(0.3f, split[9], split[10], split[11], split[12], split[13]);
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
		Animation<TextureRegion> anim = null;
		
		cont++;
		if(cont >= 16) {
			cont =0;
		}
		boolean loop = true;
		player.update();
		if( Gdx.input.isKeyPressed(Keys.A)  || player.estadoAtual == ESTADO_PLAYER.PARADO) {
			anim  = playerLeft;
		}
		
		if(Gdx.input.isKeyPressed(Keys.D)  || player.estadoAtual == ESTADO_PLAYER.CORRENDO){
			player.estadoAtual = ESTADO_PLAYER.CORRENDO;
			anim =playerRight;
					
			
		}
		batch.draw(anim.getKeyFrame(cont, true), player.pos.x, player.pos.y, 128, 128);
		font.draw(batch, "contador"+cont, 400,400);
	}
	}

