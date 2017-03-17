package org.academiadecodigo.hackathon.colisiondetector;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.*;
import org.academiadecodigo.hackathon.gameobjects.Player;

public class WorldContactListener implements ContactListener {

    @Override
    public void beginContact(Contact contact) {


        Fixture fixA = contact.getFixtureA();
        Fixture fixB = contact.getFixtureB();

        System.out.println(fixA.getUserData());
        System.out.println(fixB.getUserData());
        Fixture player;
        Fixture object;

        if (fixA.getUserData() == "ladder" || fixB.getUserData() == "ladder") {
            Gdx.app.log("LADDER", "collision");

            if (fixA.getUserData() == "ladder") {
                player = fixB;
                object = fixA;
            } else {
                player = fixA;
                object = fixB;
            }

            if (object.getUserData() == "ladder" && player.getUserData() instanceof Player) {
                if (player.getUserData() instanceof Player) {
                    System.out.println("player climb");
                    ((Player) player.getUserData()).climb();
                }
            }


        }

        if (fixA.getUserData() == "enemy" || fixB.getUserData() == "enemy") {
            Gdx.app.log("ENEMY", "collision");
            if (fixA.getUserData() == "player") {
                player = fixA;
                object = fixB;
            } else {
                player = fixB;
                object = fixA;
            }

            if (object.getUserData() == "enemy" && player.getUserData() instanceof Player) {
                ((Player) player.getUserData()).die();
            }
        }


        if (fixA.getUserData() instanceof Player || fixB.getUserData() instanceof Player) {

            if (fixA.getUserData() == "ground") {
                System.out.println("is on floor");
                player = fixB;
                object = fixA;
            } else {
                player = fixA;
                object = fixB;
                System.out.println("is on floor");

            }

            if (object.getUserData() == "ground" && player.getUserData() instanceof Player) {
                ((Player) player.getUserData()).setOnTheFloor(true);
            }
        }
    }

    @Override
    public void endContact(Contact contact) {
        Fixture fixA = contact.getFixtureA();
        Fixture fixB = contact.getFixtureB();
        Fixture player;
        Fixture object;

        if (fixA.getUserData() == "ladder" || fixB.getUserData() == "ladder") {
            System.out.println("oi");
            Gdx.app.log("LADDER", "collision");

            if (fixA.getUserData() == "ladder") {
                player = fixB;
                object = fixA;
            } else {
                player = fixA;
                object = fixB;
            }
            if (object.getUserData() == "ladder" && player.getUserData() instanceof Player) {

                if (player.getUserData() instanceof Player) {
                    System.out.println("player climb");
                    ((Player) player.getUserData()).resetGravity();
                }
            }
        }


        if (fixA.getUserData() instanceof Player || fixB.getUserData() instanceof Player) {

            System.out.println("is not on floor");
            if (fixA.getUserData() == "ground") {

                player = fixB;
                object = fixA;
            } else {
                player = fixA;
                object = fixB;

            }

            if (object.getUserData() == "ground" && player.getUserData() instanceof Player) {
                ((Player) player.getUserData()).setOnTheFloor(false);
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
