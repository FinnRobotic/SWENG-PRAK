package mm.utilities;

import javafx.stage.Screen;


/**
 * Holds constant values for scaling factors and UI dimensions used throughout the application.
 */
public class Makros {


    /** Scale factor to convert meters to pixels */
    public static final int m_to_px_scale = 80;

    /** Scale factor to convert pixels to meters */
    public static final float px_to_m_scale = 1.0f / m_to_px_scale;

    /** Width of the left sidebar in pixels */
    public static final float SIDEBAR_LEFT_WIDTH = 200;

    /** Width of the right sidebar in pixels */
    public static final float SIDEBAR_RIGHT_WIDTH = 200;

    /** Height of the bottom bar in pixels */
    public static final float BOTTOMBAR_HEIGHT = 150;

    /** Height of the main game pane, calculated based on screen height minus bottom bar */
    public static final float GAMEPANE_HEIGHT = 930;

    /** Width of the main game pane, calculated based on screen width minus sidebars */
    public static final float GAMEPANE_WIDTH = (float)Screen.getPrimary().getVisualBounds().getWidth() - SIDEBAR_LEFT_WIDTH - SIDEBAR_RIGHT_WIDTH;


    /** Width of the settings window */
    public static final float SETTINGS_WIDTH = 400;
    /** Height of the settings window */
    public static final float SETTINGS_HEIGHT = 200;



    /** Minimum frames per second */
    public static final int FPS_MIN = 30;

    /** Maximum frames per second */
    public static final int FPS_MAX = 240;

    /** Default frames per second */
    public static final int FPS_DEFAULT = 120;



    public static final int RADIUS_GAMEBALL_PX = 15;

}
