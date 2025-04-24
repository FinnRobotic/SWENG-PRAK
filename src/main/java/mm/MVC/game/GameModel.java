package mm.MVC.game;


import mm.MVC.Model;
import mm.utilities.PhysicsObjects.Box;
import mm.utilities.PhysicsObjects.RigidBody;
import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.*;


public class GameModel extends Model {

    private World world;

    public GameModel(Model StartModel){
        this.setGameDef(StartModel.getGameDef());

        Vec2 gravity = this.getGameDef().gravity;
        this.world = new World(gravity);

        Box ground = new Box(0.0f, -10.0f, 100, 20, this.world);

        // 3. Einen fallenden Kasten
        Box box = new Box(0.0f, 4.0f, 2, 2, 1.0f,0.3f,this.world);
        this.getGameDef().addBody(box);

    }

    public void simStep() {

        world.step(1.0f/this.getGameDef().FPS,
                    this.getGameDef().velocityIterations,
                    this.getGameDef().positionIterations);


    }


}
