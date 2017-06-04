package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Matrix4;
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
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.mygdx.game.AirHockey;
import com.mygdx.game.helpers.MyAssetManager;
import com.mygdx.game.objects.BoundWall;
import com.mygdx.game.objects.Disk;
import com.mygdx.game.objects.Pista;
import com.mygdx.game.objects.Player2;


/**
 * Created by MarcosPortatil on 18/04/2017.
 */

public class PlayScreen extends InputAdapter implements Screen {
    //Creamos el campo y los dos jugadores.
    Sprite pista, player, player2, disco;
    Player2 jugador1, jugador2;
    Disk disk;
    SpriteBatch spriteFont;
    private Stage stage;
    Pista pistaHockey;
    boolean pause = false;
    AirHockey game;

    SpriteBatch fontBatch;


    Matrix4 mx4Font;
    Vector2 maxVelocity;

    //Creamos los mouseJoints para
    private MouseJointDef mouseJointDefPlayer1;
    private MouseJointDef mouseJointDefPlayer2;
    private Vector3 touchPositionPlayer1 = new Vector3();
    private Vector3 touchPositionPlayer2 = new Vector3();
    private MouseJoint jointJugador1, jointJugador2;


    private Box2DDebugRenderer debugRenderer;
    private final float TIMESTEP = 1f / 60f;
    private final float MAXVELOCITY = 50f;
    //Box2D

    public World world;

    SpriteBatch batch, batch2, batch3;

    BoundWall boundWall;
    private int substeps = 3;

    private double angle;
    MyAssetManager myAssetManager;
    StretchViewport viewport;

    OrthographicCamera camera, camera2;
    ShapeRenderer shapeRenderer;
    private final int VELOCITYITERATIONS = 20, POSITIONITERATIONS = 3;
    int pointerPlayer1, pointerPlayer2, pointerActual;
    int puntuacionJugador1 = 0;
    int puntuacionJugador2 = 0;
    Matrix4 oldTransformMatrix;

    /**
     * Constructor de la playScren
     * @param game
     */
    public PlayScreen(AirHockey game) {
        //Variable para comprobar si el juego está pausado
        pause = false;
        myAssetManager = new MyAssetManager();
        this.game = game;

        //debugRenderer para ver las siluetas de los cuerpos y paredes


        debugRenderer = new Box2DDebugRenderer();

        //Establecemos la máxima velocidad del disco
        maxVelocity = new Vector2(100, 100);

        //Creamos la variables de pantalla para reescalar todos los objetos
        float screenWidth = 400;
        float screenHeight = 600;
        float gameWidth = 203;
        float gameHeight = screenHeight / (screenWidth / gameWidth);


        //Camara uno se utilizará para Box2d por eso lo dividimos entre 10 ya que World utiliza otra medida diferente al pixel
        camera = new OrthographicCamera(gameWidth / 10, gameHeight / 10);

        //Creamos la camara2 que será la que tiene la pista
        camera2 = new OrthographicCamera(gameWidth, gameHeight);


        this.world = new World(new Vector2(0, 0), true);

        //Imprimimos las paredes
        boundWall = new BoundWall(world);

        shapeRenderer = new ShapeRenderer();

        batch = new SpriteBatch();


        batch2 = new SpriteBatch();


        viewport = new StretchViewport(screenHeight, screenWidth, camera2);
        stage = new Stage(viewport, batch);


        //Creación de Sprites
        pista = myAssetManager.cargarTextura("pista");
        disco = myAssetManager.cargarTextura("pelota");
        player = myAssetManager.cargarTextura("player");
        player2 = myAssetManager.cargarTextura("player");

        pista.setSize(screenHeight, screenWidth);

        disco.setSize(screenHeight / 8, screenHeight / 8);
        player.setSize(screenHeight / 5, screenHeight / 5);
        player2.setSize(screenHeight / 5, screenHeight / 5);


        //Creacion de objetos
        jugador1 = new Player2(world);
        jugador2 = new Player2(world);
        disk = new Disk(0, 0, disco, pista.getHeight() / 2, pista.getWidth() / 2, world);
        pistaHockey = new Pista(pista, "pista", world);
        pistaHockey.setPosition(0, 0);

        //Creamos un objeto Matrix4 para poder crear un texto invertido
        mx4Font = new Matrix4();
        oldTransformMatrix = new Matrix4();


        //Añadimos pista al stage
        stage.addActor(pistaHockey);


        angle = 0;
        iniciarJuego();


    }

    public Disk getDisk() {
        return disk;
    }

    @Override
    public void show() {



        // player2.setPosition(width/2, (height/4)*3);
        mouseJointDefPlayer1 = createMouseJointDefinition(boundWall.getBody());
        mouseJointDefPlayer2 = createMouseJointDefinition(boundWall.getBody());
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

        //PAUSA
        if (!pause) {

            world.step(1 / 60f, 8, 1);
        } else {
            world.step(0, VELOCITYITERATIONS, POSITIONITERATIONS);
        }

        //Actualizamos camaras y batch
        camera.update();
        camera2.update();
        batch.setProjectionMatrix(camera2.combined);
        shapeRenderer.setProjectionMatrix(camera.combined);
        batch2.setProjectionMatrix(camera.combined);

        //Imprimimos el stage que contiene pista
        stage.act(delta);
        stage.draw();

        //Imprimimos el marcador y lo actualizamos
        imprimirMarcador();



        limitarPosicionJugador();




        if (pause) {
            batch.begin();
            MyAssetManager.font.setColor(Color.BLUE);
            MyAssetManager.font.draw(batch, "PAUSE", 210, 210);
            batch.end();
        }
        // debugRenderer.render(world, camera2.combined);

       // disco.setX(disk.getBody().getPosition().x);
        //disco.setY(disk.getBody().getPosition().y);

        regularVelocidad();
        imprimirImagenes();


        comprobarGol(disk.getBody());


        createCollisionListener();


    }

    /**
     * Controlamos la posicion de los jugadores para que no puedan entrar en la porteria ni pasar al campo contrario
     */
    private void limitarPosicionJugador() {


        if (jugador1.getBody().getPosition().y < 2.2f) {
            jugador1.getBody().setTransform(jugador1.getBody().getPosition().x, 2.2f, 0);
        }

        if (jugador1.getBody().getPosition().x - jugador1.RADIUS < -9) {
            jugador1.getBody().setTransform(-9f + jugador1.RADIUS, jugador1.getBody().getPosition().y, 0);
        }

        if (jugador1.getBody().getPosition().x + jugador1.RADIUS > 9) {
            jugador1.getBody().setTransform(9f - jugador1.RADIUS, jugador1.getBody().getPosition().y, 0);
        }

        if (jugador1.getBody().getPosition().y + jugador1.RADIUS > 14) {
            jugador1.getBody().setTransform(jugador1.getBody().getPosition().x, 14f - jugador1.RADIUS, 0);
        }


        if (jugador2.getBody().getPosition().y > -2.2f) {
            jugador2.getBody().setTransform(jugador2.getBody().getPosition().x, -2.2f, 0);
        }

        if (jugador2.getBody().getPosition().x - jugador2.RADIUS < -9) {
            jugador2.getBody().setTransform(-9f + jugador2.RADIUS, jugador2.getBody().getPosition().y, 0);
        }

        if (jugador2.getBody().getPosition().x + jugador2.RADIUS > 9) {
            jugador2.getBody().setTransform(9f - jugador2.RADIUS, jugador2.getBody().getPosition().y, 0);
        }

        if (jugador2.getBody().getPosition().y - jugador2.RADIUS < -14) {
            jugador2.getBody().setTransform(jugador2.getBody().getPosition().x, -14f + jugador2.RADIUS, 0);
        }


    }

    /**
     * Metodo que regula la velocidad del disco si no supera la velocidad maxima la disminuye progresivamente
     */
    private void regularVelocidad() {

        Vector2 velocity = disk.getBody().getLinearVelocity();
        float speed = velocity.len();
        if (speed > MAXVELOCITY) {
            disk.getBody().setLinearVelocity(velocity.scl(MAXVELOCITY / speed));
        }


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


    /**
     *
     * Actualizamos la posicion de los jugadores al arrastrar con el dedo
     *
     * @param screenX
     * @param screenY
     * @param pointer
     * @param button
     * @return
     */

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        camera.unproject(tmp.set(screenX, screenY, 0));
        pointerActual = pointer;
        //Arrastramos los jugadores
        QueryCallback queryCallback = new QueryCallback() {

            @Override
            public boolean reportFixture(Fixture fixture) {
                if (fixture.testPoint(tmp.x,
                        tmp.y) && (fixture.getBody() == jugador1.getBody())) {
                    touchPositionPlayer1 = tmp;
                    pointerPlayer1 = pointerActual;
                    mouseJointDefPlayer1.bodyB = fixture.getBody();
                    mouseJointDefPlayer1.target.set(touchPositionPlayer1.x, touchPositionPlayer1.y);
                    mouseJointDefPlayer1.maxForce = 20000000.0f * fixture.getBody().getMass();
                    jointJugador1 = (MouseJoint) world.createJoint(mouseJointDefPlayer1);
                    return true;
                }

                if (fixture.testPoint(tmp.x,
                        tmp.y) && (fixture.getBody() == jugador2.getBody())) {
                    touchPositionPlayer2 = tmp;
                    pointerPlayer2 = pointerActual;
                    mouseJointDefPlayer2.bodyB = fixture.getBody();
                    mouseJointDefPlayer2.target.set(touchPositionPlayer2.x, touchPositionPlayer2.y);
                    mouseJointDefPlayer2.maxForce = 200000000.0f * fixture.getBody().getMass();
                    jointJugador2 = (MouseJoint) world.createJoint(mouseJointDefPlayer2);
                    return true;
                }

                return false;
            }
        };


        world.QueryAABB(queryCallback, tmp.x - 0.1f, tmp.y - 0.1f, tmp.x - 0.1f, tmp.y - 0.1f);

        if (this.isPaused()) {
            pause = false;
        }
        return true;

    }

    @Override


    public boolean touchDragged(int screenX, int screenY, int pointer) {

        /* Whether the input was processed */
        boolean processed = false;

        if (jointJugador1 != null && pointer == pointerPlayer1) {

			/* Translate camera point to world point */
            camera.unproject(touchPositionPlayer1.set(screenX, screenY, 0));
            System.out.println("jugador1 x" + jugador1.getBody().getPosition().x);
            System.out.println("jugador1 y" + jugador1.getBody().getPosition().y);

            if (touchPositionPlayer1.y > jugador1.getBody().getPosition().y + jugador1.RADIUS) {
                touchPositionPlayer1.y = jugador1.getBody().getPosition().y + jugador1.RADIUS;
            } else if (touchPositionPlayer1.y < jugador1.getBody().getPosition().y - jugador1.RADIUS) {
                touchPositionPlayer1.y = jugador1.getBody().getPosition().y - jugador1.RADIUS;

            }
/**
 else if (touchPositionPlayer1.x>jugador1.getBody().getPosition().x+jugador1.RADIUS) {
 touchPositionPlayer1.x = jugador1.getBody().getPosition().x+jugador1.RADIUS -0.2f;
 } else   if (touchPositionPlayer1.x<jugador1.getBody().getPosition().x-jugador1.RADIUS) {
 touchPositionPlayer1.x = jugador1.getBody().getPosition().x-jugador1.RADIUS +0.2f;

 }
 **/

            //  System.out.println("touch x: " + touchPositionPlayer1.x + " body x" + jugador1.getBody().getPosition().x);

            jointJugador1.setTarget(new Vector2(touchPositionPlayer1.x, touchPositionPlayer1.y));

            processed = true;
        }

        if (jointJugador2 != null && pointer == pointerPlayer2) {

			/* Translate camera point to world point */
            camera.unproject(touchPositionPlayer2.set(screenX, screenY, 0));
            System.out.println("jugador2 x" + jugador2.getBody().getPosition().x);
            System.out.println("jugador2 y" + jugador2.getBody().getPosition().y);
            if (touchPositionPlayer2.y > 1.5f || touchPositionPlayer2.y < -14.5
                    || touchPositionPlayer2.x < -9 || touchPositionPlayer2.x > 9) {

                world.destroyJoint(jointJugador2);
                jugador2.getBody().setLinearVelocity(0, 0);
                jointJugador2 = null;
            } else {
                jointJugador2.setTarget(new Vector2(touchPositionPlayer2.x, touchPositionPlayer2.y));
            }


            processed = true;
        }


        return processed;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {

        if (jointJugador1 != null && pointerPlayer1 == pointer) {
            world.destroyJoint(jointJugador1);
            jugador1.getBody().setLinearVelocity(0, 0);
            jointJugador1 = null;
        }
        if (jointJugador2 != null && pointerPlayer2 == pointer) {
            world.destroyJoint(jointJugador2);
            jugador2.getBody().setLinearVelocity(0, 0);
            jointJugador2 = null;
        }


        return true;
    }


    private MouseJointDef createMouseJointDefinition(Body body) {
        MouseJointDef jointDef;
        jointDef = new MouseJointDef();
        jointDef.bodyA = body;
        jointDef.collideConnected = true;
        jointDef.frequencyHz = 400;
        jointDef.dampingRatio = 1;


        return jointDef;
    }

    /**
     * Resetea las posiciones de los jugadores después de un gol y posiciona el disco.
     *
     * @param jugador
     */
    private void resetearPosiciones(boolean jugador) {
        //Destruimos los joints
        destroyJoints();

        //Asignamos la posicion de la pelota a 0
        disk.getBody().setLinearVelocity(0, 0);
        //Asignamos la posición a cada jugador
        jugador1.getBody().setTransform(0, 10, 0);
        jugador2.getBody().setTransform(0, -10, 0);

        //Comprobamos de quien ha sido el gol y posicionamos la pelota en su campo
        if (jugador) {
            disk.getBody().setTransform(0, 3, 0);
        } else {
            disk.getBody().setTransform(0, -3, 0);
        }
    }

    private void comprobarGol(Body disco) {
        if (disco.getPosition().y > 20) {
            resetearPosiciones(true);
            puntuacionJugador1++;

        }
        if (disco.getPosition().y < -20) {
            resetearPosiciones(false);

            puntuacionJugador2++;


        }

    }

    private void imprimirMarcador() {
        Matrix4 auxiliar = batch.getTransformMatrix();

        String puntuacion1, puntuacion2, total;
        puntuacion1 = String.valueOf(puntuacionJugador1);
        puntuacion2 = String.valueOf(puntuacionJugador2);

        if (puntuacionJugador1 < 10) {
            puntuacion1 = 0 + String.valueOf(puntuacionJugador1);
        }
        if (puntuacionJugador2 < 10) {
            puntuacion2 = 0 + String.valueOf(puntuacionJugador2);
        }

        total = puntuacion1 + " - " + puntuacion2;
        batch.begin();
        MyAssetManager.fuenteMarcador.setColor(Color.RED);
        MyAssetManager.fuenteMarcador.draw(batch, total, 450, 190);
        batch.end();


        fontBatch.setTransformMatrix(mx4Font);
        fontBatch.begin();
        MyAssetManager.fuenteMarcador.draw(fontBatch, total, 0, 0);
        fontBatch.end();
        fontBatch.setTransformMatrix(oldTransformMatrix);


    }

    //pausa
    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Input.Keys.BACK) {
            if (this.isPaused()) {
                game.setScreen(new MenuScreen(game));
            } else {
                this.pause();
            }
        }
        return false;
    }

    /**
     * iniciamos todas las posiciones de los jugadores, disco y actualizamos las puntuaciones de los jugadores
     */
    private void iniciarJuego() {


        puntuacionJugador1 = 0;
        puntuacionJugador2 = 0;


        disk.getBody().setTransform(0, 0, 0);
        jugador1.getBody().setTransform(0, 5, 0);
        jugador2.getBody().setTransform(0, -5, 0);


        fontBatch = new SpriteBatch();
        fontBatch.setProjectionMatrix(camera2.combined);


        oldTransformMatrix = fontBatch.getTransformMatrix().cpy();
        mx4Font.rotate(new Vector3(0, 0, 1), 180);
        mx4Font.trn(150, 210, 0);
    }

    /**
     * Metodo destruye el "MouseJoint" objeto para hacer drag sobre los jugadores
     */
    private synchronized void destroyJoints() {
        if (jointJugador1 != null) {
            world.destroyJoint(jointJugador1);
            jugador1.getBody().setLinearVelocity(0, 0);
            jointJugador1 = null;
        }
        if (jointJugador2 != null) {
            world.destroyJoint(jointJugador2);
            jugador2.getBody().setLinearVelocity(0, 0);
            jointJugador2 = null;
        }

    }

    /**
     * Metodo para mantener un margen entre el jugador y la pared.
     */
    public void separarJugadorPared() {

    }

    /**
     * Metodo que imprime el disco y ambos jugaodres con su Sprite
     */
    public void imprimirImagenes(){
        batch2.begin();
        //batch.draw(player, player.getX(), player.getY());
        disco.setPosition(disk.getBody().getPosition().x, disk.getBody().getPosition().y);
        batch2.draw(disco, disk.getBody().getPosition().x-disk.RADIUS, disk.getBody().getPosition().y-disk.RADIUS, disk.RADIUS*2,disk.RADIUS*2);
        batch2.draw(player, jugador1.getBody().getPosition().x-jugador1.RADIUS, jugador1.getBody().getPosition().
                y-jugador1.RADIUS, jugador1.RADIUS*2, jugador1.RADIUS*2);

        batch2.draw(player, jugador2.getBody().getPosition().x-jugador2.RADIUS, jugador2.getBody().getPosition().
                y-jugador2.RADIUS, jugador2.RADIUS*2, jugador2.RADIUS*2);
        batch2.end();
    }

}