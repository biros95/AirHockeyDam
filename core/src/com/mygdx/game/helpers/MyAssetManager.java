package com.mygdx.game.helpers;


import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

/**
 * Created by MarcosPortatil on 18/04/2017.
 */

public class MyAssetManager  {

    AssetManager manager;
    TextureAtlas textureAtlas;
     public MyAssetManager() {
         manager = new AssetManager();
         textureAtlas = new TextureAtlas("textures.txt");
     }

     public void load(){

         manager.load("logo.png", Texture.class);
          manager.load("floor.png", Texture.class);
          manager.load("gameover.png", Texture.class);
          manager.load("overfloor.png", Texture.class);

          manager.load("spike.png", Texture.class);
          manager.load("player.png", Texture.class);
          manager.load("audio/die.ogg", Sound.class);
          manager.load("audio/jump.ogg", Sound.class);
          manager.load("audio/song.ogg", Music.class);
          manager.load("1Player.png",Texture.class);
         manager.load("2Player.png",Texture.class);
         manager.load("LogoPequeño.png",Texture.class);
         manager.load("Options.png",Texture.class);
         manager.load("button.png",Texture.class);
         manager.load("Options2.png",Texture.class);
         manager.load("PUCK.png",Texture.class);
         manager.load("MALLET.png",Texture.class);
         manager.load("Atras.jpg",Texture.class);
         manager.load("MALLETS.png",Texture.class);
         manager.load("MUSIC.png",Texture.class);
         manager.load("OFF.png",Texture.class);
         manager.load("ON.png",Texture.class);
         manager.load("Options2.png",Texture.class);
         manager.load("puck2.png",Texture.class);
     }

     public Sprite cargarTextura(String textura){
         return textureAtlas.createSprite(textura);
     }

    public TextureAtlas getTextureAtlas() {
        return textureAtlas;
    }

    public AssetManager getManager() {
          return manager;
     }
}
