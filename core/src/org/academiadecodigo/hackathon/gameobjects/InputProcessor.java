package org.academiadecodigo.hackathon.gameobjects;

import com.badlogic.gdx.Input;

/**
 * Created by codecadet on 17/03/17.
 */
public class InputProcessor implements com.badlogic.gdx.InputProcessor {

    public InputProcessor(Player player) {
        this.player = player;
    }

    Player player;

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {

        return !(keycode == Input.Keys.ANY_KEY);
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
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
}
