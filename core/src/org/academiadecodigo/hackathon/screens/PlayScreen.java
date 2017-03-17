package org.academiadecodigo.hackathon.screens;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
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
import org.academiadecodigo.hackathon.colisiondetector.WorldContactListener;
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

    //Music
    private Music music;

    public PlayScreen(Indiana game) {
        this.game = game;
        atlas = new TextureAtlas("sprites.pack");

        gameCam = new OrthographicCamera();
        gamePort = new FitViewport(Constants.VIEW_WIDTH / Constants.PPM, Constants.VIEW_HEIGHT / Constants.PPM, gameCam);

        mapLoader = new TmxMapLoader();
        map = mapLoader.load(Constants.LEVEL1_TMX);
        renderer = new OrthogonalTiledMapRenderer(map, 1 / Constants.PPM);

        gameCam.position.set(gamePort.getWorldWidth() / 2, gamePort.getWorldHeight() / 2, 0);

        // Creates the player TODO: MADE BY JOAQUIM CHECKA RUBEN
        this.world = new World(new Vector2(0, Constants.GRAVITY), true);
        debugRenderer = new Box2DDebugRenderer();

        creator = new WorldCreator(this);
        player = new Player(this);
        music = Indiana.manager.get("audio/music/rick.mp3", Music.class);
        music.setLooping(true);
        music.play();

    }

    public TextureAtlas getAtlas() {
        return atlas;
    }

    public void update(float dt) {
        handleInput(dt);

        player.update(dt);

        world.step(1 / 60f, 6, 2);

        if (player.currentState != Player.State.DEAD) {
            gameCam.position.x = player.b2dbody.getPosition().x;
        }

        //Update the gameCam
        gameCam.update();

        //renderer draws what the camera views
        renderer.setView(gameCam);


    }

    private void handleInput(float dt) {
        // Player Movement
        player.handleInput(dt);

    }

    @Override
    public void show() {

    }


    @Override
    public void render(float delta) {
        //separate our update logic from render
        update(delta);

        //Clear the game screen with Black
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //render our game map
        renderer.render();

        //renderer our Box2DDebugLines
        debugRenderer.render(world, gameCam.combined);

        world.setContactListener(new WorldContactListener());

        game.batch.setProjectionMatrix(gameCam.combined);
        game.batch.begin();
        player.draw(game.batch);


//        for (Enemy enemy : creator.getEnemies())
//            enemy.draw(game.batch);

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






