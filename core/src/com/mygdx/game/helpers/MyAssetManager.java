package com.mygdx.game.helpers;


import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;

/**
 * Created by MarcosPortatil on 18/04/2017.
 */

public class MyAssetManager {
     AssetManager manager = new AssetManager();

     public MyAssetManager() {

     }

     public void load(){


          manager.load("floor.png", Texture.class);
          manager.load("gameover.png", Texture.class);
          manager.load("overfloor.png", Texture.class);
          manager.load("logo.png", Texture.class);
          manager.load("spike.png", Texture.class);
          manager.load("player.png", Texture.class);
          manager.load("audio/die.ogg", Sound.class);
          manager.load("audio/jump.ogg", Sound.class);
          manager.load("audio/song.ogg", Music.class);
     }

     public AssetManager getManager() {
          return manager;
     }

}
