package org.academiadecodigo.hackathon.gameobjects;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.*;
import org.academiadecodigo.hackathon.screens.PlayScreen;
import org.academiadecodigo.hackathon.utils.Constants;

/**
 * Created by codecadet on 16/03/17.
 */
public class Enemy extends GameObject {
    public boolean enemyIsDead;
    private float initialPosition;
    private int index;

    public Enemy(PlayScreen screen, Rectangle rectangle, int index) {
        super(screen, rectangle.getX(), rectangle.getY());
        this.index = index;

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

        this.initialPosition = b2dbody.getPosition().x;

    }

    private int counter = 0;
    private int signal = 1;

    public void move() {
//        System.out.println("Position: " + b2dbody.getPosition().x);
//        System.out.println("Velocidade: " + b2dbody.getLinearVelocity());

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


    public void die() {
        System.out.println("Enemy enemyIsDead");
        enemyIsDead = true;
    }
}
