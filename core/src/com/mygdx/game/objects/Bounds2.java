
package com.mygdx.game.objects;
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

public class Bounds2 implements ContactFilter, ContactListener {

    private Body body;
    private Fixture fixture;

    public Bounds2(World world) {

        BodyDef bodyDef = new BodyDef();
        FixtureDef fixtureDef = new FixtureDef();
        float groundPos = -2.5f;
        float topPos =7.5f;
        float leftWall = -5f;
        float rightWall =15f;

        // body definition
        bodyDef.type = BodyType.StaticBody;
        bodyDef.position.set(0, groundPos);

        // ground shape
        ChainShape groundShapeBottom = new ChainShape();
        ChainShape groundShapeTop = new ChainShape();
        ChainShape groundShareRight = new ChainShape();
        ChainShape groundShareLeft = new ChainShape();

		/*
		groundShape.createChain(new Vector2[] {new Vector2(-10, groundPos), new Vector2(10,groundPos),
				new Vector2(10, 8.35f), new Vector2(-10,8.35f), new Vector2(-10,groundPos)});
		*/

        groundShapeBottom.createChain(new Vector2[] {new Vector2(-10, groundPos), new Vector2(10,groundPos)});
        groundShapeTop.createChain(new Vector2[] {new Vector2(-10, topPos), new Vector2(10,topPos)});

        groundShareRight.createChain(new Vector2[] {new Vector2(rightWall, -10), new Vector2(rightWall,10)});
        groundShareLeft.createChain(new Vector2[] {new Vector2(leftWall, -10), new Vector2(leftWall,10)});

        // fixture definition
        fixtureDef.shape = groundShapeBottom;

        body = world.createBody(bodyDef);
        fixture = body.createFixture(fixtureDef);

        // fixture definition
        fixtureDef.shape = groundShapeTop;

        body = world.createBody(bodyDef);
        fixture = body.createFixture(fixtureDef);


        bodyDef.position.set(leftWall,0 );
        // fixture definition

        fixtureDef.shape = groundShareLeft;

        body = world.createBody(bodyDef);
        fixture = body.createFixture(fixtureDef);

        // fixture definition
        fixtureDef.shape = groundShareRight;

        body = world.createBody(bodyDef);
        fixture = body.createFixture(fixtureDef);


        groundShapeTop.dispose();
        groundShapeBottom.dispose();
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
}
