package org.academiadecodigo.hackathon.colisiondetector;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.*;

public class WorldContactListener implements ContactListener {

    @Override
    public void beginContact(Contact contact) {
        Gdx.app.log("Begin contact", "");


        Fixture fixA = contact.getFixtureA();
        Fixture fixB = contact.getFixtureB();
        Fixture player;

        if (fixA.getUserData() == "ladder" || fixB.getUserData() == "ladder") {
            Gdx.app.log("LADDER", "collision");

            if (fixA.getUserData() == "ladder") {
                player = fixA;
            } else {
                player = fixB;
            }

//            player.climb();

        }

        if (fixA.getUserData() == "enemy" || fixB.getUserData() == "enemy") {
            Gdx.app.log("ENEMY", "collision");
            if (fixA.getUserData() == "player") {
                player = fixA;
            } else {
                player = fixB;
            }

//            player.die();
        }

    }

    @Override
    public void endContact(Contact contact) {
        Gdx.app.log("End Contact", "");
    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
