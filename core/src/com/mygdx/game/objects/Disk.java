package com.mygdx.game.objects;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactFilter;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;

/**
 * Created by MarcosPortatil on 19/04/2017.
 */

public class Disk extends Actor implements ContactFilter, ContactListener {
    //Box2D
    private Body body;
    private Fixture fixture;
    private World world;
    public final float RADIUS;
    public BodyDef bodyDef;
    //Libgdx

    private float velocity;
    private float maxVelocity;
    private Sprite sprite;
    private float altura;
    private float anchura;
    private Circle circle;

    public Disk(float velocity, float maxVelocity, Sprite sprite, float altura, float anchura, World world) {
        this.velocity = velocity;
        this.maxVelocity = maxVelocity;
        this.sprite = sprite;
        this.altura = altura;
        this.anchura = anchura;
        circle = new Circle();

        setBounds(anchura, altura, sprite.getWidth(), sprite.getHeight());
        circle.set(getX() + getWidth() / 2.0f, getY() + getWidth() / 2.0f, getWidth() / 2.0f);

        //Box 2d

        bodyDef = new BodyDef();
        FixtureDef fixtureDef = new FixtureDef();

        // body definition
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(getX(), getY());

        // ball shape
        CircleShape ballShape = new CircleShape();
        RADIUS = sprite.getHeight()/2;
        ballShape.setRadius(RADIUS);


        // fixture definition
        fixtureDef.shape = ballShape;
        fixtureDef.friction = 0;
        fixtureDef.restitution = 1;
        //fixtureDef.density = 0;



        body = world.createBody(bodyDef);
        fixture = body.createFixture(fixtureDef);


        ballShape.dispose();

    }


    /**
     * Metodo que se encarga de dibujuar el actor,
     * se ejecuta cada vez que stage.draw() se ejecuta.
     *
     * @param batch
     * @param parentAlpha
     */
  /**  @Override
    public void draw(Batch batch, float parentAlpha) {

        Color color = getColor();
        batch.setColor(color.r, color.g, color.b, color.a * parentAlpha);
        System.out.println( body.getLinearVelocity());
        batch.draw(sprite, body.getPosition().x-sprite.getHeight()/2,body.getPosition().y-sprite.getWidth()/2 , getWidth(), getHeight());

//        body.setTransform(getX(), getY(), 0);
    }

    @Override
    public void act(float delta) {
        setBounds(getX(), getY(), getWidth(), getHeight());
        circle.set(getX() + getWidth() / 2.0f, getY() + getWidth() / 2.0f, getWidth() / 2.0f);
    }
**/

    //GETTERS


    public Circle getCircle() {
        return circle;
    }

    @Override
    public boolean shouldCollide(Fixture fixtureA, Fixture fixtureB) {
        return false;
    }

    @Override
    public void beginContact(Contact contact) {

    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
    public Body getBody(){
        return body;
    }

    public Fixture getFixture(){
        return fixture;
    }

    public Sprite getSprite() {
        return sprite;
    }
}
