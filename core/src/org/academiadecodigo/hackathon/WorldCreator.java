package org.academiadecodigo.hackathon;


import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;
import org.academiadecodigo.hackathon.gameobjects.Enemy;
import org.academiadecodigo.hackathon.gameobjects.Ladder;
import org.academiadecodigo.hackathon.gameobjects.Treasure;
import org.academiadecodigo.hackathon.screens.PlayScreen;
import org.academiadecodigo.hackathon.utils.Constants;

//Instantiate game objects
public class WorldCreator {

    private Array<Enemy> enemies;

    public WorldCreator(PlayScreen screen) {

        World world = screen.getWorld();
        TiledMap map = screen.getMap();
        //create body and fixture variables
        BodyDef bdef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fdef = new FixtureDef();
        Body body;

        // create ground bodies/fixtures
        for (MapObject object : map.getLayers().get(Constants.GROUND_INDEX).getObjects().getByType(RectangleMapObject.class)) {

            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set((rect.getX() + rect.getWidth() / 2) / Constants.PPM, (rect.getY() + rect.getHeight() / 2) / Constants.PPM);

            body = world.createBody(bdef);

            shape.setAsBox(rect.getWidth() / 2 / Constants.PPM, rect.getHeight() / 2 / Constants.PPM);
            fdef.shape = shape;
            body.createFixture(fdef).setUserData("ground");
        }

        // create treasure
        for (MapObject object : map.getLayers().get(Constants.TREASURE_INDEX).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            new Treasure(screen, rect);
        }

        // create ladder bodies/fixtures
        for (MapObject object : map.getLayers().get(Constants.LADDER_INDEX).getObjects().getByType(RectangleMapObject.class)) {

            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            /*

            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set((rect.getX() + rect.getWidth() / 2) / Constants.PPM, (rect.getY() + rect.getHeight() / 2) / Constants.PPM);

            body = world.createBody(bdef);

//            shape.setAsBox(rect.getWidth() / 2 / Constants.PPM, rect.getHeight() / 2 / Constants.PPM);
//            fdef.shape = shape;
            body.createFixture(fdef);

           */

            new Ladder(screen, rect);
        }

        //create all enemies
        enemies = new Array<Enemy>();
        int count = 0;
        for(MapObject object: map.getLayers().get(Constants.ENEMY_INDEX).getObjects().getByType(RectangleMapObject.class)) {

            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            enemies.add((new Enemy(screen, rect, count)));
            count++;
        }



    }

    public Array<Enemy> getEnemies() {
        return enemies;
    }
}
