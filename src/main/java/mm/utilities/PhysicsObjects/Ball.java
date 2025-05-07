package mm.utilities.PhysicsObjects;


import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;
import mm.utilities.ObjectsConf.BallConf;
import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;
import org.jbox2d.dynamics.World;

import static mm.utilities.Makros.*;

public class Ball extends RigidBody {



    private Circle circle;

    private float radius;

    public Ball(float x, float y, float radius, float gradAngle, World world) {


        this.radius = radius;

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


        this.radius = radius;


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


    public Ball(BallConf conf, World world) {

        this.radius = conf.radius;

        if(conf.density == -1 || conf.friction == -1) {
            BodyDef bodydef = new BodyDef();
            bodydef.position.set(conf.x, conf.y);
            bodydef.type = BodyType.STATIC;
            bodydef.angle = (float) Math.toRadians(conf.angle);
            body = world.createBody(bodydef);


            CircleShape shape = new CircleShape();
            shape.setRadius(radius);

            body.createFixture(shape, 0.0f);

            circle = new Circle(radius * m_to_px_scale, Color.DARKGREEN);
            circle.setCenterX(0);
            circle.setCenterY(0);
            circle.setTranslateX(conf.x * m_to_px_scale);
            circle.setTranslateY(GAMEPANE_HEIGHT - conf.y * m_to_px_scale);
            circle.setRotate(-conf.angle);

        } else {

            BodyDef bodydef = new BodyDef();
            bodydef.position.set(conf.x, conf.y);
            bodydef.type = BodyType.DYNAMIC;
            bodydef.angle = (float) Math.toRadians(conf.angle);
            body = world.createBody(bodydef);


            CircleShape shape = new CircleShape();
            shape.setRadius(radius);

            FixtureDef fixtureDef = new FixtureDef();
            fixtureDef.shape = shape;
            fixtureDef.density = conf.density;
            fixtureDef.friction = conf.friction;

            body.createFixture(fixtureDef);

            circle = new Circle(radius * m_to_px_scale, Color.DARKGREEN);
            circle.setCenterX(0);
            circle.setCenterY(0);
            circle.setTranslateX(conf.x * m_to_px_scale);
            circle.setTranslateY(GAMEPANE_HEIGHT-conf.y * m_to_px_scale);
            circle.setRotate(-conf.angle);
        }
    }

    @Override
    public Shape getShape() {
        return this.circle;
    }


    public float getRadius() {
        return radius;
    }
}
