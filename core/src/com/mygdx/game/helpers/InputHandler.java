package com.mygdx.game.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.QueryCallback;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.joints.MouseJoint;
import com.badlogic.gdx.physics.box2d.joints.MouseJointDef;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.mygdx.game.AirHockey;
import com.mygdx.game.objects.Disk;
import com.mygdx.game.objects.Player;
import com.mygdx.game.objects.Player2;
import com.mygdx.game.screens.BaseScreen;
import com.mygdx.game.screens.MenuScreen;
import com.mygdx.game.screens.PlayScreen;

/**
 * Created by MarcosPortatil on 18/04/2017.
 */

public class InputHandler implements InputProcessor {
    int previousY = 0;


    private PlayScreen playScreen;

    private MouseJointDef jointDef;
    private MouseJoint joint;
    private Vector2 stageCoord;
    private Player2 player;
    private Disk disk;
    double module;
    private Stage stage;
    World world;
    AirHockey game;

    private Long timer;

    private Vector3 touchPositionPlayer1 = new Vector3();
    private int pointerPlayer1;
    private MouseJoint mouseJointPlayer1;
    private MouseJointDef mouseJointDefPlayer1;

    private Vector3 touchPositionPlayer2 = new Vector3();
    private int pointerPlayer2;
    private MouseJoint mouseJointPlayer2;
    private MouseJointDef mouseJointDefPlayer2;
    Camera camera;

    public InputHandler(PlayScreen screen) {

        this.playScreen = screen;
        this.player = screen.getJugador1();
        this.stage = screen.getStage();
        this.disk = screen.getDisk();
        world = playScreen.getWorld();
        camera = playScreen.getCamera();
//        game = playScreen.getGame();

        // mouse joint
        jointDef = new MouseJointDef();
        jointDef.bodyA = disk.getBody();
        jointDef.collideConnected = true;
        jointDef.maxForce = 500;

    }



    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Input.Keys.BACK) {
//            if (playScreen.isPaused()) {
//                game.setScreen(new MenuScreen(game));
//            } else {
//                playScreen.pauseGame();
//            }
        }
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
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }


    private Vector3 tmp = new Vector3();
    private Vector2 tmp2 = new Vector2();

    private QueryCallback queryCallback = new QueryCallback() {

        @Override
        public boolean reportFixture(Fixture fixture) {
            if (!fixture.testPoint(tmp.x, tmp.y))
                return true;

            jointDef.bodyB = fixture.getBody();
            jointDef.target.set(tmp.x, tmp.y);
            joint = (MouseJoint) world.createJoint(jointDef);
            return false;
        }

    };

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {

        camera.unproject(tmp.set(screenX, screenY, 0));
        world.QueryAABB(queryCallback, tmp.x, tmp.y, tmp.x, tmp.y);
//        if(playScreen.isPaused()){
//            playScreen.resumeGame();
//        }

        return true;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        if (joint == null)
            return false;

        camera.unproject(tmp.set(screenX, screenY, 0));
        joint.setTarget(tmp2.set(tmp.x, tmp.y));
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        if (joint == null)
            return false;

        world.destroyJoint(joint);
        joint = null;
        return true;
    }

}
