package org.academiadecodigo.hackathon.screens.persistencetest;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import org.academiadecodigo.hackathon.Indiana;
import org.academiadecodigo.hackathon.screens.AbstractGameScreen;
import org.academiadecodigo.hackathon.screens.persistencetest.userservice.JdbcUserService;

/**
 * Created by codecadet on 17/03/17.
 */
public class TestScreen extends AbstractGameScreen {

    private final Indiana game;
    private JdbcUserService userService;

    private Skin skin;
    private Stage stage;

    public String text;
    public TextField textUsername;
    public TextField textPassword;

    public TestScreen(final Indiana game) {

        this.userService = new JdbcUserService();
        this.game = game;
        this.stage = new Stage();

        stage.addListener(new InputListener() {
            @Override
            public boolean keyUp(InputEvent event, int keycode) {
                if(keycode == Input.Keys.ENTER) {
                    onLogin();
                }

                return false;
            }
        });

        Gdx.input.setInputProcessor(stage);


        TextField.TextFieldStyle font = new TextField.TextFieldStyle();
        font.fontColor = Color.WHITE;
        font.font = new BitmapFont();
        this.skin = new Skin(Gdx.files.internal("uiskin.json"));

        Table table = new Table(skin);
        table.center();
        table.setFillParent(true);
        table.center();

        textUsername = new TextField(text, skin);
        table.addActor(textUsername);
        textUsername.setText(text);
        textUsername.setSize(200, 60);
        textUsername.setPosition(250, 250);

        table.row();
        textPassword = new TextField(text, skin);
        table.addActor(textPassword);
        textPassword.setText(text);
        textUsername.setSize(200, 60);
        textPassword.setPosition(250, 500);

        table.row();
        stage.addActor(table);
    }

    /**
     *  TODO : Not yet implemented. By 10 minutes!
     */
    private void onLogin() {
        userService.authenticate(textUsername.getText(), textPassword.getText());
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(delta);
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

    @Override
    public void hide() {
    }

    @Override
    public void dispose() {
        game.dispose();
        stage.dispose();
    }
}

