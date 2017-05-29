
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
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Bounds2 extends Actor implements ContactFilter, ContactListener {

    private Body body;
    private Fixture fixture;

    public Bounds2(World world) {

        BodyDef bodyDef = new BodyDef();
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.density = 1;


        float groundPos = -2.35f;
        float topGroundPos = -2.5f;
        float groundPos2 = -2.5f;
        float topPos =7.35f;
        float leftWall = -4.65f;
        float rightWall =14.10f;

        // body definition
        bodyDef.type = BodyType.StaticBody;
       bodyDef.position.set(0, topGroundPos);

        // ground shape
        ChainShape groundShareBottomLeft= new ChainShape();
        ChainShape groundShareBottomRight = new ChainShape();

        ChainShape groundShareTopRight = new ChainShape();
        ChainShape groundShareTopLeft = new ChainShape();

        ChainShape groundShareRight = new ChainShape();
        ChainShape groundShareLeft = new ChainShape();



        groundShareBottomLeft.createChain(new Vector2[] {new Vector2(-10, groundPos), new Vector2(-3.5f,groundPos)});
        groundShareTopLeft.createChain(new Vector2[] {new Vector2(-10,topPos), new Vector2(-3.5f,topPos)});

        groundShareBottomRight.createChain(new Vector2[] {new Vector2(3.5f, groundPos), new Vector2(10f,groundPos)});
        groundShareTopRight.createChain(new Vector2[] {new Vector2(3.5f,topPos), new Vector2(10f,topPos)});


        groundShareRight.createChain(new Vector2[] {new Vector2(rightWall, -10), new Vector2(rightWall,10)});
        groundShareLeft.createChain(new Vector2[] {new Vector2(leftWall, -10), new Vector2(leftWall,10)});

        // fixture definition
        fixtureDef.shape = groundShareBottomLeft;

        body = world.createBody(bodyDef);
        fixture = body.createFixture(fixtureDef);

        // fixture definition
        fixtureDef.shape = groundShareTopLeft;

        body = world.createBody(bodyDef);
        fixture = body.createFixture(fixtureDef);


        // fixture definition
        fixtureDef.shape = groundShareBottomRight;

        body = world.createBody(bodyDef);
        fixture = body.createFixture(fixtureDef);

        // fixture definition
        fixtureDef.shape = groundShareTopRight;

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


        groundShareTopLeft.dispose();
        groundShareBottomLeft.dispose();
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
