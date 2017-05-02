package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.mygdx.game.AirHockey;

/**
 * Created by MarcosPortatil on 18/04/2017.
 */

public class PlayScreen extends BaseScreen {
    TextureAtlas textureAtlas;
    Sprite pista;
    SpriteBatch batch;
    private int height;
    private int width;

    OrthographicCamera camera;


    public PlayScreen(AirHockey game) {
        super(game);
        //Obtenemos el TexturePack
        textureAtlas = new TextureAtlas("textures.txt");

    }


    @Override
    public void show() {
        //Obtenemos la altura y anchura de la pantalla para poder escalar la imagen.
        width = Gdx.graphics.getWidth();
        height = Gdx.graphics.getHeight();

        //Obtenemos la imagen de pista que será el fondo del juego.
        pista = textureAtlas.createSprite("pista");
        batch = new SpriteBatch();

       pista.setSize(width, height);
       /** pista.setOrigin(pista.getWidth() / 2,
                pista.getHeight() / 2);
        pista.setPosition(-pista.getWidth() / 2,
                -pista.getHeight() / 2);
**/

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        pista.draw(batch);
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
        textureAtlas.dispose();

    }
}
