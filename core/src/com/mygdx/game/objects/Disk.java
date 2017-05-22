package com.mygdx.game.objects;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;

/**
 * Created by MarcosPortatil on 19/04/2017.
 */

public class Disk extends Actor {
    private float velocity;
    private float maxVelocity;
    private Sprite sprite;
    private float altura;
    private float anchura;
    private Circle circle;
    public Disk(float velocity, float maxVelocity, Sprite sprite, float altura, float anchura) {
        this.velocity = velocity;
        this.maxVelocity = maxVelocity;
        this.sprite = sprite;
        this.altura = altura;
        this.anchura = anchura;
        circle = new Circle();

        setBounds(anchura, altura, sprite.getWidth(), sprite.getHeight());
        circle.set(getX() + getWidth() / 2.0f, getY() + getWidth() / 2.0f, getWidth() / 2.0f);
        setTouchable(Touchable.enabled);
    }


    /**
     * Metodo que se encarga de dibujuar el actor,
     * se ejecuta cada vez que stage.draw() se ejecuta.
     *
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


    //GETTERS


    public Circle getCircle() {
        return circle;
    }
}
