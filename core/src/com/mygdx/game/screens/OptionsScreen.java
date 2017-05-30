package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.mygdx.game.AirHockey;
import com.mygdx.game.helpers.MyAssetManager;

/**
 * Created by ALUMNEDAM on 30/05/2017.
 */

public class OptionsScreen extends BaseScreen implements Screen {

    AirHockey game;
    Texture TMALLET, TPUCK, TbotonSalir, Tlogo,Tpucks,Ton,Toff,Tmallets,Tmusic;
    SpriteBatch batch;
    public final MyAssetManager assetManager = new MyAssetManager();
    float ancho = Gdx.graphics.getWidth();
    float altura = Gdx.graphics.getHeight();
    Stage stage;
    int color = 0;
    ImageButton Mallet,on,off,pucks,mallets,music;
    ImageButton puck, botonSalir, logo;
    private Skin skin = new Skin();


    public OptionsScreen(final AirHockey game) {
        super(game);
        assetManager.load();

        //LOGOTIPO
        Tlogo = new Texture("Options2.png");
        TextureRegion textureRegion1jugador4 = new TextureRegion(Tlogo);
        TextureRegionDrawable drawable1Jugador4 = new TextureRegionDrawable(textureRegion1jugador4);
        logo = new ImageButton(drawable1Jugador4);
        logo.setPosition(ancho - ancho / 2 - Tlogo.getWidth(), altura / 2);
        logo.setBounds(ancho / 2 - 150, altura / 2 + Tlogo.getHeight() + 150, 300, 300);
        logo.getImageCell().expand().fill();

        //BOTON DE IR ATRAS
        TbotonSalir = new Texture("Atras.jpg");
        TextureRegion textureRegion1jugador5 = new TextureRegion(TbotonSalir);
        TextureRegionDrawable drawable1Jugador5 = new TextureRegionDrawable(textureRegion1jugador5);
        botonSalir = new ImageButton(drawable1Jugador5);
        botonSalir.setPosition(ancho / 2 - 100, altura / 2 - TbotonSalir.getHeight() - 450);
        botonSalir.setBounds(ancho / 2 - 250, altura / 2 - TbotonSalir.getHeight() - 380, 100, 100);
        botonSalir.getImageCell().expand().fill();

        botonSalir.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                // Take me to the game screen!
                game.setScreen(game.menuScreen);
            }
        });
        //MALLET(JUGADOR)
        TMALLET = new Texture("MALLET.png");
        TextureRegion textureRegion1jugador = new TextureRegion(TMALLET);
        TextureRegionDrawable drawable1Jugador = new TextureRegionDrawable(textureRegion1jugador);
        Mallet = new ImageButton(drawable1Jugador);
        Mallet.setPosition(ancho - ancho / 2 - TMALLET.getWidth(), altura / 2);
        Mallet.setBounds(ancho / 2 + 50, altura / 2 + TMALLET.getHeight() - 100, 100, 100);
        Mallet.getImageCell().expand().fill();
        stage = new Stage(new FitViewport(ancho, altura));

        //PUCK(DISCO)
        TPUCK = new Texture("PUCK.png");
        TextureRegion textureRegion1jugador2 = new TextureRegion(TPUCK);
        TextureRegionDrawable drawable1Jugador2 = new TextureRegionDrawable(textureRegion1jugador2);
        puck = new ImageButton(drawable1Jugador2);
        puck.setPosition(ancho - ancho / 2 - TPUCK.getWidth(), altura / 2);
        puck.setBounds(ancho / 2 + 50, altura / 2 + TPUCK.getHeight() - 230, 100, 100);
        puck.getImageCell().expand().fill();
        stage = new Stage(new FitViewport(ancho, altura));

        //MALLETS
        Tmallets = new Texture("MALLETS.png");
        TextureRegion textureRegion1jugadorX = new TextureRegion(Tmallets);
        TextureRegionDrawable drawable1JugadorX = new TextureRegionDrawable(textureRegion1jugadorX);
        mallets = new ImageButton(drawable1JugadorX);
        mallets.setPosition(ancho - ancho / 2 - Tmallets.getWidth(), altura / 2);
        mallets.setBounds(ancho / 2 - 150, altura / 2 + TMALLET.getHeight() - 100, 140, 140);
        mallets.getImageCell().expand().fill();
        stage = new Stage(new FitViewport(ancho, altura));

        //Pucks
        Tpucks = new Texture("puck2.png");
        TextureRegion textureRegion1jugadorY = new TextureRegion(Tpucks);
        TextureRegionDrawable drawable1JugadorY = new TextureRegionDrawable(textureRegion1jugadorY);
        pucks = new ImageButton(drawable1JugadorY);
        pucks.setPosition(ancho - ancho / 2 - Tpucks.getWidth(), altura / 2);
        pucks.setBounds(ancho / 2 - 150, altura / 2 + Tpucks.getHeight() - 140, 140, 140);
        pucks.getImageCell().expand().fill();

        //MUSIC
        Tmusic = new Texture("MUSIC.png");
        TextureRegion textureRegion1jugadorM = new TextureRegion(Tmusic);
        TextureRegionDrawable drawable1JugadorM = new TextureRegionDrawable(textureRegion1jugadorM);
        music = new ImageButton(drawable1JugadorM);
        music.setPosition(ancho - ancho / 2 - Tpucks.getWidth(), altura / 2);
        music.setBounds(ancho / 2 - 150, altura / 2 + Tpucks.getHeight() - 240, 100, 100);
        music.getImageCell().expand().fill();

        //ON
        //MUSIC
        Ton = new Texture("ON.png");
        TextureRegion ON = new TextureRegion(Ton);
        TextureRegionDrawable ONDRAW = new TextureRegionDrawable(ON);
        on = new ImageButton(ONDRAW);
        on.setPosition(ancho - ancho / 2 - Tpucks.getWidth(), altura / 2);
        on.setBounds(ancho / 2 - 100, altura / 2 + Tpucks.getHeight() - 340, 100, 100);
        on.getImageCell().expand().fill();

        //Off
        //MUSIC
        Ton = new Texture("OFF.png");
        TextureRegion OFF = new TextureRegion(Ton);
        TextureRegionDrawable OFFDRAW = new TextureRegionDrawable(OFF);
        off = new ImageButton(OFFDRAW);
        off.setPosition(ancho - ancho / 2 - Tpucks.getWidth(), altura / 2);
        off.setBounds(ancho / 2 +20, altura / 2 + Tpucks.getHeight() - 340, 100, 130);
        off.getImageCell().expand().fill();



        stage = new Stage(new FitViewport(ancho, altura));
    }


    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        Gdx.input.setInputProcessor(stage);
        Gdx.gl.glClearColor(255, 255, 255, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.addActor(logo);
        stage.addActor(botonSalir);
        stage.addActor(Mallet);
        stage.addActor(puck);
        stage.addActor(mallets);
        stage.addActor(pucks);
        stage.addActor(music);
        stage.addActor(on);
        stage.addActor(off);
        stage.draw();
//        stage.dispose();


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

    }

}
