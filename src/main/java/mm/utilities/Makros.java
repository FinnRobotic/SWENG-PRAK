package mm.utilities;

public class Makros {


    public enum Kinematic {
        STATIC,
        DYNAMIC;

        @Override
        public String toString() {
            switch (this) {
                case STATIC: return "Static";
                case DYNAMIC: return "Dynamic";
                default: return super.toString();
            }
        }
    }
}
