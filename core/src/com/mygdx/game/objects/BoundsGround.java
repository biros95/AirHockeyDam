package com.mygdx.game.objects;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.ChainShape;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactFilter;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;

public class BoundsGround implements ContactFilter, ContactListener {

    private Body body;
    private Fixture fixture;

    public BoundsGround(World world) {

        BodyDef bodyDef = new BodyDef();
        FixtureDef fixtureDef = new FixtureDef();
        float groundPos = -2.5f;

        // body definition
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set(0, groundPos);

        // ground shape
        ChainShape groundShapeBottom = new ChainShape();




        groundShapeBottom.createChain(new Vector2[]{new Vector2(-10, groundPos), new Vector2(10, groundPos)});


        // fixture definition
        fixtureDef.shape = groundShapeBottom;

        body = world.createBody(bodyDef);
        fixture = body.createFixture(fixtureDef);

        groundShapeBottom.dispose();

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

    public Body getBody() {
        return body;
    }

    public void setBody(Body body) {
        this.body = body;
    }
}