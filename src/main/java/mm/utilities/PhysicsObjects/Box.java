package mm.utilities.PhysicsObjects;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;
import org.jbox2d.dynamics.World;

import static mm.utilities.Makros.GAMEPANE_HEIGHT;
import static mm.utilities.Makros.m_to_px_scale;

public class Box extends RigidBody {

    private Rectangle rect;

    public float height;
    public float width;


    public Box(float x, float y, float gradAngle, float width, float height, World world) {

        this.height = height;
        this.width = width;


        BodyDef bodydef = new BodyDef();
        bodydef.position.set(x, y);
        bodydef.type = BodyType.STATIC;
        bodydef.angle = (float) Math.toRadians(gradAngle);
        body = world.createBody(bodydef);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(width/2, height/2);

        body.createFixture(shape, 0.0f);



        rect = new Rectangle(width* m_to_px_scale, height * m_to_px_scale, Color.DODGERBLUE);
        rect.setX(-width* m_to_px_scale/ 2.0f);
        rect.setY(-height* m_to_px_scale/ 2.0f);
        rect.setTranslateX(body.getPosition().x * m_to_px_scale);
        rect.setTranslateY(GAMEPANE_HEIGHT - body.getPosition().y * m_to_px_scale);
        rect.setRotate(-Math.toDegrees(body.getAngle()));
    }


    public Box(float x, float y, float gradAngle, float width, float height,float density, float friction, World world) {
        BodyDef bodydef = new BodyDef();
        bodydef.position.set(x, y);
        bodydef.type = BodyType.DYNAMIC;
        bodydef.angle = (float) Math.toRadians(gradAngle);
        body = world.createBody(bodydef);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(width/2, height/2);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = density;
        fixtureDef.friction = friction;
        body.createFixture(fixtureDef);

        rect = new Rectangle(width* m_to_px_scale, height * m_to_px_scale, Color.DODGERBLUE);
        rect.setX(-width* m_to_px_scale/ 2.0f);
        rect.setY(-height* m_to_px_scale/ 2.0f);
        rect.setTranslateX(x * m_to_px_scale);
        rect.setTranslateY(500 - y * m_to_px_scale);
        rect.setRotate(gradAngle);
    }
    @Override
    public Rectangle getShape() {
        return this.rect;
    }

}
