package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.AirHockey;
import com.mygdx.game.helpers.InputHandler;
import com.mygdx.game.helpers.MyAssetManager;
import com.mygdx.game.objects.Disk;
import com.mygdx.game.objects.Pista;
import com.mygdx.game.objects.Player;



/**
 * Created by MarcosPortatil on 18/04/2017.
 */

public class PlayScreen extends BaseScreen {
    //Creamos el campo y los dos jugadores.
    Sprite pista, player, player2, disco;
    Player jugador1, jugador2;
    Disk disk;
    private Stage stage;
    Pista pistaHockey;

    SpriteBatch batch, batch2;
    private int height;
    private int width;
    MyAssetManager myAssetManager;
    ExtendViewport viewport;
    private SpriteBatch spriteBatch;
    OrthographicCamera camera, camera2;
    ShapeRenderer shapeRenderer;


    public PlayScreen(AirHockey game) {
        super(game);
        //Obtenemos la altura y anchura de la pantalla para poder escalar la imagen.
        width = Gdx.graphics.getWidth();
        height = Gdx.graphics.getHeight();
        myAssetManager = new MyAssetManager();
        Gdx.input.setInputProcessor(new InputHandler(this));
        camera = new OrthographicCamera(width, height);
        camera2 = new OrthographicCamera(width*1.5f, height*1.5f);

        shapeRenderer = new ShapeRenderer();

        batch = new SpriteBatch();
        batch2 = new SpriteBatch();
        viewport = new ExtendViewport(width, height, camera);
        stage = new Stage(viewport, batch);

        spriteBatch = new SpriteBatch();


        //Creación de Sprites
        pista = myAssetManager.cargarTextura("pista");
        disco = myAssetManager.cargarTextura("pelota");
        player = myAssetManager.cargarTextura("player");
        player2 = myAssetManager.cargarTextura("player");


        pista.setSize(width, height);

        //Creación de actores
        jugador1= new Player(player, "Jugador 1");
        disk = new Disk(0, 0, disco, pista.getHeight()/2, pista.getWidth()/2);
        pistaHockey = new Pista(pista, "pista");
        pistaHockey.setPosition(0, 0);


        stage.addActor(pistaHockey);
        stage.addActor(jugador1);
        stage.addActor(disk);



    }


    @Override
    public void show() {


       // player2.setPosition(width/2, (height/4)*3);


        Gdx.input.setInputProcessor(new InputHandler(this));

    }



    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();

        batch.setProjectionMatrix(camera.combined);
        stage.draw();

        stage.act(delta);
        if (Intersector.overlaps(jugador1.getCircle(), disk.getCircle())){
            System.out.println("Colision!");
        }
       // System.out.println(stage.getActors().size);



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

    public Player getJugador1() {
        return jugador1;
    }

    public Stage getStage() {
        return stage;
    }
}
