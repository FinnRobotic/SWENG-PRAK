/**
 * The main module of the mm application.
 */
module mm {
    requires javafx.controls;
    requires transitive javafx.graphics;
    requires jbox2d.library;
    requires java.desktop;

    exports mm.gui;
}
