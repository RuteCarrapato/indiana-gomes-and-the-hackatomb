package org.academiadecodigo.hackathon.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;
import org.academiadecodigo.hackathon.Indiana;
import org.academiadecodigo.hackathon.screens.PlayScreen;
import org.academiadecodigo.hackathon.utils.Constants;

import static org.academiadecodigo.hackathon.Indiana.manager;

/**
 * Created by codecadet on 16/03/17.
 */
public class Player extends GameObject {

    public State currentState;
    public State previousState;

    public Animation<TextureRegion> animRun;
    public Animation<TextureRegion> animLadder;
    public Animation animJump;
    public TextureRegion animStand;

    public float animTimer;
    public boolean runningRight;
    public boolean climbingLadder;
    public boolean playerIsDead;

    private Array<Projectile> projectiles;

    private Sound sound;

    public Player(PlayScreen screen) {
        super(screen);

        // Atlas Region in sprites.png and sprites.pack
        this.atlasRegion = screen.getAtlas().findRegion(Constants.PLAYER_REGION);
        this.textureRegion = new TextureRegion(atlasRegion, 0, 0, Constants.HUMAN_SIZE, Constants.HUMAN_SIZE);
        setBounds(0, 0, Constants.HUMAN_SIZE / Constants.PPM, Constants.HUMAN_SIZE / Constants.PPM);

        setRegion(textureRegion);

        animStand = screen.getAtlas().findRegion("player");

        // State and Animations
        currentState = State.STANDING;
        previousState = State.STANDING;
        animTimer = 0;
        runningRight = true;

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
        b2dbody.createFixture(fdef).setUserData(Constants.PLAYER_REGION);
    }

    public void handleInput(float dt) {


        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) && b2dbody.getLinearVelocity().x <= Constants.PLAYER_X_SPEED) {
            this.b2dbody.applyLinearImpulse(new Vector2(0.1f, 0), this.b2dbody.getWorldCenter(), true);
        }

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) && b2dbody.getLinearVelocity().x >= -Constants.PLAYER_X_SPEED) {

            this.b2dbody.applyLinearImpulse(new Vector2(-0.1f, 0), this.b2dbody.getWorldCenter(), true);
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            this.b2dbody.applyLinearImpulse(new Vector2(0, 2f), this.b2dbody.getWorldCenter(), true);
        }

        if (Gdx.input.isKeyPressed(Input.Keys.UP) && climbingLadder) {
            System.out.println("GOING UP AND CLIMBING LADDER");
           b2dbody.setLinearVelocity(0, 1);
        }
    }

    public void update(float dt) {

        setPosition(b2dbody.getPosition().x - getWidth() / 2, b2dbody.getPosition().y - getHeight() / 2);
        setRegion(getFrame(dt));

        for (Projectile projectile : projectiles) {
            projectile.update(dt);
            if (projectile.isDestroyed()) {
                projectiles.removeValue(projectile, true);
            }
        }
    }

    public void hittingLadder() {

        if(climbingLadder) {
            climbingLadder = false;
        } else {
            this.climbingLadder = true;
        }
    }

    public TextureRegion getFrame(float dt) {

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

    public State getState(float dt) {

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

    public enum State {
        FALLING,
        JUMPING,
        STANDING,
        RUNNING,
        DEAD,
        LADDER
    }



    public void resetGravity() {
        b2dbody.setGravityScale(1);
    }

    public void die() {
        if (!isDead()) {
            //TODO: Boni: Implement sound effect of dead/game over
            playerIsDead = true;
        }
    }

    public boolean isDead() {
        return playerIsDead;
    }

    public void fire() {
        projectiles.add(new Projectile(screen, b2dbody.getPosition().x, b2dbody.getPosition().y, runningRight));

        sound = Indiana.manager.get("audio/sounds/GUN.mp3", Sound.class);
        sound.play();
    }
}

