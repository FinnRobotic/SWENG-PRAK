package mm.utilities.ObjectsConf;


import javafx.scene.layout.Pane;

/**
 * Configuration class representing a ball object in the physics simulation.
 * Extends the generic ObjectConf with radius, density, and friction properties.
 */
public class BallConf extends ObjectConf{

    /**
     * Radius of the ball (in meters).
     */
    public float radius;

    /**
     * Density of the ball material.
     * Use -1 to indicate a static object (no density).
     */
    public float density;

    /**
     * Friction coefficient of the ball surface.
     * Use -1 to indicate default or static.
     */
    public float friction;



    /**
     * Constructs a fully specified BallConf with position, radius, angle, density, and friction.
     *
     * @param x        X position of the ball center (meters).
     * @param y        Y position of the ball center (meters).
     * @param radius   Radius of the ball (meters).
     * @param angle    Rotation angle in degrees.
     * @param density  Density of the ball.
     * @param friction Friction coefficient of the ball.
     */
    public BallConf(float x, float y, float radius, float angle, float density, float friction) {

        this.x = x;
        this.y = y;
        this.radius = radius;
        this.angle = angle;
        this.density = density;
        this.friction = friction;
    }

    /**
     * Constructs a BallConf with position, radius, and angle.
     * Density and friction are set to -1 by default (static).
     *
     * @param x      X position of the ball center (meters).
     * @param y      Y position of the ball center (meters).
     * @param radius Radius of the ball (meters).
     * @param angle  Rotation angle in degrees.
     */
    public BallConf(float x, float y, float radius, float angle) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.angle = angle;
        this.density = -1;
        this.friction = -1;
    }

    /**
     * Constructs a BallConf with position and radius.
     * Angle is set to 0, density and friction to -1 (static).
     *
     * @param x      X position of the ball center (meters).
     * @param y      Y position of the ball center (meters).
     * @param radius Radius of the ball (meters).
     */
    public BallConf(float x, float y, float radius) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.angle = 0;
        this.density = -1;
        this.friction = -1;
    }


    @Override
    public void scaleDownRelative(Pane pane) {
        this.x = this.x * 100 / (float)pane.getWidth();
        this.y = this.y * 100 / (float)pane.getHeight();
        this.radius = this.radius * 100 / (float)pane.getWidth();
    }

    @Override
    public void scaleUpRelative(Pane pane) {
        System.out.println("Before: " + this.x + ", " + this.y + ", " + this.radius);
        this.x = this.x * (float)pane.getWidth() / 100;
        this.y = this.y * (float)pane.getHeight() / 100;
        this.radius = this.radius * (float)pane.getWidth() / 100;
        System.out.println("After: " + this.x + ", " + this.y + ", " + this.radius);
    }

}


