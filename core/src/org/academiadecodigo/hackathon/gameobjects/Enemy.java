package org.academiadecodigo.hackathon.gameobjects;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import org.academiadecodigo.hackathon.screens.PlayScreen;
import org.academiadecodigo.hackathon.utils.Constants;

/**
 * Created by codecadet on 16/03/17.
 */
public class Enemy extends GameObject {

    public Enemy(PlayScreen screen, Rectangle rectangle) {
        super(screen, rectangle.getX(), rectangle.getY());
        this.world = screen.getWorld();
        this.screen = screen;
        defineEnemy(rectangle);
//        this.b2dbody = new Body();
    }

    public void defineEnemy(Rectangle rect) {

        BodyDef bdef = new BodyDef();

        bdef.type = BodyDef.BodyType.DynamicBody;
        bdef.position.set((rect.getX() + rect.getWidth() / 2) / Constants.PPM, (rect.getY() + rect.getHeight() / 2) / Constants.PPM);

        b2dbody = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        PolygonShape shape = new PolygonShape();

        shape.setAsBox(rect.getWidth() / 2 / Constants.PPM, rect.getHeight() / 2 / Constants.PPM);
        fdef.shape = shape;

        b2dbody.createFixture(fdef).setUserData(this);

    }

    public void move() {
        System.out.println(b2dbody.getPosition().x);

        if (this.b2dbody.getPosition().x == 0) {
            this.b2dbody.applyLinearImpulse(new Vector2(0.3f, 0), this.b2dbody.getWorldCenter(), true);
        } else if (this.b2dbody.getLinearVelocity().x >= 0.2f) {
            System.out.println("entrou la dentro");
            this.b2dbody.applyLinearImpulse(new Vector2(-0.4f, 0), this.b2dbody.getWorldCenter(), true);
        }
    }
}
