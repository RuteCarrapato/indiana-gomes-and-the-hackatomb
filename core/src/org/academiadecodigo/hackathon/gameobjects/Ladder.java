package org.academiadecodigo.hackathon.gameobjects;

import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.*;
import org.academiadecodigo.hackathon.screens.PlayScreen;
import org.academiadecodigo.hackathon.utils.Constants;

/**
 * Created by codecadet on 16/03/17.
 */
public class Ladder extends GameObject {

    public Ladder(PlayScreen screen, Rectangle rectangle) {
        super(screen);
        defineLadder(rectangle);
    }

    public void defineLadder(Rectangle rect) {

        BodyDef bdef = new BodyDef();

        bdef.type = BodyDef.BodyType.StaticBody;
        bdef.position.set((rect.getX() + rect.getWidth() / 2) / Constants.PPM, (rect.getY() + rect.getHeight() / 2) / Constants.PPM);

        b2dbody = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        PolygonShape shape = new PolygonShape();

        shape.setAsBox(rect.getWidth() / 2 / Constants.PPM, rect.getHeight() / 2 / Constants.PPM);
        fdef.shape = shape;
        fdef.isSensor = true;
        b2dbody.createFixture(fdef).setUserData(Constants.LADDER_SPRITE_NAME);

    }
}
