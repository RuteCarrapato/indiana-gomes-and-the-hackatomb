package org.academiadecodigo.hackathon.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import org.academiadecodigo.hackathon.Indiana;
import org.academiadecodigo.hackathon.utils.Constants;

/**
 * Created by codecadet on 3/17/17.
 */
public class WinScreen extends AbstractGameScreen {

    private final Indiana game;
    private OrthographicCamera camera;
    private Texture splashScreen;

    public WinScreen(Indiana game) {

        this.game = game;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, Constants.CONFIG_WIDTH, Constants.CONFIG_HEIGHT);

    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();

        splashScreen = new Texture("splashscreens/WinScreen.png");
        game.batch.draw(splashScreen,0,0,Constants.CONFIG_WIDTH,Constants.CONFIG_HEIGHT);
        game.batch.end();

        if (Gdx.input.isKeyPressed(Input.Keys.ENTER)) {
            game.setScreen(new PlayScreen(game));
        }
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

        game.dispose();
        splashScreen.dispose();
    }
}
