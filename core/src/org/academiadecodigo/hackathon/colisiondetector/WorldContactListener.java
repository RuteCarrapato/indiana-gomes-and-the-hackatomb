package org.academiadecodigo.hackathon.colisiondetector;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.*;
import org.academiadecodigo.hackathon.gameobjects.Player;
import org.academiadecodigo.hackathon.screens.PlayScreen;

public class WorldContactListener implements ContactListener {

    public World world;
    public Player player;

    public WorldContactListener(PlayScreen screen) {
        this.world = screen.getWorld();
        this.player = screen.getPlayer();
    }

    @Override
    public void beginContact(Contact contact) {
        Gdx.app.log("Begin contact", "");


        Fixture fixA = contact.getFixtureA();
        Fixture fixB = contact.getFixtureB();

        if (fixA.getUserData() == "player" || fixB.getUserData() == "player" && fixA.getUserData() == "ladder" || fixB.getUserData() == "ladder") {
            Gdx.app.log("LADDER", "collision");

            player.hittingLadder();

        }

    }

    @Override
    public void endContact(Contact contact) {
        Gdx.app.log("End Contact", "");

        Fixture fixA = contact.getFixtureA();
        Fixture fixB = contact.getFixtureB();

        if (fixA.getUserData() == "player" || fixB.getUserData() == "player" && fixA.getUserData() == "ladder" || fixB.getUserData() == "ladder") {
            Gdx.app.log("LADDER", "collision");

            player.hittingLadder();

        }
    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
