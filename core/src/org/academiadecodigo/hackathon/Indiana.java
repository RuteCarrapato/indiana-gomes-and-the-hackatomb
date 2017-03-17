package org.academiadecodigo.hackathon;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.academiadecodigo.hackathon.screens.MenuScreen;
import org.academiadecodigo.hackathon.screens.TestScreen;

public class Indiana extends Game {

    public SpriteBatch batch;
    public BitmapFont font;
    public static com.badlogic.gdx.assets.AssetManager manager;

    @Override
    public void create() {
        batch = new SpriteBatch();

        font = new BitmapFont();

        manager = new com.badlogic.gdx.assets.AssetManager();
        manager.load("audio/music/rick.mp3", Music.class);
        manager.load("audio/sounds/GUN.mp3", Sound.class);
        manager.finishLoading();

        setScreen(new MenuScreen(this));
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose() {
        batch.dispose();
        font.dispose();
    }
}
