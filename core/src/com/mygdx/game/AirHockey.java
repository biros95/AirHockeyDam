package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.helpers.MyAssetManager;
import com.mygdx.game.screens.BaseScreen;
import com.mygdx.game.screens.GameOverScreen;
import com.mygdx.game.screens.MenuScreen;
import com.mygdx.game.screens.PlayScreen;
import com.mygdx.game.screens.SplashScreen;

public class AirHockey extends Game implements ApplicationListener {
	public static int WIDTH;
	public static int HEIGHT;
	private MyAssetManager manager;

	public BaseScreen loadingScreen, menuScreen, gameScreen, gameOverScreen, creditsScreen;
	@Override
	public void create () {
		manager = new MyAssetManager();
		manager.getManager().update();
		manager.load();
		WIDTH = Gdx.graphics.getWidth();
		HEIGHT = Gdx.graphics.getHeight();
		setScreen(new SplashScreen(this));
	}

	@Override
	public void dispose() {
		super.dispose();
	}

	@Override
	public void render () {
		super.render();
	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
	}

	@Override
	public void pause() {
		super.pause();
	}

	@Override
	public void resume() {
		super.resume();
	}

	public AssetManager getManager() {
		return manager.getManager();
	}

	public void finishLoading() {
		menuScreen = new MenuScreen(this);
		gameScreen = new PlayScreen(this);
		gameOverScreen = new GameOverScreen(this);
		setScreen(menuScreen);
	}



}
