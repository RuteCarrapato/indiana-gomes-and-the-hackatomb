package org.academiadecodigo.hackathon.gameobjects;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import org.academiadecodigo.hackathon.screens.PlayScreen;

/**
 * Created by codecadet on 16/03/17.
 */
public class Enemy extends GameObject {

    public boolean died;

    public Enemy(PlayScreen screen, float x, float y) {
        super(screen, x, y);
        this.world = screen.getWorld();
        this.screen = screen;
//        this.b2dbody = new Body();
    }

    public void die() {
        died = true;
    }
}
