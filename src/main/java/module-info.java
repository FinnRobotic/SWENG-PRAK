/**
 * The main module of the mm application.
 */
module mm {
    requires javafx.controls;
    requires transitive javafx.graphics;
    requires jbox2d.library;
    requires java.desktop;
    requires com.google.gson;

    exports mm.gui;


    opens mm.utilities to com.google.gson;
    opens mm.utilities.PhysicsObjects to com.google.gson;
    exports mm.utilities.ObjectsConf;
    opens mm.utilities.ObjectsConf to com.google.gson;
    exports mm.utilities;

}
