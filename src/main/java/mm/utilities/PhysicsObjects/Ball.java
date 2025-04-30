package mm.utilities.PhysicsObjects;


import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;
import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;
import org.jbox2d.dynamics.World;

import static mm.utilities.Makros.*;

public class Ball extends RigidBody {



    private Circle circle;

    public Ball(float x, float y, float radius, float gradAngle, World world) {


        BodyDef bodydef = new BodyDef();
        bodydef.position.set(x, y);
        bodydef.type = BodyType.STATIC;
        bodydef.angle = (float) Math.toRadians(gradAngle);
        body = world.createBody(bodydef);


        CircleShape shape = new CircleShape();
        shape.setRadius(radius);

        body.createFixture(shape, 0.0f);

        circle = new Circle(radius* m_to_px_scale, Color.DARKGREEN);
        circle.setCenterX(0);
        circle.setCenterY(0);
        circle.setTranslateX(x * m_to_px_scale);
        circle.setTranslateY(GAMEPANE_HEIGHT-y * m_to_px_scale);
        circle.setRotate(-gradAngle);
    }

    public Ball(float x, float y, float radius, float gradAngle,float density, float friction, World world) {


        BodyDef bodydef = new BodyDef();
        bodydef.position.set(x, y);
        bodydef.type = BodyType.DYNAMIC;
        bodydef.angle = (float) Math.toRadians(gradAngle);
        body = world.createBody(bodydef);


        CircleShape shape = new CircleShape();
        shape.setRadius(radius);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = density;
        fixtureDef.friction = friction;

        body.createFixture(fixtureDef);

        circle = new Circle(radius * m_to_px_scale, Color.DARKGREEN);
        circle.setCenterX(0);
        circle.setCenterY(0);
        circle.setTranslateX(x * m_to_px_scale);
        circle.setTranslateY(GAMEPANE_HEIGHT-y * m_to_px_scale);
        circle.setRotate(-gradAngle);
    }

    @Override
    public Shape getShape() {
        return this.circle;
    }

}
