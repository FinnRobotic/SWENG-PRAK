package mm.utilities;

import mm.utilities.ObjectsConf.ObjectConf;
import mm.utilities.PhysicsObjects.RigidBody;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.World;

import java.util.ArrayList;
import java.util.List;


/**
 * Represents a game level, holding its name, difficulty, gravity settings,
 * and a list of configured objects within the level.
 */
public class Level {

    /** The name of the level */
    private String name;

    /** The difficulty of the level */
    private Difficulty difficulty;

    /** Gravity vector (x and y components) */
    private Vec2 gravity;

    /** List of object configurations included in the level */
    private List<ObjectConf> objects;


    /**
     * Constructs a new Level with default settings:
     * empty name, EASY difficulty, zero gravity, and an empty object list.
     */
    public Level() {
        name = "";
        difficulty = Difficulty.EASY;
        gravity = new Vec2(0, 0);
        objects = new ArrayList<>();
    }

    /**
     * Adds a new object configuration to the level.
     *
     * @param object the object configuration to add
     */
    public void addObject(ObjectConf object) {
        objects.add(object);
    }

    /**
     * Returns the name of the level.
     *
     * @return the level name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the level.
     *
     * @param name the new level name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the difficulty of the level.
     *
     * @return the level difficulty
     */
    public Difficulty getDifficulty() {
        return difficulty;
    }

    /**
     * Returns the gravity vector of the level.
     *
     * @return the gravity as a 2D vector
     */
    public Vec2 getGravity() {
        return gravity;
    }

    /**
     * Sets the gravity x-component.
     *
     * @param gravityX the x value of gravity
     */
    public void setGravityX(float gravityX) {
        this.gravity.x = gravityX;
    }

    /**
     * Sets the gravity y-component.
     *
     * @param gravityY the y value of gravity
     */
    public void setGravityY(float gravityY) {
        this.gravity.y = gravityY;
    }

    /**
     * Returns the list of object configurations contained in the level.
     *
     * @return list of objects
     */
    public List<ObjectConf> getObjects() {
        return objects;
    }
}
