package mm.utilities.ObjectsConf;

public class BallConf extends ObjectConf{

    public float radius;

    public float density;

    public float friction;




    public BallConf(float x, float y, float radius, float angle, float density, float friction) {

        this.x = x;
        this.y = y;
        this.radius = radius;
        this.angle = angle;
        this.density = density;
        this.friction = friction;
    }

    public BallConf(float x, float y, float radius, float angle) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.angle = angle;
        this.density = -1;
        this.friction = -1;
    }

    public BallConf(float x, float y, float radius) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.angle = 0;
        this.density = -1;
        this.friction = -1;
    }


}


