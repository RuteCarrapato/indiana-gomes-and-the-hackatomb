package org.academiadecodigo.hackathon.gameobjects;


import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import org.academiadecodigo.hackathon.screens.PlayScreen;

/**
 * Created by codecadet on 16/03/17.
 */
public class GameObject extends Sprite {

    public World world;
    private PlayScreen screen;
    public Body b2dbody;
    public Vector2 velocity;

    public GameObject(PlayScreen screen) {

        this.screen = screen;
        this.world = screen.getWorld();
    }

    public GameObject(PlayScreen screen, float x, float y) {

        this.screen = screen;
        world = screen.getWorld();
    }

    public void defineGameObj() {

    }
}
