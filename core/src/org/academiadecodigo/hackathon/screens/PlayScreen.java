package org.academiadecodigo.hackathon.screens;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import org.academiadecodigo.hackathon.Indiana;

/**
 * Created by codecadet on 16/03/17.
 */
public class PlayScreen extends AbstractGameScreen {

    // BOX2D
    private World world;
    private Box2DDebugRenderer b2dr;

    // TILED MAP    TODO: Está aqui preparado para ti BONI
//    private TmxMapLoader mapLoader;
//    private TiledMap map;
//    private OrthogonalTiledMapRenderer renderer;

    public PlayScreen(Indiana game) {

        //this.game = game;
        // BOX2D
        world = new World(new Vector2(0, 0), true);
        b2dr = new Box2DDebugRenderer();

        // TILED MAP        TODO: Está aqui preparado para ti BONI
//        mapLoader = new TmxMapLoader();
//        map = mapLoader.load("map.tmx");
//        renderer = new OrthogonalTiledMapRenderer(map);

        BodyDef bdef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fdef = new FixtureDef();
        Body body;

        // CREATE THE MAP OBJECTS ON LAYER 2
        for(MapObject object : map.getLayers().get(2).getObjects().getByType(RectangleMapObject.class)) {

            Rectangle rect = ((RectangleMapObject)object).getRectangle();

            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set((rect.getX() + rect.getWidth() / 2) / Indiana.PPM, (rect.getY() + rect.getHeight() / 2) / Indiana.PPM);

            body = world.createBody(bdef);

            shape.setAsBox(rect.getWidth() / 2, rect.getHeight() / 2);
            fdef.shape = shape;
            body.createFixture(fdef);
        }
    }

}
