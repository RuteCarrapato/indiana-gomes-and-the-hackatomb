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

    final Indiana game;
    OrthographicCamera camera;
    private Texture splashScreen;

    public WinScreen(Indiana game, Texture splashScreen) {

        this.game = game;
        this.splashScreen = splashScreen;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, Constants.VIEW_WIDTH, Constants.VIEW_HEIGHT);

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();

        //splashScreen = new Texture(" RUBEN, FAZ AQUI UMA TEXTURA, BRO");
        game.font.draw(game.batch, "You won!!! ", 100, 150);
        game.font.draw(game.batch, "Press any key to play again!", 100, 100);

        game.batch.end();

        if (Gdx.input.isTouched() || Gdx.input.isKeyPressed(Input.Keys.ANY_KEY)) {

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

    }
}