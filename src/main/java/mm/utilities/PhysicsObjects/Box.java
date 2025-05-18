package mm.utilities.PhysicsObjects;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import mm.utilities.ObjectsConf.BoxConf;
import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;
import org.jbox2d.dynamics.World;

import static mm.utilities.Makros.GAMEPANE_HEIGHT;
import static mm.utilities.Makros.m_to_px_scale;

/**
 * Represents a rectangular rigid body in the physics simulation.
 * This class wraps a JBox2D body and provides a corresponding JavaFX rectangle shape for rendering.
 */
public class Box extends RigidBody {

    private Rectangle rect;

    private float width;
    private float height;

    /**
     * Creates a static Box with specified position, rotation, width, and height.
     *
     * @param x         x-position in physics world units (meters)
     * @param y         y-position in physics world units (meters)
     * @param gradAngle rotation angle in degrees
     * @param width     width in meters
     * @param height    height in meters
     * @param world     the physics world where the body is created
     */
    public Box(float x, float y, float gradAngle, float width, float height, World world) {


        this.width = width;
        this.height = height;

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
        rect.setTranslateX(x * m_to_px_scale);
        rect.setTranslateY(GAMEPANE_HEIGHT- y * m_to_px_scale);
        rect.setRotate(-gradAngle);
    }

    /**
     * Creates a dynamic Box with specified position, rotation, size, density, and friction.
     *
     * @param x         x-position in meters
     * @param y         y-position in meters
     * @param gradAngle rotation angle in degrees
     * @param width     width in meters
     * @param height    height in meters
     * @param density   density of the material
     * @param friction  friction coefficient
     * @param world     the physics world where the body is created
     */
    public Box(float x, float y, float gradAngle, float width, float height,float density, float friction, World world) {

        this.width = width;
        this.height = height;


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
        rect.setTranslateY(GAMEPANE_HEIGHT - y * m_to_px_scale);
        rect.setRotate(-gradAngle);
    }

    /**
     * Creates a Box from a BoxConf configuration object.
     * If density or friction is -1, the Box is created as static.
     * Otherwise, it is created as dynamic with specified physical properties.
     *
     * @param conf  configuration object containing position, size, angle, density, and friction
     * @param world the physics world where the body is created
     */
    public Box(BoxConf conf, World world) {
        this.width = conf.width;
        this.height = conf.height;

        if(conf.density == -1 || conf.friction == -1){
            BodyDef bodydef = new BodyDef();
            bodydef.position.set(conf.x, conf.y);
            bodydef.type = BodyType.STATIC;
            bodydef.angle = (float) Math.toRadians(conf.angle);
            body = world.createBody(bodydef);

            PolygonShape shape = new PolygonShape();
            shape.setAsBox(width/2, height/2);

            body.createFixture(shape, 0.0f);

            rect = new Rectangle(width* m_to_px_scale, height * m_to_px_scale, Color.DODGERBLUE);
            rect.setX(-width* m_to_px_scale/ 2.0f);
            rect.setY(-height* m_to_px_scale/ 2.0f);
            rect.setTranslateX(conf.x * m_to_px_scale);
            rect.setTranslateY(GAMEPANE_HEIGHT- conf.y * m_to_px_scale);
            rect.setRotate(-conf.angle);

        } else {
            BodyDef bodydef = new BodyDef();
            bodydef.position.set(conf.x, conf.y);
            bodydef.type = BodyType.DYNAMIC;
            bodydef.angle = (float) Math.toRadians(conf.angle);
            body = world.createBody(bodydef);

            PolygonShape shape = new PolygonShape();
            shape.setAsBox(width/2, height/2);

            FixtureDef fixtureDef = new FixtureDef();
            fixtureDef.shape = shape;
            fixtureDef.density = conf.density;
            fixtureDef.friction = conf.friction;
            body.createFixture(fixtureDef);

            rect = new Rectangle(width* m_to_px_scale, height * m_to_px_scale, Color.DODGERBLUE);
            rect.setX(-width* m_to_px_scale/ 2.0f);
            rect.setY(-height* m_to_px_scale/ 2.0f);
            rect.setTranslateX(conf.x * m_to_px_scale);
            rect.setTranslateY(GAMEPANE_HEIGHT - conf.y * m_to_px_scale);
            rect.setRotate(-conf.angle);

        }


    }


    /**
     * Returns the JavaFX Rectangle shape representing this Box.
     *
     * @return the Rectangle shape for rendering
     */
    @Override
    public Rectangle getShape() {
        return this.rect;
    }
    /**
     * Returns the width of the Box in meters.
     *
     * @return the width in meters
     */
    public float getWidth() {
        return width;
    }

    /**
     * Returns the height of the Box in meters.
     *
     * @return the height in meters
     */
    public float getHeight() {
        return height;
    }

}
