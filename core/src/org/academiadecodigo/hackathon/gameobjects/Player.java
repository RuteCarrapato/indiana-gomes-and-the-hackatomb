package org.academiadecodigo.hackathon.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.utils.Array;
import org.academiadecodigo.hackathon.Indiana;
import org.academiadecodigo.hackathon.screens.GameOverScreen;
import org.academiadecodigo.hackathon.screens.PlayScreen;
import org.academiadecodigo.hackathon.screens.WinScreen;
import org.academiadecodigo.hackathon.utils.Constants;

/**
 * Created by codecadet on 16/03/17.
 */
public class Player extends GameObject implements com.badlogic.gdx.InputProcessor {

    public State currentState;
    private State previousState;

    private Animation<TextureRegion> animRun;
    private Animation<TextureRegion> animLadder;
    private Animation animJump;
    private TextureRegion animStand;
    private TextureRegion animLadderStop;

    private float animTimer;
    private boolean runningRight;
    private boolean climbingLadder;
    private boolean playerIsDead = false;
    private boolean onTheFloor = true;

    private Array<Projectile> projectiles;

    private Sound sound;

    public Player(PlayScreen screen) {
        super(screen);

        // Atlas Region in player_movements.png and player_movements.pack
        this.atlasRegion = screen.getAtlas().findRegion(Constants.PLAYER_REGION);
        this.textureRegion = new TextureRegion(atlasRegion, 0, 0, Constants.HUMAN_SIZE, Constants.HUMAN_SIZE);
        setBounds(0, 0, Constants.HUMAN_SIZE / Constants.PPM, Constants.HUMAN_SIZE / Constants.PPM);

        setRegion(textureRegion);

        // State and Animations
        currentState = State.STANDING;
        previousState = State.STANDING;
        animTimer = 0;
        runningRight = true;

        animStand = screen.getAtlas().findRegion("player");
        animLadderStop = screen.getAtlas().findRegion("playerladder1");

        Array<TextureRegion> frames = new Array<TextureRegion>();

        for (int i = 1; i < 6; i++) {
            frames.add(new TextureRegion(getTexture(), i * 16, 0, 16, 16));
        }

        animRun = new Animation<TextureRegion>(0.1f, frames);
        frames.clear();

        for (int i = 6; i < 8; i++) {
            frames.add(new TextureRegion(getTexture(), i * 16, 0, 16, 16));
        }

        animLadder = new Animation<TextureRegion>(0.1f, frames);
        frames.clear();

        definePlayer();

        projectiles = new Array<Projectile>();
    }

    private void definePlayer() {

        BodyDef bdef = new BodyDef();

        bdef.position.set(52 / Constants.PPM, 52 / Constants.PPM);

        bdef.type = BodyDef.BodyType.DynamicBody;
        b2dbody = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(6 / Constants.PPM);

        fdef.shape = shape;
        b2dbody.createFixture(fdef).setUserData(this);
    }

    /**
     * Handles player input
     *
     * @param dt
     */
    int count = 0;

    public void handleInput(float dt) {

        if (keyUp(Input.Keys.ANY_KEY) && climbingLadder) {
            this.currentState = State.LADDER_STOP;
            this.b2dbody.getLinearVelocity().y = 0;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) && b2dbody.getLinearVelocity().x <= Constants.PLAYER_X_SPEED) {
            this.b2dbody.applyLinearImpulse(new Vector2(Constants.PLAYER_RUN_FORCE, 0), this.b2dbody.getWorldCenter(), true);
        }

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) && b2dbody.getLinearVelocity().x >= -Constants.PLAYER_X_SPEED) {

            this.b2dbody.applyLinearImpulse(new Vector2(-Constants.PLAYER_RUN_FORCE, 0), this.b2dbody.getWorldCenter(), true);
        }


        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE) && onTheFloor) {
            this.b2dbody.applyLinearImpulse(new Vector2(0, 2f), this.b2dbody.getWorldCenter(), true);
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.F)) {
            fire();
        }

        if (Gdx.input.isKeyPressed(Input.Keys.UP) && climbingLadder) {
            this.b2dbody.setLinearVelocity(0, 1);
        }
    }

    public void update(float dt) {

        setPosition(b2dbody.getPosition().x - getWidth() / 2, b2dbody.getPosition().y - getHeight() / 2);
        setRegion(getFrame(dt));

        for (Projectile projectile : projectiles) {
            projectile.update(dt);
            if (projectile.isDestroyed()) {
                System.out.println();
                projectiles.removeValue(projectile, true);
            }
        }
    }

    public void hittingLadder() {

        if (climbingLadder) {
            this.climbingLadder = false;
        } else {
            this.climbingLadder = true;
        }
    }

    private TextureRegion getFrame(float dt) {

        currentState = getState(dt);

        TextureRegion region = null;

        switch (currentState) {

            case FALLING:
                region = animStand;
                break;
            case JUMPING:
                region = animStand;
                break;
            case STANDING:
                region = animStand;
                break;
            case RUNNING:
                region = animRun.getKeyFrame(animTimer, true);
                break;
            case DEAD:
                region = animStand;
                break;
            case LADDER:
                region = animLadder.getKeyFrame(animTimer, true);
                break;
            case LADDER_STOP:
                region = animLadderStop;
                break;
            default:
                region = animStand;
        }

        if ((b2dbody.getLinearVelocity().x < 0 || !runningRight) && !region.isFlipX()) {
            region.flip(true, false);
            runningRight = false;

        } else if ((b2dbody.getLinearVelocity().x > 0 || runningRight) && region.isFlipX()) {
            region.flip(true, false);
            runningRight = true;
        }

        animTimer = currentState == previousState ? animTimer + dt : 0;
        previousState = currentState;

        return region;
    }

    private State getState(float dt) {

        if (climbingLadder) {
            return State.LADDER;
        }

        if (b2dbody.getLinearVelocity().y > 0 || (b2dbody.getLinearVelocity().y < 0 && previousState == State.JUMPING && !climbingLadder)) {
            return State.JUMPING;
        }

        if (b2dbody.getLinearVelocity().y < 0 && !climbingLadder) {
            return State.FALLING;
        }

        if (b2dbody.getLinearVelocity().x != 0) {
            return State.RUNNING;
        }

        return State.STANDING;
    }

    public void die() {
        if (!isDead()) {

            screen.getGame().setScreen(new GameOverScreen(screen.getGame()));

            //TODO: Boni: Implement sound effect of dead/game over
            playerIsDead = true;
        }
    }

    private boolean isDead() {
        return playerIsDead;
    }

    public float getAnimTimer() {
        return animTimer;
    }

    private void fire() {
        projectiles.add(new Projectile(screen, b2dbody.getPosition().x, b2dbody.getPosition().y, runningRight));

        sound = Indiana.manager.get("audio/sounds/GUN.mp3", Sound.class);
        sound.play();
    }

    public void draw(Batch batch) {
        super.draw(batch);

        for (Projectile projectile : projectiles) {
            projectile.draw(batch);
        }
    }

    public void setOnTheFloor(boolean onTheFloor) {

        this.onTheFloor = onTheFloor;
    }


    public void win() {
        screen.getGame().setScreen(new WinScreen(screen.getGame()));
    }


    // INPUT PROCESSOR IMPLEMENTATION
    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {

        switch (keycode) {
            case Input.Keys.UP:
            case Input.Keys.DOWN:
            case Input.Keys.RIGHT:
            case Input.Keys.LEFT:
            case Input.Keys.SPACE:
            case Input.Keys.ANY_KEY:
                return true;
        }

        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }

    public enum State {
        FALLING,
        JUMPING,
        STANDING,
        RUNNING,
        DEAD,
        LADDER,
        LADDER_STOP
    }
}

