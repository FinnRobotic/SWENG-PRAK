package mm.MVC.game;


import mm.MVC.start.StartModel;
import mm.MVC.util.Observable;
import mm.utilities.GameDef;
import mm.utilities.PhysicsObjects.Ball;
import mm.utilities.PhysicsObjects.Box;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.*;

import static mm.utilities.Makros.*;

public class GameModel extends Observable {

    private World world;

    private long lastUpdate;
    private float accumulator;
    private boolean simRunning;

    private GameDef gameDef;

    public GameModel(GameDef gamedef){
        this.gameDef = gamedef;

        this.lastUpdate = 0;
        this.accumulator = 0;
        this.simRunning = false;

        Vec2 gravity = this.getGameDef().gravity;
        this.world = new World(gravity);
        initPlayground();
        // 3. Einen fallenden Kasten
        Box box = new Box(5.5f, 0.25f, 0, 0.5f, 0.5f, 1.0f,50,this.world);
        this.getGameDef().addBody(box);

        Box plank = new Box(5.5f, 4f, 45, 4f, 0.25f, 1.0f,0.5f,this.world);
        this.getGameDef().addBody(plank);

        Box smallBox = new Box(6.5f, 7f, 0, 0.5f, 0.5f, 1.0f,0.5f,this.world);
        this.getGameDef().addBody(smallBox);


        Ball ball = new Ball(5f,8,1.0f,0,1,10f, world);
        this.getGameDef().addBody(ball);


    }

    public GameDef getGameDef() {
        return this.gameDef;
    }



    public long getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(long lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public float getAccumulator() {
        return accumulator;
    }

    public void incrAccumulator(float deltaTime) {
        this.accumulator+= deltaTime;
    }

    public void resetAccumulator() {
        this.accumulator = 0;
    }

    public void simStep() {

        world.step(1.0f/this.getGameDef().FPS,
                    this.getGameDef().velocityIterations,
                    this.getGameDef().positionIterations);
        notifyObservers();
    }

    public boolean isSimRunning() {
        return this.simRunning;
    }

    public void toggleSimRunning() {
        this.simRunning = !this.simRunning;
        notifyObservers();
    }


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
