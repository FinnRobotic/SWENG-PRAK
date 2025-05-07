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

public class GameDef {



    public GameDef() {
        this.FPS = 120;

        this.optimizeSimulation = false;

        this.velocityIterations = 8;

        this.positionIterations = 4;

        this.worldSizeX = 10;

        this.worldSizeY = 10;

    }
    
    public int FPS;

    public List<RigidBody> bodies = new ArrayList<>();

    public Vec2 gravity;

    public Difficulty difficulty;

    public boolean optimizeSimulation;

    public int velocityIterations;

    public int positionIterations;

    public int worldSizeX;

    public int worldSizeY;

    public void addBody(RigidBody body) {
        this.bodies.add(body);
    }

    public List<RigidBody> getBodies() {
        return this.bodies;
    }

    public void clearBodies() {
        this.bodies.clear();
    }


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

    public void integrateLevel(Level level, World world) {

        createBodies(level.getObjects(), world);

        difficulty = level.getDifficulty();

        gravity = level.getGravity();

    }


}
