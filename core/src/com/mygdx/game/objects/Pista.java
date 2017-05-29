package com.mygdx.game.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Created by MarcosPortatil on 16/05/2017.
 */

public class Pista extends Actor {

    private Sprite sprite;
    private String name;
    private float posX, posY;


    public Pista(Sprite sprite, String name) {
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