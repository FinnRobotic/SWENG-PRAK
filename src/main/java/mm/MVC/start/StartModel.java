package mm.MVC.start;


import mm.MVC.util.Observable;
import mm.utilities.GameDef;

// To be implemented
public class StartModel extends Observable {

    private GameDef gameDef;

    private boolean showSettings = false;

    private boolean showLevelOverlay = false;

    public void toggleSettings() {
        showSettings = !showSettings;
        notifyObservers();
    }

    public void toggleLevelOverlay() {
        showLevelOverlay = !showLevelOverlay;
        notifyObservers();
    }

    public boolean getShowSettings() {
        return showSettings;
    }

    public StartModel() {
        this.gameDef = new GameDef();
    }

    public GameDef getGameDef() {
        return this.gameDef;
    }


}
