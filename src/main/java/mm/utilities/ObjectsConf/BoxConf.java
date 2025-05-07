package mm.utilities.ObjectsConf;

public class BoxConf extends ObjectConf{

    public float x;

    public float y;

    public float width;

    public float height;

    public float angle;

    public float density;

    public float friction;

    public BoxConf(float x, float y, float width, float height, float angle, float density, float friction) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.angle = angle;
        this.density = density;
        this.friction = friction;
    }

    public BoxConf(float x, float y, float width, float height, float angle) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.angle = angle;
        this.density = -1;
        this.friction = -1;
    }
    public BoxConf(float x, float y, float width, float height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.angle = 0;
        this.density = -1;
        this.friction = -1;
    }
}
