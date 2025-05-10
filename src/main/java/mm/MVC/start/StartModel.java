package mm.MVC.start;


import mm.MVC.util.Observable;
import mm.utilities.GameDef;
import mm.utilities.Level;

import static mm.utilities.JSON.JSONLevelIO.saveToFileWithDirectoryChooser;


public class StartModel extends Observable {

    private GameDef gameDef;

    private boolean showSettings = false;

    private boolean showLevelOverlay = false;

    private boolean showBuilder = false;

    private Level builderLevel;

    public void toggleSettings() {
        showSettings = !showSettings;
        notifyObservers();
    }

    public void toggleLevelOverlay() {
        showLevelOverlay = !showLevelOverlay;
        notifyObservers();
    }

    public void toggleBuilder() {
        showBuilder = !showBuilder;
        notifyObservers();
    }

    public boolean getShowSettings() {
        return showSettings;
    }

    public Boolean getShowLevelOverlay() {
        return showLevelOverlay;
    }

    public Boolean getShowBuilder() {
        return showBuilder;

    }

    public Level getBuilderLevel() {
        return builderLevel;
    }


    public StartModel() {
        this.gameDef = new GameDef();
    }

    public GameDef getGameDef() {
        return this.gameDef;
    }



    public void startBuilder() {
        this.builderLevel = new Level();
        this.showBuilder = true;
        notifyObservers();
    }

    public void saveAndCloseBuilder(StartView view) {
        this.showBuilder = false;

        try {
            saveToFileWithDirectoryChooser(builderLevel, view.getRoot().getScene().getWindow());
        } catch(Exception e) {
            e.printStackTrace();
        }


        notifyObservers();
    }

}
