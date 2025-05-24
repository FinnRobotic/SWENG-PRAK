package mm.utilities.ObjectsConf;

import javafx.scene.layout.Pane;

/**
 * Configuration class representing a generic physics object.
 * Contains the position (x, y), rotation angle, and static state.
 */
public abstract class ObjectConf {

    /**
     * The x-coordinate position of the object (in meters).
     */
    public float x;

    /**
     * The y-coordinate position of the object (in meters).
     */
    public float y;

    /**
     * The rotation angle of the object in degrees.
     */
    public float angle;

    /**
     * Whether the object is static (does not move) or dynamic.
     */
    public Boolean isStatic;


    public abstract void scaleDownRelative(Pane pane);

    public abstract void scaleUpRelative(Pane pane);


}
