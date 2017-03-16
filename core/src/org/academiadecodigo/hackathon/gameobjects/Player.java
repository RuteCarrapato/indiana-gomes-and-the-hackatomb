package org.academiadecodigo.hackathon.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import org.academiadecodigo.hackathon.screens.PlayScreen;
import org.academiadecodigo.hackathon.utils.Constants;

/**
 * Created by codecadet on 16/03/17.
 */
public class Player extends GameObject {

    public State currentState;
    public State previousState;
    private Animation animRun;
    private Animation animJump;
    private float animTimer;
    private boolean runningRight;

    public Player(PlayScreen screen) {

        super(screen);

        // Atlas Region in sprites.png and sprites.pack
        this.atlasRegion = screen.getAtlas().findRegion("player");
        this.textureRegion = new TextureRegion(atlasRegion, 0, 0, 16, 16);
        setBounds(0, 0, 16 / Constants.PPM, 16 / Constants.PPM);
        setRegion(textureRegion);

        // State and Animations
        currentState = State.STANDING;
        previousState = State.STANDING;
        animTimer = 0;
        definePlayer();
    }

    private void definePlayer() {

        BodyDef bdef = new BodyDef();

        bdef.position.set(52 / Constants.PPM , 52 / Constants.PPM);

        bdef.type = BodyDef.BodyType.DynamicBody;
        b2dbody = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(6 / Constants.PPM);

        fdef.shape = shape;
        b2dbody.createFixture(fdef);
    }

    public void handleInput(float dt) {

        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT) && b2dbody.getLinearVelocity().x <= 2) {
            this.b2dbody.applyLinearImpulse(new Vector2(0.1f, 0), this.b2dbody.getWorldCenter(), true);
        }

        if(Gdx.input.isKeyPressed(Input.Keys.LEFT) && b2dbody.getLinearVelocity().x >= -2) {
            this.b2dbody.applyLinearImpulse(new Vector2(-0.1f, 0), this.b2dbody.getWorldCenter(), true);
        }

        if(Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
            this.b2dbody.applyLinearImpulse(new Vector2(0, 2f), this.b2dbody.getWorldCenter(), true);
        }
    }

    public void update(float dt) {

        setPosition(b2dbody.getPosition().x - getWidth() / 2, b2dbody.getPosition().y / 2);
    }

    public enum State {
        FALLING,
        JUMPING,
        STANDING,
        RUNNING
    }
}
