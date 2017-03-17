package org.academiadecodigo.hackathon.colisiondetector;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.*;
import org.academiadecodigo.hackathon.gameobjects.Player;

public class WorldContactListener implements ContactListener {

    @Override
    public void beginContact(Contact contact) {

        Fixture fixA = contact.getFixtureA();
        Fixture fixB = contact.getFixtureB();
        Fixture player;

        if (fixA.getUserData() == "ladder" || fixB.getUserData() == "ladder") {
            Gdx.app.log("LADDER", "collision");

            if (fixA.getUserData() == "ladder") {
                player = fixB;
            } else {
                player = fixA;
            }

                if (player.getUserData() instanceof Player) {
                    System.out.println("player climb");
                    ((Player) player.getUserData()).climb();
                }


        }

        if (fixA.getUserData() == "enemy" || fixB.getUserData() == "enemy") {
            Gdx.app.log("ENEMY", "collision");
            if (fixA.getUserData() == "player") {
                player = fixA;
            } else {
                player = fixB;
            }

//            ((Player)player).die();
        }

    }

    @Override
    public void endContact(Contact contact) {
        Fixture fixA = contact.getFixtureA();
        Fixture fixB = contact.getFixtureB();
        Fixture player;

        if (fixA.getUserData() == "ladder" || fixB.getUserData() == "ladder") {
            System.out.println("oi");
            Gdx.app.log("LADDER", "collision");

            if (fixA.getUserData() == "ladder") {
                player = fixB;
            } else {
                player = fixA;
            }
            if (player.getUserData() instanceof Player) {
                System.out.println("player climb");
                ((Player) player.getUserData()).resetGravity();
            }
        }
    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
