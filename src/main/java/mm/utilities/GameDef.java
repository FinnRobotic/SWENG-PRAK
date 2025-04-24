package mm.utilities;

import mm.utilities.PhysicsObjects.RigidBody;
import org.jbox2d.common.Vec2;

import java.util.ArrayList;
import java.util.List;

public class GameDef {



    public GameDef() {
        this.FPS = 60;

        this.difficulty = Difficulty.MEDIUM;

        this.gravity = new Vec2(0.0f,-9.81f);

        this.optimizeSimulation = false;

        this.velocityIterations = 6;

        this.positionIterations = 2;

    }
    
    public int FPS;

    public List<RigidBody> bodies = new ArrayList<>();

    public Vec2 gravity;

    public Difficulty difficulty;

    public boolean optimizeSimulation;

    public int velocityIterations;

    public int positionIterations;

    public static final int m_to_px_scale = 50;

    public void addBody(RigidBody body) {
        this.bodies.add(body);
    }

    public List<RigidBody> getBodies() {
        return this.bodies;
    }

    public void clearBodies() {
        this.bodies.clear();
    }

    public enum Difficulty {
        EASY,
        MEDIUM,
        HARD;

        @Override
        public String toString() {
            switch (this) {
                case EASY: return "Easy";
                case MEDIUM: return "Medium";
                case HARD: return "Hard";
                default: return super.toString();
            }
        }
    }




}
