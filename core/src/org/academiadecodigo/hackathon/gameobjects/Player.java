package org.academiadecodigo.hackathon.gameobjects;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.*;
import org.academiadecodigo.hackathon.screens.PlayScreen;
import org.academiadecodigo.hackathon.utils.Constants;

/**
 * Created by codecadet on 16/03/17.
 */
public class Player extends GameObject {


    public Player(PlayScreen screen) {
        super(screen);
        textureRegion = new TextureRegion(getTexture(), 16, 0, 16, 16);
        setBounds(0, 0, 16 / Constants.PPM, 16 / Constants.PPM);
        setRegion(textureRegion);
        definePlayer();
    }

    public void definePlayer() {

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

    /*public void handleInput(float dt) {

        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            this.b2dbody.applyLinearImpulse(new Vector2(0.1f, 0), this.b2dbody.getWorldCenter(), true);
        }

        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            this.b2dbody.applyLinearImpulse(new Vector2(-0.1f, 0), this.b2dbody.getWorldCenter(), true);
        }
    }*/

    public void update() {

    }
}
