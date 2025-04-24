package mm.blueprints;

import java.util.ArrayList;
import java.util.List;

public class GameDef {



    public GameDef() {
        this.FPS = 60;

        this.difficulty = Difficulty.MEDIUM;


    }
    
    private int FPS;

    public void setFPS(int fps) {
        this.FPS = fps;
    }

    public int getFPS() {
        return this.FPS;
    }


    private List<RigidBody> bodies = new ArrayList<RigidBody>();

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

    private Difficulty difficulty;

    public Difficulty getDifficulty() {
        return this.difficulty;
    }
    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }


}
