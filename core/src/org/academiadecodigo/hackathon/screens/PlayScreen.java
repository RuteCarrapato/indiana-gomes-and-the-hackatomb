package org.academiadecodigo.hackathon.screens;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import org.academiadecodigo.hackathon.Indiana;
import org.academiadecodigo.hackathon.WorldCreator;
import org.academiadecodigo.hackathon.gameobjects.Player;
import org.academiadecodigo.hackathon.utils.Constants;

public class PlayScreen extends AbstractGameScreen {

    //Reference to our game, used to set Screens
    private Indiana game;
    private TextureAtlas atlas;

    //Basic PlayScreen variables
    private OrthographicCamera gameCam;
    private Viewport gamePort;

    //TileMap variables
    private TmxMapLoader mapLoader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;

    //BOX2D variables
    private World world;
    private Box2DDebugRenderer debugRenderer;
    private WorldCreator creator;

    //Sprites
    private Player player;

    private Texture texture;

    public PlayScreen(Indiana game) {
        this.game = game;

        gameCam = new OrthographicCamera();
        gamePort = new FitViewport(Constants.VIEW_WIDTH / Constants.PPM, Constants.VIEW_HEIGHT / Constants.PPM, gameCam);

        mapLoader = new TmxMapLoader();
        map = mapLoader.load("level1.tmx");
        renderer = new OrthogonalTiledMapRenderer(map, 1 / Constants.PPM);

        gameCam.position.set(gamePort.getWorldWidth() / 2, gamePort.getWorldHeight()/ 2, 0);

        // Creates the player TODO: MADE BY JOAQUIM CHECKA RUBEN
        this.world = new World(new Vector2(0, Constants.GRAVITY), true);
        debugRenderer = new Box2DDebugRenderer();
        creator = new WorldCreator(this);
        player = new Player(this);
    }

    public void update(float dt){
        handleInput(dt);

        world.step(1/60f, 6, 2);

        gameCam.position.x = player.b2dbody.getPosition().x;
        gameCam.update();
        renderer.setView(gameCam);

    }

    private void handleInput(float dt) {
        // Player Movement
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            player.b2dbody.applyLinearImpulse(new Vector2(0.1f, 0), player.b2dbody.getWorldCenter(), true);
        }

        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            player.b2dbody.applyLinearImpulse(new Vector2(-0.1f, 0), player.b2dbody.getWorldCenter(), true);
        }
    }

    @Override
    public void show() {

    }


    @Override
    public void render(float delta) {
        update(delta);

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        renderer.render();
        debugRenderer.render(world, gameCam.combined);

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
        map.dispose();
        renderer.dispose();
        world.dispose();
        debugRenderer.dispose();
    }

    public World getWorld() {
        return world;
    }

    public TiledMap getMap() {
        return map;
    }
}






