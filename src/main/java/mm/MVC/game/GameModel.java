package mm.MVC.game;


import mm.MVC.start.StartModel;
import mm.MVC.util.Observable;
import mm.utilities.GameDef;
import mm.utilities.Level;
import mm.utilities.PhysicsObjects.Ball;
import mm.utilities.PhysicsObjects.Box;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.*;

import static mm.utilities.Makros.*;

/**
 * The GameModel class represents the data and logic for the game simulation.
 *
 * It manages the physics world (using JBox2D), simulation timing, and game
 * definition including level data and physics bodies.
 *
 * Extends Observable to notify views/controllers of changes.
 */
public class GameModel extends Observable {

    private World world;

    private long lastUpdate;
    private float accumulator;
    private boolean simRunning;

    private GameDef gameDef;

    /**
     * Constructs a GameModel with the specified game definition and level.
     * Initializes the physics world with the level's gravity, sets up the
     * playground boundaries, and integrates the level's objects into the world.
     *
     * @param gamedef the GameDef containing physics parameters and bodies
     * @param level the Level containing gravity and objects to load
     */
    public GameModel(GameDef gamedef, Level level){
        this.gameDef = gamedef;

        this.lastUpdate = 0;
        this.accumulator = 0;
        this.simRunning = false;

        // Initialize JBox2D world with gravity from level
        this.world = new World(level.getGravity());

        initPlayground();

        // Integrate level objects into physics world and GameDef bodies list
        this.gameDef.integrateLevel(level, world);


    }

    /**
     * Returns the current GameDef instance.
     * @return the game definition data
     */
    public GameDef getGameDef() {
        return this.gameDef;
    }


    /**
     * Returns the timestamp of the last simulation update (nanoseconds).
     * @return last update timestamp
     */
    public long getLastUpdate() {
        return lastUpdate;
    }

    /**
     * Sets the timestamp of the last simulation update.
     * @param lastUpdate timestamp in nanoseconds
     */
    public void setLastUpdate(long lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    /**
     * Returns the current accumulated delta time for the simulation step loop.
     * @return accumulated delta time in seconds
     */
    public float getAccumulator() {
        return accumulator;
    }

    /**
     * Increases the accumulator by the specified delta time.
     * @param deltaTime time in seconds to add to the accumulator
     */
    public void incrAccumulator(float deltaTime) {
        this.accumulator+= deltaTime;
    }

    /**
     * Resets the accumulator to zero.
     */
    public void resetAccumulator() {
        this.accumulator = 0;
    }


    /**
     * Performs a single fixed-step simulation update on the physics world.
     * Steps the physics world using parameters from GameDef.
     * Notifies observers after the simulation step.
     */
    public void simStep() {

        world.step(1.0f/this.getGameDef().FPS,
                    this.getGameDef().velocityIterations,
                    this.getGameDef().positionIterations);
        notifyObservers();
    }

    /**
     * Returns whether the simulation is currently running.
     * @return true if simulation running, false otherwise
     */
    public boolean isSimRunning() {
        return this.simRunning;
    }

    /**
     * Toggles the simulation running state between running and stopped.
     * Notifies observers of the change.
     */
    public void toggleSimRunning() {
        this.simRunning = !this.simRunning;
        notifyObservers();
    }


    /**
     * Initializes the playground boundaries (ground, ceiling, left and right walls)
     * in the physics world as static boxes positioned just outside the visible game pane.
     * These boundaries prevent physics bodies from leaving the visible area.
     */
    private void initPlayground() {

        float gamePaneWidthMeter = GAMEPANE_WIDTH * px_to_m_scale;
        float gamePaneHeightMeter = GAMEPANE_HEIGHT * px_to_m_scale;


        Box ground = new Box(gamePaneWidthMeter /2, -1, 0,
                                    gamePaneWidthMeter, 2,this.world);

        Box ceiling = new Box(gamePaneWidthMeter /2, gamePaneHeightMeter + 1.495f, 0,
                                    gamePaneWidthMeter, 2,this.world);

        Box leftWall = new Box(-1, gamePaneHeightMeter / 2, 0, 2,
                                    gamePaneHeightMeter, this.world);

        Box rightWall = new Box(gamePaneWidthMeter + 1, gamePaneHeightMeter / 2, 0, 2,
                                    gamePaneHeightMeter, this.world);

    }

}
