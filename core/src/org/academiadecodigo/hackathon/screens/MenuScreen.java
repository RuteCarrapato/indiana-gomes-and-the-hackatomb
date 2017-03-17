package org.academiadecodigo.hackathon.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import org.academiadecodigo.hackathon.Indiana;
import org.academiadecodigo.hackathon.utils.Constants;

/**
 * Created by codecadet on 16/03/17.
 */
public class MenuScreen extends AbstractGameScreen {

    private final Indiana game;
    private OrthographicCamera camera;
    private Texture splashScreen;

    public MenuScreen(final Indiana game) {

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

        splashScreen = new Texture("splashscreens/IndianaGomes.png");
        game.batch.draw(splashScreen,0,0,Constants.CONFIG_WIDTH,Constants.CONFIG_HEIGHT);

        game.batch.end();

        //Waiting for input of any key or touch
        if (Gdx.input.isTouched() || Gdx.input.isKeyPressed(Input.Keys.ENTER)) {
            game.setScreen(new PlayScreen(game));
        } else if (Gdx.input.isKeyPressed((Input.Keys.I))){
            game.setScreen(new InstructionsScreen(game));
        } else if (Gdx.input.isKeyPressed((Input.Keys.C))) {
            game.setScreen(new CreditsScreen(game));
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
