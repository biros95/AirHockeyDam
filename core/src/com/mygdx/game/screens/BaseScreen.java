package com.mygdx.game.screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.AirHockey;

/**
 * Created by MarcosPortatil on 18/04/2017.
 */

public class BaseScreen implements Screen {


    protected AirHockey game;

    public BaseScreen(AirHockey game) {
        this.game = game;
    }
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
