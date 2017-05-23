package com.mygdx.game.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.mygdx.game.objects.Disk;
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
    private Disk disk;
    double module;
    private Stage stage;

    public InputHandler(PlayScreen screen) {

        this.playScreen = screen;
        this.player = screen.getJugador1();
        this.stage = screen.getStage();
        this.disk = screen.getDisk();

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
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {


        stageCoord = stage.screenToStageCoordinates(new Vector2(screenX, screenY));


        /**
         * NO SE PUEDE QUEDAR ASÍ, ESE 40 DEBE CAMBIARSE
         *
         * se pone -40 porque el jugador va siempre un poco a la derecha y un poco arriba, hay que cambiarlo
         */
        player.setX(stageCoord.x - 40);
        player.setY(stageCoord.y - 40);


        return true;
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
