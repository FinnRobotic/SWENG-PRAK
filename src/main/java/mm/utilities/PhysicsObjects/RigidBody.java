package mm.utilities.PhysicsObjects;

import javafx.scene.shape.Shape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;


/**
 * Abstract base class representing a physical rigid body in the physics simulation.
 * It wraps a JBox2D {@link Body} and provides methods to access its position and orientation.
 */
public abstract class RigidBody {

    /** The underlying JBox2D physics body */
    public Body body;

    /**
     * Returns the current position of the rigid body in the physics world.
     *
     * @return the position as a 2D vector (Vec2)
     */
    public Vec2 getPosition() {
        return this.body.getPosition();
    }

    /**
     * Returns the x-coordinate of the rigid body's position.
     *
     * @return the x position in physics world units
     */
    public float getX() {
        return this.body.getPosition().x;
    }

    /**
     * Returns the y-coordinate of the rigid body's position.
     *
     * @return the y position in physics world units
     */
    public float getY() {
        return this.body.getPosition().y;
    }

    /**
     * Returns the current rotation angle of the rigid body in radians.
     *
     * @return the angle in radians
     */
    public float getAngle(){
        return this.body.getAngle();
    }

    /**
     * Returns the JavaFX {@link Shape} used to visually represent this rigid body.
     *
     * @return the shape representing the rigid body
     */
    public abstract Shape getShape();
}
