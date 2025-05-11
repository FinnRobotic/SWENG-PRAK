package mm.utilities;

import mm.utilities.ObjectsConf.ObjectConf;
import mm.utilities.PhysicsObjects.RigidBody;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.World;

import java.util.ArrayList;
import java.util.List;

public class Level {

    private String name;

    private Difficulty difficulty;

    private Vec2 gravity;

    private List<ObjectConf> objects;

    public Level() {
        name = "";
        difficulty = Difficulty.EASY;
        gravity = new Vec2(0, 0);
        objects = new ArrayList<>();
    }

    public void addObject(ObjectConf object) {
        objects.add(object);
    }

    public String getName() {
        return name;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public Vec2 getGravity() {
        return gravity;
    }

    public void setGravityX(float gravityX) {
        this.gravity.x = gravityX;
    }

    public void setGravityY(float gravityY) {
        this.gravity.y = gravityY;
    }

    public void setName(String name) {
        this.name = name;
    }
    public List<ObjectConf> getObjects() {
        return objects;
    }
}
