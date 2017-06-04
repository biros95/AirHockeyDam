package com.mygdx.game.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
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
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Created by MarcosPortatil on 16/05/2017.
 */

public class Pista extends Actor  {

    private Sprite sprite;
    private String name;
    private float posX, posY;
    private Body body;
    private Fixture fixture;


    public Pista(Sprite sprite, String name, World world) {
        this.sprite = sprite;
        this.name = name;
        this.posX = sprite.getX();
        this.posY = sprite.getY();
        setBounds(sprite.getX(), sprite.getY(), sprite.getWidth(), sprite.getHeight());



    }

    @Override
    public void draw(Batch batch, float parentAlpha){

        Color color = getColor();
        batch.setColor(color.r, color.g, color.b, color.a * parentAlpha);

        batch.draw(sprite, 0, 0, sprite.getWidth(), sprite.getHeight());
    }


}