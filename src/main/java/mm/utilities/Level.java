package mm.utilities;

import mm.utilities.PhysicsObjects.RigidBody;
import org.jbox2d.common.Vec2;

import java.util.List;

public class Level {


    private String name;

    private Difficulty difficulty;

    private Vec2 gravity;

    private List<RigidBody> objects;
}
