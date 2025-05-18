package mm.utilities.ObjectsConf;


/**
 * Configuration class representing a rectangular box object in the physics simulation.
 * Extends the generic ObjectConf with width, height, density, and friction properties.
 */
public class BoxConf extends ObjectConf{


    /**
     * Width of the box (in meters).
     */
    public float width;

    /**
     * Height of the box (in meters).
     */
    public float height;

    /**
     * Density of the box material.
     * Use -1 to indicate a static object (no density).
     */
    public float density;

    /**
     * Friction coefficient of the box surface.
     * Use -1 to indicate static state.
     */
    public float friction;

    /**
     * Constructs a fully specified BoxConf with position, size, angle, density, and friction.
     *
     * @param x        X position of the box center (meters).
     * @param y        Y position of the box center (meters).
     * @param width    Width of the box (meters).
     * @param height   Height of the box (meters).
     * @param angle    Rotation angle in degrees.
     * @param density  Density of the box.
     * @param friction Friction coefficient of the box.
     */
    public BoxConf(float x, float y, float width, float height, float angle, float density, float friction) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.angle = angle;
        this.density = density;
        this.friction = friction;
    }

    /**
     * Constructs a BoxConf with position, size, and angle.
     * Density and friction are set to -1 by default (static).
     *
     * @param x      X position of the box center (meters).
     * @param y      Y position of the box center (meters).
     * @param width  Width of the box (meters).
     * @param height Height of the box (meters).
     * @param angle  Rotation angle in degrees.
     */
    public BoxConf(float x, float y, float width, float height, float angle) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.angle = angle;
        this.density = -1;
        this.friction = -1;
    }

    /**
     * Constructs a BoxConf with position and size.
     * Angle is set to 0, density and friction to -1 (static).
     *
     * @param x      X position of the box center (meters).
     * @param y      Y position of the box center (meters).
     * @param width  Width of the box (meters).
     * @param height Height of the box (meters).
     */
    public BoxConf(float x, float y, float width, float height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.angle = 0;
        this.density = -1;
        this.friction = -1;
    }

    /**
     * Default constructor initializing all values to zero or -1.
     * Useful for manual property setting after construction.
     */
    public BoxConf(){
        this.x = 0;
        this.y = 0;
        this.width = 0;
        this.height = 0;
        this.angle = 0;
        this.density = -1;
        this.friction = -1;

    }
}
