package com.mygdx.game.screens;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.loaders.AssetLoader;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.mygdx.game.AirHockey;
import com.badlogic.gdx.assets.AssetManager;
import com.mygdx.game.helpers.InputHandler;
import com.mygdx.game.helpers.MyAssetManager;

/**
 * Created by MarcosPortatil on 18/04/2017.
 */

public class MenuScreen extends  BaseScreen implements Screen {
    AirHockey game;
    Texture Tboton1Jugador,Tboton2Jugadores,TbotonConfiguracion,TbotonSalir,Tlogo;
    SpriteBatch batch;
    public final MyAssetManager assetManager = new MyAssetManager();
    float ancho = Gdx.graphics.getWidth();
    float altura = Gdx.graphics.getHeight();
    Stage stage;
    ImageButton boton1Jugador;
    ImageButton boton2Jugadores,botonConfiguracion,botonSalir,logo;


    public MenuScreen(final AirHockey game) {
        super(game);
        assetManager.load();
        //BOTON DE UN JUGADOR
        Tboton1Jugador = new Texture("1Player.png");
        TextureRegion textureRegion1jugador = new TextureRegion(Tboton1Jugador);
        TextureRegionDrawable drawable1Jugador = new TextureRegionDrawable(textureRegion1jugador);
        boton1Jugador = new ImageButton(drawable1Jugador);
        boton1Jugador.setPosition(300,150);
        boton1Jugador.setBounds(ancho/2 - 100,altura/2 - Tboton1Jugador.getHeight(), altura/4, ancho/8);
        boton1Jugador.getImageCell().expand().fill();
        boton1Jugador.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                // Take me to the game screen!
                game.setScreen(game.gameScreen);
            }
        });
        //BOTON DE DOS JUGADORES
        Tboton2Jugadores = new Texture("2Player.png");
        TextureRegion textureRegion1jugador2 = new TextureRegion(Tboton2Jugadores);
        TextureRegionDrawable drawable1Jugador2 = new TextureRegionDrawable(textureRegion1jugador2);
        boton2Jugadores = new ImageButton(drawable1Jugador2);
        boton2Jugadores.setPosition(ancho/2 - 100,altura/2 - Tboton2Jugadores.getHeight() - 300);
        boton2Jugadores.setBounds(ancho/2 - 100,altura/2 - Tboton2Jugadores.getHeight() - 100,  altura/4, ancho/8);
        boton2Jugadores.getImageCell().expand().fill();

        boton2Jugadores.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                // Take me to the game screen!
                game.setScreen(game.gameScreen);
            }
        });

        //BOTON CONFIGURACION
        TbotonConfiguracion = new Texture("Options.png");
        TextureRegion textureRegion1jugador3 = new TextureRegion(TbotonConfiguracion);
        TextureRegionDrawable drawable1Jugador3 = new TextureRegionDrawable(textureRegion1jugador3);
        botonConfiguracion = new ImageButton(drawable1Jugador3);
        botonConfiguracion.setPosition( ancho / 2f - 100f,altura/2 - TbotonConfiguracion.getHeight()-1);
        botonConfiguracion.setBounds( ancho/2 - 100,altura/2 - TbotonConfiguracion.getHeight() - 200,  altura/4, ancho/8);
        botonConfiguracion.getImageCell().expand().fill();

        botonConfiguracion.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                // Take me to the game screen!
                game.setScreen(game.optionsScreen);
            }
        });

        //LOGOTIPO
        Tlogo = new Texture("LogoPequeño.png");
        TextureRegion textureRegion1jugador4 = new TextureRegion(Tlogo);
        TextureRegionDrawable drawable1Jugador4 = new TextureRegionDrawable(textureRegion1jugador4);
        logo = new ImageButton(drawable1Jugador4);
        logo.setPosition(ancho - ancho/2-Tlogo.getWidth(),altura/2);
        logo.setBounds(ancho/4 -270, altura/4 + 290, altura - 100, ancho - 200);
        logo.getImageCell().expand().fill();

        //BOTON DE SALIR
        TbotonSalir = new Texture("button.png");
        TextureRegion textureRegion1jugador5 = new TextureRegion(TbotonSalir);
        TextureRegionDrawable drawable1Jugador5 = new TextureRegionDrawable(textureRegion1jugador5);
        botonSalir = new ImageButton(drawable1Jugador5);
        botonSalir.setPosition(ancho/2 - 100,altura/2 - TbotonSalir.getHeight() - 450);
        botonSalir.setBounds(ancho/2 - 100,altura/2 - TbotonConfiguracion.getHeight() - 300,  altura/4, ancho/8);
        botonSalir.getImageCell().expand().fill();

        botonSalir.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                // Take me to the game screen!

            }
        });
        stage = new Stage(new FitViewport(ancho,altura));
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        Gdx.input.setInputProcessor(stage);
        Gdx.gl.glClearColor(255,255,255,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.addActor(boton1Jugador);
        stage.draw();
        stage.addActor(boton2Jugadores);
        stage.addActor(botonConfiguracion);
        stage.addActor(botonSalir);
        stage.addActor(logo);
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