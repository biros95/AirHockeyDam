package com.mygdx.game.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
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
    private float posX, posY;


    public Player(Sprite sprite, String name) {
        this.sprite = sprite;
        this.name = name;
        this.posX = sprite.getX();
        this.posY = sprite.getY();
        setBounds(sprite.getX(), sprite.getY(), sprite.getWidth(), sprite.getHeight());
        setTouchable(Touchable.enabled);
        setSize(10f, 10f);

        addListener(new InputListener(){
            @Override
            public boolean keyDown(InputEvent event, int keycode) {
                if(keycode==Input.Keys.RIGHT){
                    MoveByAction mba = new MoveByAction();
                    mba.setAmount(100f, 0f);
                    mba.setDuration(5f);

                    Player.this.addAction(mba);
                }

                return true;
            }

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("Has tocado el actor!");
                MoveByAction mba = new MoveByAction();
                mba.setAmount(100f, 0f);
                mba.setDuration(5f);
                Player.this.addAction(mba);


                return true;

            }


            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                super.touchUp(event, x, y, pointer, button);
            }
        });


    }
    @Override
    protected void positionChanged() {
        super.setPosition(getX(), getY());
        super.positionChanged();
    }

    @Override
    public void draw(Batch batch, float parentAlpha){

        Color color = getColor();
        batch.setColor(color.r, color.g, color.b, color.a * parentAlpha);

        batch.draw(sprite, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }


}
