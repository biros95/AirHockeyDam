package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.QueryCallback;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.joints.MouseJoint;
import com.badlogic.gdx.physics.box2d.joints.MouseJointDef;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.utils.Array;

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
import com.mygdx.game.objects.Player2;
import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;


/**
 * Created by MarcosPortatil on 18/04/2017.
 */

public class PlayScreen extends InputAdapter implements Screen {
    //Creamos el campo y los dos jugadores.
    Sprite pista, player, player2, disco;
    Player2 jugador1, jugador2;
    Disk disk;
    private Stage stage;
    Pista pistaHockey;
    boolean pause = false;

    Vector2 maxVelocity;

    private Box2DDebugRenderer renderer;
    private Body ball, ground;

    private MouseJointDef mouseJointDefPlayer1;
    private MouseJointDef mouseJointDefPlayer2;
    private Vector3 touchPositionPlayer1 = new Vector3();
    private Vector3 touchPositionPlayer2 = new Vector3();
    private MouseJoint joint1, joint2;


    private Box2DDebugRenderer debugRenderer;
    private final float TIMESTEP = 1f / 60f;
    private final float MAXVELOCITY = 50f;
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
    StretchViewport viewport;
    private SpriteBatch spriteBatch;
    OrthographicCamera camera, camera2;
    boolean isBelowMaxVel;
    ShapeRenderer shapeRenderer;
    private final int VELOCITYITERATIONS = 20, POSITIONITERATIONS = 3;
    int pointerPlayer1, pointerPlayer2, pointerActual;
    boolean jugador1Touched = false, jugador2Touched = false;
    int puntuacionJugador1, puntuacionJugador2;


    public PlayScreen(AirHockey game) {
        //Obtenemos la altura y anchura de la pantalla para poder escalar la imagen.
        width = Gdx.graphics.getWidth();
        height = Gdx.graphics.getHeight();
        pause = false;
        System.out.println("With " + width + "\nHeight " + height);
        myAssetManager = new MyAssetManager();


        angle = 0;


        debugRenderer = new Box2DDebugRenderer();
        maxVelocity = new Vector2(100, 100);
        float screenWidth = 400;
        float screenHeight = 600;
        float gameWidth = 203;
        float gameHeight = screenHeight / (screenWidth / gameWidth);

        camera = new OrthographicCamera(gameWidth / 10, gameHeight / 10);
        camera2 = new OrthographicCamera(gameWidth, gameHeight);

        this.world = new World(new Vector2(0, 0), true);
        //bounds2 = new Bounds2(world);
        // boundsGround = new BoundsGround(world);
        shapeRenderer = new ShapeRenderer();

        batch = new SpriteBatch();
        batch2 = new SpriteBatch();
        viewport = new StretchViewport(screenHeight, screenWidth, camera2);
        stage = new Stage(viewport, batch);

        spriteBatch = new SpriteBatch();

        //Creación de Sprites
        pista = myAssetManager.cargarTextura("pista");
        disco = myAssetManager.cargarTextura("pelota");
        player = myAssetManager.cargarTextura("player");
        player2 = myAssetManager.cargarTextura("player");

        pista.setSize(screenHeight, screenWidth);

        disco.setSize(screenHeight / 8, screenHeight / 8);
        player.setSize(screenHeight / 5, screenHeight / 5);
        player2.setSize(screenHeight / 5, screenHeight / 5);


        //Creación de actores
        // jugador1 = new Player2(player, "Jugador 1", world);
        jugador1 = new Player2(world);
        jugador2 = new Player2(world);
        disk = new Disk(0, 0, disco, pista.getHeight() / 2, pista.getWidth() / 2, world);
        pistaHockey = new Pista(pista, "pista", world);
        pistaHockey.setPosition(0, 0);


        stage.addActor(pistaHockey);


        disk.getBody().setTransform(0, 0, 0);
        jugador1.getBody().setTransform(0, 5, 0);
        jugador2.getBody().setTransform(0, -5, 0);
    }

    public Disk getDisk() {
        return disk;
    }

    @Override
    public void show() {


        // player2.setPosition(width/2, (height/4)*3);
        mouseJointDefPlayer1 = createMouseJointDefinition(disk.getBody());
        mouseJointDefPlayer2 = createMouseJointDefinition(disk.getBody());
        Gdx.input.setInputProcessor(this);
        Gdx.input.setCatchBackKey(true);
        // mouse joint


    }

    public World getWorld() {
        return world;
    }

    public Sprite getPista() {
        return pista;
    }


    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0 / 255f, 0 / 255f, 0 / 255f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        Vector2 velocity = disk.getBody().getLinearVelocity();
        float speed = velocity.len();
        if (speed > MAXVELOCITY) {
            disk.getBody().setLinearVelocity(velocity.scl(MAXVELOCITY / speed));
        }
        camera.update();
        camera2.update();
        batch.setProjectionMatrix(camera2.combined);
        shapeRenderer.setProjectionMatrix(camera.combined);


        stage.act(delta);
        stage.draw();


        shapeRenderer.begin(ShapeType.Filled);
        // Dibujar pelota
        shapeRenderer.setColor(Color.GREEN);

        shapeRenderer.circle(disk.getBody().getPosition().x, disk.getBody().getPosition().y, disk.RADIUS, 32);

        shapeRenderer.setColor(Color.RED);
        if (jugador1.getBody().getPosition().y < 2.2f) {
            jugador1.getBody().setTransform(jugador1.getBody().getPosition().x, 2.2f, 0);
        }

        shapeRenderer.circle(jugador1.getBody().getPosition().x, jugador1.getBody().getPosition().y, jugador1.RADIUS, 32);
        shapeRenderer.setColor(Color.BLUE);
        if (jugador2.getBody().getPosition().y > -2.2f) {
            System.out.println(jugador2.getBody().getPosition().y);
            jugador2.getBody().setTransform(jugador2.getBody().getPosition().x, -2.2f, 0);
        }
        shapeRenderer.circle(jugador2.getBody().getPosition().x, jugador2.getBody().getPosition().y, jugador2.RADIUS, 32);


        shapeRenderer.end();
        System.out.println("PASUA: " + pause);
        if(pause) {
            batch.begin();
            MyAssetManager.font.setColor(Color.BLUE);
            MyAssetManager.font.draw(batch,"PAUSE",210,210);
            batch.end();
        }
        // debugRenderer.render(world, camera2.combined);
        debugRenderer.render(world, camera.combined);
        //PAUSA
        if(!pause) {
            world.step(TIMESTEP, VELOCITYITERATIONS, POSITIONITERATIONS);
        } else {
            world.step(0, VELOCITYITERATIONS, POSITIONITERATIONS);
            }
        /**
         System.out.println("Posicion del disco: " + disk.getBody().getPosition().x + ", " + disk.getBody().getPosition().y);
         System.out.println("Posición de la tejado: " + bounds.getBody().getPosition().x + ", " + bounds.getBody().getPosition().y);
         //System.out.println("Posicion del suelo: " + boundsGround.getBody().getPosition().x + ", " + boundsGround.getBody().getPosition().y);
         System.out.printf("Posicion del jugador " + jugador1.getX() + " ," + jugador1.getY());
         **/
      comprobarGol(disk.getBody());

        createCollisionListener();
    }

    private void createCollisionListener() {
        world.setContactListener(new ContactListener() {

            @Override
            public void beginContact(Contact contact) {
                Fixture fixtureA = contact.getFixtureA();
                Fixture fixtureB = contact.getFixtureB();


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
        pause = true;
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
        world.dispose();
        shapeRenderer.dispose();
        debugRenderer.dispose();

    }

    public Player2 getJugador1() {
        return jugador1;
    }

    public Stage getStage() {
        return stage;
    }

    public OrthographicCamera getCamera() {
        return camera;
    }

    public void setCamera(OrthographicCamera camera) {
        this.camera = camera;
    }

    public boolean isPaused() {
        return pause;
    }

    private Vector3 tmp = new Vector3();
    private Vector2 tmp2 = new Vector2();

    private QueryCallback queryCallback = new QueryCallback() {

        @Override
        public boolean reportFixture(Fixture fixture) {
            if (fixture.testPoint(tmp.x,
                    tmp.y) && (fixture.getBody() == jugador1.getBody())) {
                touchPositionPlayer1 = tmp;
                pointerPlayer1 = pointerActual;
                pointerPlayer1 = pointerActual;
                mouseJointDefPlayer1.bodyB = fixture.getBody();
                mouseJointDefPlayer1.target.set(touchPositionPlayer1.x, touchPositionPlayer1.y);
                mouseJointDefPlayer1.maxForce = 200000.0f * fixture.getBody().getMass();
                joint1 = (MouseJoint) world.createJoint(mouseJointDefPlayer1);
                return true;
            }

            if (fixture.testPoint(tmp.x,
                    tmp.y) && (fixture.getBody() == jugador2.getBody())) {
                touchPositionPlayer2 = tmp;
                pointerPlayer2 = pointerActual;
                mouseJointDefPlayer2.bodyB = fixture.getBody();
                mouseJointDefPlayer2.target.set(touchPositionPlayer2.x, touchPositionPlayer2.y);
                mouseJointDefPlayer2.maxForce = 200000.0f * fixture.getBody().getMass();
                joint2 = (MouseJoint) world.createJoint(mouseJointDefPlayer2);
                return true;
            }

            return false;
        }
    };

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        camera.unproject(tmp.set(screenX, screenY, 0));
        pointerActual = pointer;
        world.QueryAABB(queryCallback, tmp.x, tmp.y, tmp.x, tmp.y);
        if (this.isPaused()) {
            pause = false;
        }
        return true;

    }

    @Override


    public boolean touchDragged(int screenX, int screenY, int pointer) {

        /* Whether the input was processed */
        boolean processed = false;

        if (joint1 != null && pointer == pointerPlayer1) {

			/* Translate camera point to world point */
            camera.unproject(touchPositionPlayer1.set(screenX, screenY, 0));
            joint1.setTarget(new Vector2(touchPositionPlayer1.x, touchPositionPlayer1.y));

            processed = true;
        }

        if (joint2 != null && pointer == pointerPlayer2) {

			/* Translate camera point to world point */
            camera.unproject(touchPositionPlayer2.set(screenX, screenY, 0));
            joint2.setTarget(new Vector2(touchPositionPlayer2.x, touchPositionPlayer2.y));

            processed = true;
        }


        return processed;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {

        if (joint1 != null && pointerPlayer1 == pointer) {
            world.destroyJoint(joint1);
            jugador1.getBody().setLinearVelocity(0, 0);
            joint1 = null;
        }
        if (joint2 != null && pointerPlayer2 == pointer) {
            world.destroyJoint(joint2);
            jugador2.getBody().setLinearVelocity(0, 0);
            joint2 = null;
        }


        System.out.println("X= " + screenX + " Y=" + screenY);
        System.out.println(pointer);

        return true;
    }


    private MouseJointDef createMouseJointDefinition(Body body) {
        MouseJointDef jointDef;
        jointDef = new MouseJointDef();
        jointDef.bodyA = body;
        jointDef.collideConnected = true;
        jointDef.frequencyHz = 100;
        jointDef.dampingRatio = 0.0f;

        return jointDef;
    }
    private void resetearPosiciones(boolean jugador){
        disk.getBody().setLinearVelocity(0,0);


        jugador1.getBody().setTransform(0, 10,0);
        jugador2.getBody().setTransform(0, -10,0);
        if (jugador){
            disk.getBody().setTransform(0,3,0);
        }
        else{
            disk.getBody().setTransform(0,-3,0);
        }
    }
    public void comprobarGol(Body disco){
        if (disco.getPosition().y >20){
            resetearPosiciones(true);
            System.out.println("Gol jugador 2");
        }
        if (disco.getPosition().y<-20){
            resetearPosiciones(false);
            System.out.println("Gol jugador 1");

        }
    }

    //pausa
    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Input.Keys.BACK) {
            if (this.isPaused()) {
//                game.setScreen(new MenuScreen(game));
            } else {
                this.pause();
            }
        }
        return false;
    }
}