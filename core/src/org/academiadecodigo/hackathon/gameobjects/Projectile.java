package org.academiadecodigo.hackathon.gameobjects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import org.academiadecodigo.hackathon.screens.PlayScreen;
import org.academiadecodigo.hackathon.utils.Constants;

/**
 * Created by codecadet on 16/03/17.
 */
public class Projectile extends GameObject {
    boolean fireRight;
    boolean destroyed;
    boolean setToDestroy;


    public Projectile(PlayScreen screen, float x, float y, boolean fireRight) {
        super(screen, new Texture(fireRight ? Constants.PROJECTILE_RIGHT : Constants.PROJECTILE_LEFT));//"bullet_left.png"));
        this.fireRight = fireRight;
        setBounds(x, y + (float)0.3*Constants.HUMAN_SIZE/Constants.PPM, Constants.PROJECTILE_WIDTH / Constants.PPM, Constants.PROJECTILE_HEIGHT / Constants.PPM);
        defineProjectile();
    }

    private void defineProjectile() {
        BodyDef bdef = new BodyDef();
        bdef.position.set(
                fireRight ? getX() + (Constants.PROJECTILE_WIDTH) / Constants.PPM
                          : getX() - (Constants.PROJECTILE_WIDTH) / Constants.PPM, getY());

        bdef.type = BodyDef.BodyType.DynamicBody;//DynamicBody;
        if (!world.isLocked()) {
            b2dbody = world.createBody(bdef);
        }

        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(Constants.PROJECTILE_RADIUS / Constants.PPM);
        fdef.shape = shape;
        fdef.restitution = 1;
        fdef.friction = 0;
        fdef.density = 0f;


        b2dbody.createFixture(fdef).setUserData(this);
    }

    public void update(float dt) {

        setPosition(b2dbody.getPosition().x - getWidth() / 2, b2dbody.getPosition().y - getHeight() / 2);

        b2dbody.setLinearVelocity(new Vector2(fireRight ? Constants.PROJECTILE_SPEED : -Constants.PROJECTILE_SPEED, 0.15f)); //magic number nullify gravity
        if (setToDestroy && !destroyed) {
            world.destroyBody(b2dbody);
            destroyed = true;
        }

        if (b2dbody.getLinearVelocity().y > 2f) {
            b2dbody.setLinearVelocity(b2dbody.getLinearVelocity().x, 2f);
        }

    }

    public void setToDestroy() {
        setToDestroy = true;
    }

    public boolean isDestroyed() {
        return destroyed;
    }

    @Override
    public void draw(Batch batch){
        super.draw(batch);
    }
}
