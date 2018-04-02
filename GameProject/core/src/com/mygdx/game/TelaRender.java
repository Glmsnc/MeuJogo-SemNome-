package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.SpriteCache;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ImmediateModeRenderer20;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.Game;

public class TelaRender {
	
	OrthographicCamera cam;
	SpriteCache cache;
	SpriteBatch batch = new SpriteBatch(5460);
	
	Player player;
	
	Animation<TextureRegion> playerLeft;
	
	public TelaRender() {
		player = new Player(50, 50);
		/*this.cam = new OrthographicCamera(24,16);
		this.cam.position.set(player.pos.x, player.pos.y, 0);*/
	}


	public void createRender(){
		//this.tile = new TextureRegion(new Texture(Gdx.files.internal("data/tile.png")), 0, 0, 20, 20);
		Texture bobTexture = new Texture(Gdx.files.internal("bob.png"));
		TextureRegion[] split = new TextureRegion(bobTexture).split(20, 20)[0];
		TextureRegion[] mirror = new TextureRegion(bobTexture).split(20, 20)[0];
		//inverte o sprite
		for (TextureRegion region : mirror)
			region.flip(true, false);
		//	spikes = split[5];
		//playerRight = new Animation(0.1f, split[0], split[1]);
		playerLeft = new Animation(0.1f, split[0], split[1]);
	
	}
	
	
	public void render() {
		playerRender();
	}
	
	public void playerRender() {
		Animation<TextureRegion> anim = playerLeft;
		TextureRegion tx = playerLeft.getKeyFrame(1);
		boolean loop = true;
		if(player.estadoAtual == ESTADO_PLAYER.PARADO) {
			anim  = playerLeft;
		}

		batch.draw(tx, player.pos.x, player.pos.y, 1, 1);
	}
	}

