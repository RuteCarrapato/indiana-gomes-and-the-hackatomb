package org.academiadecodigo.hackathon.gameobjects;

import com.badlogic.gdx.physics.box2d.World;
import org.academiadecodigo.hackathon.gameobjects.GameObject;
import org.academiadecodigo.hackathon.screens.PlayScreen;

/**
 * Created by codecadet on 16/03/17.
 */
public class Projectile extends GameObject {
    boolean fireRight;
    boolean destroyed;
    boolean setToDestroy;

    public Projectile(PlayScreen screen, float x, float y, boolean fireRight) {
        super(screen);
        this.fireRight = fireRight;

    }

    public void update(float dt) {
    }

    public boolean isDestroyed() {
        return destroyed;
    }
}
