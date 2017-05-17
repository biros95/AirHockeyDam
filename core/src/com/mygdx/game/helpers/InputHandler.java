package com.mygdx.game.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.objects.Player;
import com.mygdx.game.screens.BaseScreen;
import com.mygdx.game.screens.PlayScreen;

/**
 * Created by MarcosPortatil on 18/04/2017.
 */

public class InputHandler implements InputProcessor {
    int previousY = 0;


    private PlayScreen playScreen;
    private Vector2 stageCoord;
    private Player player;

    private Stage stage;
    public InputHandler(PlayScreen screen) {

        this.playScreen = screen;
        this.player = screen.getJugador1();
        this.stage = screen.getStage();

    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {

        previousY = screenY;

        stageCoord = stage.screenToStageCoordinates(new Vector2(screenX, screenY));
        Actor actorHit = stage.hit(stageCoord.x, stageCoord.y, true);








        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
