package org.academiadecodigo.hackathon.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import org.academiadecodigo.hackathon.screens.PlayScreen;
import org.academiadecodigo.hackathon.utils.Constants;

/**
 * Created by codecadet on 16/03/17.
 */
public class Player extends GameObject {

    public Player(PlayScreen screen) {
        super(screen);
        System.out.println(super.getTexture());
        this.atlasRegion = screen.getAtlas().findRegion("player");
        this.textureRegion = new TextureRegion(atlasRegion, 0, 0, 16, 16);
        setBounds(0, 0, 16 / Constants.PPM, 16 / Constants.PPM);
        setRegion(textureRegion);
        definePlayer();
    }

    private void definePlayer() {

        BodyDef bdef = new BodyDef();

        bdef.position.set(52 / Constants.PPM , 52 / Constants.PPM);

        bdef.type = BodyDef.BodyType.DynamicBody;
        b2dbody = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(5 / Constants.PPM);

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

        if(Gdx.input.isKeyPressed(Input.Keys.UP)) {
            this.b2dbody.applyLinearImpulse(new Vector2(0, 4f), this.b2dbody.getWorldCenter(), true);
        }
    }

    public void update(float dt) {

    }
}
