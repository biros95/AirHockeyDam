package com.mygdx.game.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.ChainShape;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactFilter;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.screens.PlayScreen;

public class Bounds implements ContactFilter, ContactListener {

    private Body body;
    private Fixture fixture;
    private float width;
    private float height;

    public Bounds(World world) {

        BodyDef bodyDef = new BodyDef();
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.density = 1;
        width = Gdx.graphics.getWidth();
     //   height = Gdx.graphics.getHeight();
    //    height = height ;


        float topPos = width+60;

        // body definition
        bodyDef.type = BodyType.StaticBody;
        bodyDef.position.set(-100, -900);


        // ground shape
        ChainShape groundShapeTop = new ChainShape();
        ChainShape lados = new ChainShape();


		/*
        groundShape.createChain(new Vector2[] {new Vector2(-10, groundPos), new Vector2(10,groundPos),
				new Vector2(10, 8.35f), new Vector2(-10,8.35f), new Vector2(-10,groundPos)});
		*/

       groundShapeTop.createChain(new Vector2[]{new Vector2(topPos, 4000), new Vector2(topPos, 0)});
        //groundShapeTop.createChain(new Vector2[]{new Vector2(100, 4000), new Vector2(100, 0)});

        // fixture definition
        fixtureDef.shape = groundShapeTop;

        body = world.createBody(bodyDef);
        fixture = body.createFixture(fixtureDef);

        bodyDef.position.set(-width, -900);
        fixtureDef.shape = groundShapeTop;

        body = world.createBody(bodyDef);
        fixture = body.createFixture(fixtureDef);


        //Crear los lados
        //Lado izquierdo  Bajo
        bodyDef.position.set(-100, 950);
        lados.createChain(new Vector2[]{new Vector2(0, 950), new Vector2(450, 950)});

        fixtureDef.shape = lados;

        body = world.createBody(bodyDef);
        fixture = body.createFixture(fixtureDef);

        //Izquierdo arriba
        bodyDef.position.set(-100, 950);
        lados.createChain(new Vector2[]{new Vector2(1400, 950), new Vector2(830, 950)});

        fixtureDef.shape = lados;
        body = world.createBody(bodyDef);
        fixture = body.createFixture(fixtureDef);


        //Crear los lados
        //Lado Derecho  Bajo
        bodyDef.position.set(-100, 950);
        lados.createChain(new Vector2[]{new Vector2(0, -920), new Vector2(450, -920)});

        fixtureDef.shape = lados;

        body = world.createBody(bodyDef);
        fixture = body.createFixture(fixtureDef);

        //Derecho arriba
        bodyDef.position.set(-100, 950);
        lados.createChain(new Vector2[]{new Vector2(1400, -920), new Vector2(830, -920)});

        fixtureDef.shape = lados;

        body = world.createBody(bodyDef);
        fixture = body.createFixture(fixtureDef);


        groundShapeTop.dispose();
    }



    public Body getBody() {
        return body;
    }

    public void setBody(Body body) {
        this.body = body;
    }

    @Override
    public void beginContact(Contact contact) {
        // TODO Auto-generated method stub

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

    @Override
    public boolean shouldCollide(Fixture fixtureA, Fixture fixtureB) {
        // TODO Auto-generated method stub
        return false;
    }

    public Fixture getFixture() {
        return fixture;
    }

    public float getHeight() {
        return height;
    }
}
