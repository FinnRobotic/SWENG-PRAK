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


/**
 * Represents a circular rigid body in the physics simulation.
 * Wraps a JBox2D body and provides a corresponding JavaFX circle shape for rendering.
 */
public class Ball extends RigidBody {



    private Circle circle;

    private float radius;

    /**
     * Creates a static Ball with specified position, radius, and rotation.
     *
     * @param x         x-position in physics world units (meters)
     * @param y         y-position in physics world units (meters)
     * @param radius    radius of the ball in meters
     * @param gradAngle rotation angle in degrees
     * @param world     the physics world where the body is created
     */
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

    /**
     * Creates a dynamic Ball with specified position, radius, rotation, density, and friction.
     *
     * @param x         x-position in meters
     * @param y         y-position in meters
     * @param radius    radius in meters
     * @param gradAngle rotation angle in degrees
     * @param density   density of the material
     * @param friction  friction coefficient
     * @param world     the physics world where the body is created
     */
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

    /**
     * Creates a Ball from a BallConf configuration object.
     * If density or friction is -1, the Ball is created as static.
     * Otherwise, it is created as dynamic with specified physical properties.
     *
     * @param conf  configuration object containing position, radius, angle, density, and friction
     * @param world the physics world where the body is created
     */
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

    /**
     * Returns the JavaFX Circle shape representing this Ball.
     *
     * @return the Circle shape for rendering
     */
    @Override
    public Shape getShape() {
        return this.circle;
    }

    /**
     * Returns the radius of the Ball in meters.
     *
     * @return the radius in meters
     */
    public float getRadius() {
        return radius;
    }
}
