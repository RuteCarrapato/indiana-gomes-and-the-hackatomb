package org.academiadecodigo.hackathon;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.academiadecodigo.hackathon.screens.MenuScreen;
import org.academiadecodigo.hackathon.screens.PlayScreen;

public class Indiana extends Game {

	public SpriteBatch batch;
    public BitmapFont font;


	@Override
	public void create () {
		batch = new SpriteBatch();

		font = new BitmapFont();

		setScreen(new MenuScreen(this));
	}

	@Override
	public void render () {
    		super.render();

	}

	@Override
	public void dispose () {
		batch.dispose();
		font.dispose();
	}
}
