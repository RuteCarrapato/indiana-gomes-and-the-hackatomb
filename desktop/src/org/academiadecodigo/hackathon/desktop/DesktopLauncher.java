package org.academiadecodigo.hackathon.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import org.academiadecodigo.hackathon.Indiana;
import org.academiadecodigo.hackathon.utils.Constants;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		config.width = Constants.CONFIG_WIDTH;
		config.height = Constants.CONFIG_HEIGHT;
		config.title = "Indiana Gomes";

		new LwjglApplication(new Indiana(), config);
	}
}
