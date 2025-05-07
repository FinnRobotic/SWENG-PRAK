package mm.utilities.PhysicsObjects;

import javafx.scene.shape.Shape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;

public abstract class RigidBody {

    public Body body;


    public Vec2 getPosition() {
        return this.body.getPosition();
    }

    public float getX() {
        return this.body.getPosition().x;
    }

    public float getY() {
        return this.body.getPosition().y;
    }




    public float getAngle(){
        return this.body.getAngle();
    }


    public abstract Shape getShape();
}
