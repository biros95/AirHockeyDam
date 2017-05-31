package com.mygdx.game.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Created by MarcosPortatil on 18/04/2017.
 */

public class Player2 {
    Body body;

    float width = Gdx.graphics.getWidth();
    public final float RADIUS;

    public Player2(World world) {
        CircleShape ballShape = new CircleShape();
        RADIUS = 2f;
        ballShape.setRadius(RADIUS);

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;

        bodyDef.fixedRotation=true;
        body = world.createBody(bodyDef);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = ballShape;
        fixtureDef.friction = 0;
        fixtureDef.restitution = 1;

        fixtureDef.density = 500000;

        body.createFixture(fixtureDef);

        ballShape.dispose();

    }


    public Body getBody() {
        return body;
    }
}
