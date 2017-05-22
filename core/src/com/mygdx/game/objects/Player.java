package com.mygdx.game.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Circle;
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

    public Player(Sprite sprite, String name) {
        this.sprite = sprite;
        this.name = name;
        circle = new Circle();
        setX(0);
        setY(0);
        setBounds(getX(), getY(), sprite.getWidth(), sprite.getHeight());
        circle.set(getX() + getWidth() / 2.0f, getY() + getWidth() / 2.0f, getWidth() / 2.0f);
        setTouchable(Touchable.enabled);

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
}
