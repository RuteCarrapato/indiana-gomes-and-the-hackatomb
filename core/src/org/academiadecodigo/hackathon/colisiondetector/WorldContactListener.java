package org.academiadecodigo.hackathon.colisiondetector;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.*;
import org.academiadecodigo.hackathon.gameobjects.*;
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


//        System.out.println(fixA.getUserData());
//        System.out.println(fixB.getUserData());


        if (fixA.getUserData() instanceof Player || fixB.getUserData() instanceof Player && fixA.getUserData() instanceof Ladder || fixB.getUserData() instanceof Ladder) {
            Gdx.app.log("LADDER", "collision");

            player.hittingLadder();
        }

        if (((fixA.getUserData() instanceof Enemy) && (fixB.getUserData() instanceof Player)) || ((fixA.getUserData() instanceof Player) && (fixB.getUserData() instanceof Enemy))) {
            Gdx.app.log("ENEMY", "collision");

            player.die();
        }

        if(fixA.getUserData() instanceof Enemy || fixB.getUserData() instanceof Player){

        }

        if (fixA.getUserData() instanceof Player || fixB.getUserData() instanceof Player && fixA.getUserData() == "ground" || fixB.getUserData() == "ground") {

            player.setOnTheFloor(true);
        }

        if (fixA.getUserData() instanceof Projectile || fixB.getUserData() instanceof Projectile && fixA.getUserData() == "ground" || fixB.getUserData() == "ground") {

            Fixture projectileFixture;
            Projectile projectile = null;

            if (fixA.getUserData() instanceof Projectile) {
                projectileFixture = fixA;
                projectile = (Projectile) projectileFixture.getUserData();

            } else {
                projectileFixture = fixB;
                projectile = (Projectile) projectileFixture.getUserData();
            }

            projectile.setToDestroy();
        }

        if (((fixA.getUserData() instanceof Projectile) && (fixB.getUserData() instanceof Enemy)) || ((fixA.getUserData() instanceof Enemy) && (fixB.getUserData() instanceof Projectile))) {
            Projectile projectile = null;
            Enemy enemy = null;

            if (fixA.getUserData() instanceof Projectile) {
                projectile = (Projectile)(fixA.getUserData());
                enemy = (Enemy)(fixB.getUserData());

            } else {
                projectile = (Projectile)(fixB.getUserData());
                enemy = (Enemy)(fixA.getUserData());
            }

            projectile.setToDestroy();
            enemy.die();
        }

        // Collision Player with Treasure
        if (fixA.getUserData() instanceof Player || fixB.getUserData() instanceof Player && fixA.getUserData() instanceof Treasure || fixB.getUserData() instanceof Treasure) {
            player.win();
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
