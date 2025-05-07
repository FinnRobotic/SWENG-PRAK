package mm.utilities;

import javafx.stage.Screen;

public class Makros {



    public static final int m_to_px_scale = 80;

    public static final float px_to_m_scale = 1.0f / m_to_px_scale;

    public static final float BorderMargin = 2;



    public static final float SIDEBAR_LEFT_WIDTH = 200;

    public static final float SIDEBAR_RIGHT_WIDTH = 200;

    public static final float BOTTOMBAR_HEIGHT = 150;


    public static final float GAMEPANE_HEIGHT = (float)Screen.getPrimary().getVisualBounds().getHeight() - BOTTOMBAR_HEIGHT;

    public static final float GAMEPANE_WIDTH = (float)Screen.getPrimary().getVisualBounds().getWidth() - SIDEBAR_LEFT_WIDTH - SIDEBAR_RIGHT_WIDTH;

    public static final float SETTINGS_WIDTH = 400;
    public static final float SETTINGS_HEIGHT = 200;

    public static final int FPS_MIN = 30;
    public static final int FPS_MAX = 240;
    public static final int FPS_DEFAULT = 120;

}
