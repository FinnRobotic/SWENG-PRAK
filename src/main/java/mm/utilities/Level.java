package mm.utilities;

import mm.utilities.ObjectsConf.ObjectConf;
import org.jbox2d.common.Vec2;

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

    private Vec2 startPosition;

    private int numSmallBox = 3;

    private int numMediumBox = 3;

    private int numBigBox = 3;

    private int  numSmallBall = 3;

    private int  numMediumBall = 3;

    private int  numBigBall = 3;

    private WinCondition winCondition;

    private float maxTime = 20;

    /**
     * Constructs a new Level with default settings:
     * empty name, EASY difficulty, zero gravity, and an empty object list.
     */
    public Level() {
        name = "";
        difficulty = Difficulty.EASY;
        gravity = new Vec2(0, 0);
        objects = new ArrayList<>();
        startPosition = new Vec2(-1, -1);
        numSmallBox = 3;
        numMediumBox = 3;
        numBigBox = 3;
        numSmallBall = 3;
        numMediumBall = 3;
        numBigBall = 3;

        winCondition = new WinCondition();
        winCondition.winPosition = new Vec2(-1, -1);
        winCondition.height = 0;
        winCondition.width = 0;
    }

    /**
     * Adds a new object configuration to the level.
     *
     * @param object the object configuration to add
     */
    public void addObject(ObjectConf object) {
        objects.add(object);
    }

    public List<ObjectConf> getObjects() {
        return objects;
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

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
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


    public void setGravity(Vec2 gravity) {
        this.gravity = gravity;
    }


    public void setStartPosition(Vec2 startPosition) {
        this.startPosition = startPosition;
    }

    public Boolean StartPositionNotSet() {
        return startPosition.x == -1 || startPosition.y == -1;
    }
    /**
     * Returns the list of object configurations contained in the level.
     *
     * @return list of objects
     */


    public Vec2 getStartPosition() {
        return startPosition;
    }

    public Boolean winConditionNotSet() {
        return winCondition.winPosition.x == -1 || winCondition.winPosition.y == -1;
    }

    public void setWinCondition(WinCondition win) {
        this.winCondition = win;
    }

    public WinCondition getWinCondition() {
        return winCondition;
    }

    public float getMaxTime() {
        return maxTime;
    }

    public void setMaxTime(float maxTime) {
        this.maxTime = maxTime;
    }



}
