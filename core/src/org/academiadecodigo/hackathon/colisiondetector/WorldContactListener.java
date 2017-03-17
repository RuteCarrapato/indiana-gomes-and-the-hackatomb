package org.academiadecodigo.hackathon.colisiondetector;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.*;
import org.academiadecodigo.hackathon.gameobjects.Ladder;
import org.academiadecodigo.hackathon.gameobjects.Player;
import org.academiadecodigo.hackathon.gameobjects.Projectile;
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


        Fixture fixA = contact.getFixtureA();
        Fixture fixB = contact.getFixtureB();


        System.out.println(fixA.getUserData());
        System.out.println(fixB.getUserData());


        if (fixA.getUserData() instanceof Player || fixB.getUserData() instanceof Player && fixA.getUserData() instanceof Ladder || fixB.getUserData() instanceof Ladder) {
            Gdx.app.log("LADDER", "collision");

            player.hittingLadder();
        }

        if (fixA.getUserData() == "enemy" || fixB.getUserData() == "enemy" && fixA.getUserData() instanceof Player || fixB.getUserData() instanceof Player) {
            Gdx.app.log("ENEMY", "collision");

            player.die();
        }


        if (fixA.getUserData() instanceof Player || fixB.getUserData() instanceof Player && fixA.getUserData() == "ground" || fixB.getUserData() == "ground") {

            player.setOnTheFloor(true);
        }

        if(fixA.getUserData() instanceof Projectile || fixB.getUserData() instanceof Projectile && fixA.getUserData() == "ground" || fixB.getUserData()== "ground") {

            Fixture projectileFixture;
            Projectile projectile = null;

            if(fixA.getUserData() instanceof Projectile) {
                projectileFixture = fixA;
                projectile = (Projectile)projectileFixture.getUserData();

            } else {
                projectileFixture = fixB;
                projectile = (Projectile)projectileFixture.getUserData();
            }

            projectile.setToDestroy();
        }

    }

    @Override
    public void endContact(Contact contact) {

        Fixture fixA = contact.getFixtureA();
        Fixture fixB = contact.getFixtureB();


        if (fixA.getUserData() instanceof Player || fixB.getUserData() instanceof Player && fixA.getUserData() instanceof Ladder || fixB.getUserData() instanceof Ladder) {
            Gdx.app.log("LADDER", "collision");

            player.hittingLadder();
        }


        if (fixA.getUserData() instanceof Player || fixB.getUserData() instanceof Player && fixA.getUserData() == "ground" || fixB.getUserData() == "ground") {

            System.out.println("is not on floor");

            player.setOnTheFloor(false);
        }

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
