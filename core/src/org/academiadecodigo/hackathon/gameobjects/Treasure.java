package org.academiadecodigo.hackathon.gameobjects;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import org.academiadecodigo.hackathon.gameobjects.GameObject;
import org.academiadecodigo.hackathon.screens.PlayScreen;
import org.academiadecodigo.hackathon.utils.Constants;

/**
 * Created by codecadet on 16/03/17.
 */
public class Treasure extends GameObject {

    public Treasure(PlayScreen screen, Rectangle rect) {
        super(screen);
        defineTreasure(rect);

    }

    private void defineTreasure(Rectangle rect) {
        BodyDef bdef = new BodyDef();

        bdef.type = BodyDef.BodyType.StaticBody;
        bdef.position.set((rect.getX() + rect.getWidth() / 2) / Constants.PPM, (rect.getY() + rect.getHeight() / 2) / Constants.PPM);

        b2dbody = world.createBody(bdef);
    }
}
