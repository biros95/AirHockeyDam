package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.mygdx.game.AirHockey;
import com.mygdx.game.helpers.MyAssetManager;

import sun.rmi.runtime.Log;

/**
 * Created by MarcosPortatil on 18/04/2017.
 */

public class PlayScreen extends BaseScreen implements InputProcessor {
    //Creamos el campo y los dos jugadores.
    Sprite pista, player, player2;

    SpriteBatch batch;
    private int height;
    private int width;
    private float posX;
    private float posY;
    MyAssetManager myAssetManager;

    OrthographicCamera camera;


    public PlayScreen(AirHockey game) {
        super(game);
        myAssetManager = new MyAssetManager();
        Gdx.input.setInputProcessor(this);
    }


    @Override
    public void show() {
        //Obtenemos la altura y anchura de la pantalla para poder escalar la imagen.
        width = Gdx.graphics.getWidth();
        height = Gdx.graphics.getHeight();

        //Obtenemos la imagen de pista que será el fondo del juego
        pista = myAssetManager.cargarTextura("pista");
        player = myAssetManager.cargarTextura("player");
        player2 = myAssetManager.cargarTextura("player");

        //Posiciones de los jugadores.
        posX = height / 4;
        posY = width / 2;
        player.setPosition(posY, posX);
        player2.setPosition(width/2, (height/4)*3);
        pista.setSize(width, height);

        batch = new SpriteBatch();

    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        Gdx.app.log("MyTag", "my informative message");
        if(button == Buttons.LEFT){
            posX = screenX - player.getWidth()/2;
            posY = Gdx.graphics.getHeight() - screenY - player.getHeight()/2;
        }
        if(button == Buttons.RIGHT){
            posX = Gdx.graphics.getWidth()/2 - player.getWidth()/2;
            posY = Gdx.graphics.getHeight()/2 - player.getHeight()/2;
        }
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        Gdx.app.log("HOLA", "HOLA");

        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        player.setPosition(posX,posY);
        batch.begin();
        pista.draw(batch);
        player.draw(batch);
        player2.draw(batch);
        batch.end();

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        myAssetManager.getTextureAtlas().dispose();

    }
}
