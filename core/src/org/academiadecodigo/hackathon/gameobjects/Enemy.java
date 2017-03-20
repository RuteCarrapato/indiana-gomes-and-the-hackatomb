package org.academiadecodigo.hackathon.gameobjects;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.utils.Array;
import org.academiadecodigo.hackathon.screens.PlayScreen;
import org.academiadecodigo.hackathon.utils.Constants;

public class Enemy extends GameObject {

    public boolean enemyIsDead;
    private int index;

    private Animation<TextureRegion> animWalk;
    private TextureRegion stand;

    private State currentState;
    private State previousState;
    private float animTimer;
    private boolean runningRight;

    private int counter = 0;
    private int signal = 1;
    private boolean setToDestroy;

    public Enemy(PlayScreen screen, Rectangle rectangle, int index) {
        super(screen, rectangle.getX(), rectangle.getY());

        this.index = index;
        this.world = screen.getWorld();
        this.screen = screen;

        this.atlasRegion = screen.getAtlas().findRegion(Constants.ENEMY_REGION);
        this.textureRegion = new TextureRegion(atlasRegion, 0, 0, Constants.HUMAN_SIZE, Constants.HUMAN_SIZE);

        setBounds(0, 0, Constants.HUMAN_SIZE / Constants.PPM, Constants.HUMAN_SIZE / Constants.PPM);
        setRegion(textureRegion);

        currentState = State.STANDING;
        previousState = State.STANDING;
        animTimer = 0;
        runningRight = true;

        stand = screen.getAtlas().findRegion("zenemy");

        Array<TextureRegion> frames = new Array<TextureRegion>();

        for(int i = 11; i < 13; i++) {
            frames.add(new TextureRegion(getTexture(), i * 16, 0, 16, 16));
        }

        animWalk = new Animation<TextureRegion>(0.1f, frames);
        frames.clear();

        defineEnemy(rectangle);
    }

    public void defineEnemy(Rectangle rect) {

        BodyDef bdef = new BodyDef();

        bdef.type = BodyDef.BodyType.DynamicBody;
        bdef.position.set((rect.getX() + rect.getWidth() / 2) / Constants.PPM, (rect.getY() + rect.getHeight() / 2) / Constants.PPM);


        b2dbody = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(6 / Constants.PPM);

        fdef.shape = shape;
        b2dbody.createFixture(fdef).setUserData(this);

    }

    public void move() {
        counter++;

        if(counter % 60 == 0){
            if(index > 6) {
                this.b2dbody.applyForceToCenter(signal * 100f, 0, true);

            } else {
                this.b2dbody.applyForceToCenter(signal * 65f,0,true);
            }
            signal *= -1;
        }
    }

    public void update(float dt) {

        setPosition(b2dbody.getPosition().x - getWidth() / 2, b2dbody.getPosition().y - (getHeight() / 2) + Constants.SPRITE_POSITION_FIX);
        setRegion(getFrame(dt));

        if (setToDestroy && !enemyIsDead) {
            world.destroyBody(b2dbody);
            enemyIsDead = true;
        }


    }

    private TextureRegion getFrame(float dt) {

        currentState = getState(dt);

        TextureRegion region = null;

        switch(currentState) {

            case RUNNING:
                region = animWalk.getKeyFrame(animTimer, true);
                break;
            case STANDING:
                region = stand;
            default:
                region = stand;
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

        if(enemyIsDead) {
            return State.DEAD;
        }

        if(b2dbody.getLinearVelocity().x != 0) {
            return State.RUNNING;
        }

        return State.STANDING;
    }


    public void die() {
        enemyIsDead = true;
    }

    public boolean isDead() {
        return enemyIsDead;
    }

    public void setToDestroy() {
        setToDestroy = true;
    }

    public enum State {
        RUNNING,
        FALLING,
        STANDING,
        DEAD
    }

}
