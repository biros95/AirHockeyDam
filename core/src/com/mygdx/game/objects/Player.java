package com.mygdx.game.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.MoveByAction;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

/**
 * Created by MarcosPortatil on 18/04/2017.
 */

public class Player extends Actor {
    private Sprite sprite;
    private String name;
    private Circle circle;

    //Box2d

    private Body body;
    private Fixture fixture;
    private World world;
    public final float RADIUS = .2f;

    public Player(Sprite sprite, String name, World world) {
        this.sprite = sprite;
        this.name = name;
        circle = new Circle();
        setX(0);
        setY(0);
        setBounds(getX(), getY(), sprite.getWidth(), sprite.getHeight());
        circle.set(getX() + getWidth() / 2.0f, getY() + getWidth() / 2.0f, getWidth() / 2.0f);
        setTouchable(Touchable.enabled);





        //Box2d

        BodyDef bodyDef = new BodyDef();
        FixtureDef fixtureDef = new FixtureDef();
        this.world = world;

        // body definition
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(50, 50);

        // ball shape
        CircleShape ballShape = new CircleShape();
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
     * @param batch
     * @param parentAlpha
     */
    @Override
    public void draw(Batch batch, float parentAlpha) {

        Color color = getColor();
        batch.setColor(color.r, color.g, color.b, color.a * parentAlpha);

        batch.draw(sprite, getX(), getY(), getWidth(), getHeight());


    }

    @Override
    public void act(float delta) {

        setBounds(getX(), getY(), getWidth(), getHeight());
        circle.set(getX() + getWidth() / 2.0f, getY() + getWidth() / 2.0f, getWidth() / 2.0f);
    }


    //GETTERS Y SETTERS


    public Circle getCircle() {
        return circle;
    }

    public Body getBody() {
        return body;
    }

    public void setBody(Body body) {
        this.body = body;
    }

    public Fixture getFixture() {
        return fixture;
    }

    public void setFixture(Fixture fixture) {
        this.fixture = fixture;
    }
}
