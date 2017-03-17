package org.academiadecodigo.hackathon.managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by tomazsaraiva on 21/10/15.
 */
public class AssetManager {

    public static final String TEXTURE_ATLAS_OBJECT = "images/uiskin.atlas";

    public static final AssetManager instance = new AssetManager();

    private AssetManager() {}

    public AtlasRegion spaceship;

    public void init () {

        // load texture atlas
        TextureAtlas atlas = new TextureAtlas(Gdx.files.internal(TEXTURE_ATLAS_OBJECT));

    }
}


