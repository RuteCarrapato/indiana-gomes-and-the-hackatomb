package org.academiadecodigo.hackathon.screens;

import com.badlogic.gdx.graphics.OrthographicCamera;
import org.academiadecodigo.hackathon.Indiana;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import org.academiadecodigo.hackathon.Indiana;

/**
 * Created by codecadet on 16/03/17.
 */
public class PlayScreen extends AbstractGameScreen {

    private Indiana game;
    private Texture texture;
    private OrthographicCamera gameCam;
    private Viewport gamePort;


    public PlayScreen(Indiana game) {
        this.game = game;
        texture = new Texture("badlogic.jpg");
        gameCam = new OrthographicCamera();
        gamePort = new FitViewport(800, 480, gameCam);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.setProjectionMatrix(gameCam.combined);
        game.batch.begin();
        game.batch.draw(texture, 50, 50);
        game.batch.end();
    }

    @Override
    public void resize(int width, int height) {
        gamePort.update(width, height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}



//    // BOX2D
//    private World world;
//    private Box2DDebugRenderer b2dr;
//
//    // TILED MAP    TODO: Está aqui preparado para ti BONI
////    private TmxMapLoader mapLoader;
////    private TiledMap map;
////    private OrthogonalTiledMapRenderer renderer;
//
//    public PlayScreen(Indiana game) {
//
//        //this.game = game;
//        // BOX2D
//        world = new World(new Vector2(0, 0), true);
//        b2dr = new Box2DDebugRenderer();
//
//        // TILED MAP        TODO: Está aqui preparado para ti BONI
////        mapLoader = new TmxMapLoader();
////        map = mapLoader.load("map.tmx");
////        renderer = new OrthogonalTiledMapRenderer(map);
//
//        BodyDef bdef = new BodyDef();
//        PolygonShape shape = new PolygonShape();
//        FixtureDef fdef = new FixtureDef();
//        Body body;
//
//        // CREATE THE MAP OBJECTS ON LAYER 2
//        for(MapObject object : map.getLayers().get(2).getObjects().getByType(RectangleMapObject.class)) {
//
//            Rectangle rect = ((RectangleMapObject)object).getRectangle();
//
//            bdef.type = BodyDef.BodyType.StaticBody;
//            bdef.position.set((rect.getX() + rect.getWidth() / 2) / Indiana.PPM, (rect.getY() + rect.getHeight() / 2) / Indiana.PPM);
//
//            body = world.createBody(bdef);
//
//            shape.setAsBox(rect.getWidth() / 2, rect.getHeight() / 2);
//            fdef.shape = shape;
//            body.createFixture(fdef);
//        }
//    }


