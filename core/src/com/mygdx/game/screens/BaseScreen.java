package com.mygdx.game.screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.AirHockey;

/**
 * Created by MarcosPortatil on 18/04/2017.
 */

public abstract class BaseScreen implements Screen {


    protected AirHockey game;

    BaseScreen(AirHockey game) {
        this.game = game;
    }


}
