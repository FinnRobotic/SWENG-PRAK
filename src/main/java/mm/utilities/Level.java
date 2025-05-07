package mm.utilities;

import mm.utilities.ObjectsConf.ObjectConf;
import mm.utilities.PhysicsObjects.RigidBody;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.World;

import java.util.List;

public class Level {

    private String name;

    private Difficulty difficulty;

    private Vec2 gravity;

    private List<ObjectConf> objects;


    public String getName() {
        return name;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public Vec2 getGravity() {
        return gravity;
    }
    public List<ObjectConf> getObjects() {
        return objects;
    }
}
