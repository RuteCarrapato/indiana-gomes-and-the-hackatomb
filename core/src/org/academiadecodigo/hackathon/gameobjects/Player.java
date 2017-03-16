package org.academiadecodigo.hackathon.gameobjects;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import org.academiadecodigo.hackathon.screens.PlayScreen;
import org.academiadecodigo.hackathon.utils.Constants;

/**
 * Created by codecadet on 16/03/17.
 */
public class Player  extends GameObject {

    public Player(PlayScreen screen) {

        super(screen);
        definePlayer();
    }

    public void definePlayer() {

        BodyDef bdef = new BodyDef();
        bdef.position.set(32 / Constants.PPM , 32 / Constants.PPM);
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2dbody = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(5 / Constants.PPM);

        fdef.shape = shape;
        b2dbody.createFixture(fdef);
    }

}
