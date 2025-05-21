package mm.utilities;

import mm.utilities.ObjectsConf.BallConf;
import mm.utilities.ObjectsConf.BoxConf;
import mm.utilities.ObjectsConf.ObjectConf;
import mm.utilities.PhysicsObjects.Ball;
import mm.utilities.PhysicsObjects.Box;
import mm.utilities.PhysicsObjects.RigidBody;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.World;

import java.util.ArrayList;
import java.util.List;

/**
 * Defines the core game configuration including physics simulation parameters,
 * current level details, and management of physical bodies in the simulation.
 */
public class GameDef {


    /**
     * Constructs a default GameDef instance with default FPS, physics iterations,
     * and world size.
     */
    public GameDef() {
        this.FPS = 120;

        this.velocityIterations = 8;

        this.positionIterations = 4;

        this.worldSizeX = 10;

        this.worldSizeY = 10;

    }

    public Level currentLevel;
    
    public int FPS;

    public List<RigidBody> bodies = new ArrayList<>();

    public Vec2 gravity;

    public Difficulty difficulty;

    public int velocityIterations;

    public int positionIterations;

    public int worldSizeX;

    public int worldSizeY;

    /**
     * Adds a rigid body to the simulation.
     *
     * @param body the RigidBody to add
     */
    public void addBody(RigidBody body) {
        this.bodies.add(body);
    }

    /**
     * Returns the list of all rigid bodies in the simulation.
     *
     * @return list of rigid bodies
     */
    public List<RigidBody> getBodies() {
        return this.bodies;
    }

    /**
     * Clears all rigid bodies from the simulation.
     */
    public void clearBodies() {
        this.bodies.clear();
    }

    /**
     * Creates physical bodies in the Box2D world based on the provided object configurations.
     * Currently supports BallConf and BoxConf types.
     *
     * @param objects list of object configurations to create bodies from
     * @param world the physics World where bodies will be created
     */
    private void createBodies(List<ObjectConf> objects, World world) {
        for (ObjectConf object : objects) {
            switch (object.getClass().getSimpleName()) {
                case "BallConf":
                    // Umwandeln von ObjectConf zu BallConf und Erstellen eines Balls
                    BallConf ballConf = (BallConf) object;
                    Ball ball = new Ball(ballConf, world);
                    System.out.println("Dies ist ein Ball: x = " + ballConf.x + ", y = " + ballConf.y + ", radius = " + ballConf.radius);
                    addBody(ball);

                    break;

                case "BoxConf":
                    // Umwandeln von ObjectConf zu BoxConf und Erstellen einer Box
                    BoxConf boxConf = (BoxConf) object;
                    Box box = new Box(boxConf, world);
                    System.out.println("Dies ist eine Box: x = " + boxConf.x + ", y = " + boxConf.y + ", width = " + boxConf.width + ", height = " + boxConf.height);
                    addBody(box);

                    break;

                default:
                    // Falls der Typ unbekannt ist
                    System.out.println("Unbekannter Typ: " + object.getClass().getSimpleName());
                    break;
            }
        }
    }

    /**
     * Integrates a new level into the game definition, creating bodies
     * in the specified physics world and updating gravity and difficulty.
     *
     * @param level the Level to integrate
     * @param world the physics World where bodies should be created
     */
    public void integrateLevel(Level level, World world) {

        this.currentLevel = level;

        createBodies(level.getObjects(), world);

        difficulty = level.getDifficulty();

        gravity = level.getGravity();

    }

    /**
     * Resets the current level by clearing all bodies and recreating them
     * in the given physics world.
     *
     * @param world the physics World where bodies should be recreated
     */
    public void resetLevel(World world) {
        clearBodies();
        createBodies(this.currentLevel.getObjects(), world);

    }


}
