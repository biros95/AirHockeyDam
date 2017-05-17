package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.mygdx.game.AirHockey;
import com.badlogic.gdx.assets.AssetManager;
import com.mygdx.game.helpers.MyAssetManager;

/**
 * Created by MarcosPortatil on 18/04/2017.
 */

public class MenuScreen extends BaseScreen {

    private Stage stage;

    /**
     * The skin that we use to set the style of the buttons.
     */
    private Skin skin;

    /**
     * The logo image you see on top of the screen.
     */
    private Image logo;

    /**
     * The play button you use to jump to the game screen.
     */
    private TextButton play, credits;
    private int height;
    private int width;

    public MenuScreen(final AirHockey game) {

        super(game);

        height = 180;
        width = 320;

        stage = new Stage(new FitViewport(height, width));

        // Load the skin file. The skin file contains information about the skins. It can be
        // passed to any widget in Scene2D UI to set the style. It just works, amazing.
        skin = new Skin(Gdx.files.internal("skin/uiskin.json"));
        // For instance, here you see that I create a new button by telling the label of the
        // button as well as the skin file. The background image for the button is in the skin
        // file.
        play = new TextButton("Jugar", skin);
        credits = new TextButton("Opciones", skin);

        // Also, create an image. Images are actors that only display some texture. Useful if you
        // want to display a texture in a Scene2D based screen but you don't want to rewrite code.
        logo = new Image(game.getManager().get("logo.png", Texture.class));

        // Add capture listeners. Capture listeners have one method, changed, that is executed
        // when the button is pressed or when the user interacts somehow with the widget. They are
        // cool because they let you execute some code when you press them.
        play.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                // Take me to the game screen!
                game.setScreen(game.gameScreen);
            }
        });
        play.addCaptureListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(game.gameScreen);
            }
        });

        credits.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(game.creditsScreen);
            }


        });


        logo.setSize(100, 40);
        play.setSize(80, 30);
        credits.setSize(80, 30);

        logo.setPosition(40, stage.getHeight() - 40);
        play.setPosition(30, logo.getY() - 60);
        credits.setPosition(30, play.getY() - 60);


        stage.addActor(play);
        stage.addActor(logo);
        stage.addActor(credits);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void hide() {
        Gdx.input.setInputProcessor(null);
    }

    @Override
    public void dispose() {
        stage.dispose();
        skin.dispose();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.2f, 0.3f, 0.5f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();
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


}
