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
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;
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
import com.mygdx.game.objects.Bounds;
//import com.mygdx.game.objects.BoundsGround;
import com.mygdx.game.objects.Bounds2;
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

    private Box2DDebugRenderer debugRenderer;

    //Box2D

    public World world;

    SpriteBatch batch, batch2;
    private int height;
    private int width;

    Bounds bounds;
    Bounds2 bounds2;

    private float force;
    private double module, angle;
    MyAssetManager myAssetManager;
    ExtendViewport viewport;
    private SpriteBatch spriteBatch;
    OrthographicCamera camera, camera2;
    ShapeRenderer shapeRenderer;
    private final int VELOCITYITERATIONS = 8, POSITIONITERATIONS = 3;


    public PlayScreen(AirHockey game) {
        super(game);
        //Obtenemos la altura y anchura de la pantalla para poder escalar la imagen.
        width = Gdx.graphics.getWidth();
        height = Gdx.graphics.getHeight();

        System.out.println("With " + width + "\nHeight " + height);
        myAssetManager = new MyAssetManager();
        Gdx.input.setInputProcessor(new InputHandler(this));

        angle = 0;




        debugRenderer = new Box2DDebugRenderer();

        float screenWidth = 800;
        float screenHeight = 400;
        float gameWidth = 203;
        float gameHeight = screenHeight / (screenWidth / gameWidth);

        camera = new OrthographicCamera(gameWidth/10, gameHeight/10);
        camera2 = new OrthographicCamera(gameWidth*1.5f, gameHeight*1.5f);

            this.world = new World(new Vector2(0, 0), true);
        bounds2 = new Bounds2(world);
        // boundsGround = new BoundsGround(world);
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
        jugador1 = new Player(player, "Jugador 1", world);
        disk = new Disk(0, 0, disco, pista.getHeight() / 2, pista.getWidth() / 2, world);
        pistaHockey = new Pista(pista, "pista");
        pistaHockey.setPosition(0, 0);


        stage.addActor(pistaHockey);
        stage.addActor(jugador1);
        stage.addActor(disk);


    }

    public Disk getDisk() {
        return disk;
    }

    @Override
    public void show() {


        // player2.setPosition(width/2, (height/4)*3);


        Gdx.input.setInputProcessor(new InputHandler(this));

    }

    public World getWorld() {
        return world;
    }

    public Sprite getPista() {
        return pista;
    }


    @Override
    public void render(float delta) {

        world.step(Gdx.graphics.getDeltaTime(), 100, 100);

        //  disk.getBody().setLinearVelocity(0, 0);
//        d.getBody().applyForce(-800f,100f,0,0,true);


        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        camera2.update();
        batch.setProjectionMatrix(camera2.combined);
        shapeRenderer.setProjectionMatrix(camera.combined);

        force += 100f;
        module = Math.sqrt(force * force + 100f * 100f);
        //  disk.getBody().applyForceToCenter((float) (module * Math.cos(angle)), (float) (module * Math.sin(angle)), true);
        angle = 0;
//
        stage.act(delta);
        stage.draw();


        debugRenderer.render(world, camera.combined);
        /**
         System.out.println("Posicion del disco: " + disk.getBody().getPosition().x + ", " + disk.getBody().getPosition().y);
         System.out.println("Posición de la tejado: " + bounds.getBody().getPosition().x + ", " + bounds.getBody().getPosition().y);
         //System.out.println("Posicion del suelo: " + boundsGround.getBody().getPosition().x + ", " + boundsGround.getBody().getPosition().y);
         System.out.printf("Posicion del jugador " + jugador1.getX() + " ," + jugador1.getY());
         **/
        createCollisionListener();
    }

    private void createCollisionListener() {
        world.setContactListener(new ContactListener() {

            @Override
            public void beginContact(Contact contact) {
                Fixture fixtureA = contact.getFixtureA();
                Fixture fixtureB = contact.getFixtureB();




                if (fixtureA == jugador1.getFixture() && fixtureB == disk.getFixture()) {
                    System.out.println("ENTRO AL IF");
                    Vector2 aux = jugador1.getBody().getLinearVelocity();
                    jugador1.getBody().setLinearVelocity(0, 0);
                    System.out.println(aux);
                    disk.getBody().setLinearVelocity(new Vector2(1, 1));


                  //  disk.getBody().applyForceToCenter((float) (module * Math.cos(angle)), (float) (module * Math.sin(angle)), true);



                }



            }


            @Override
            public void endContact(Contact contact) {
                // TODO Auto-generated method stub

            }

            @Override
            public void preSolve(Contact contact, Manifold oldManifold) {
                // TODO Auto-generated method stub

            }

            @Override
            public void postSolve(Contact contact, ContactImpulse impulse) {
                // TODO Auto-generated method stub

            }

        });
    }

    // System.out.println(stage.getActors().size);


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