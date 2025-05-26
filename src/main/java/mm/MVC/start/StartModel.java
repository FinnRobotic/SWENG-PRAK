package mm.MVC.start;


import mm.MVC.util.Observable;
import mm.utilities.GameDef;
import mm.utilities.Level;
import mm.utilities.ObjectsConf.ObjectConf;

import static mm.utilities.JSON.JSONLevelIO.saveToFileWithDirectoryChooser;

/**
 * The StartModel class represents the data and state for the start screen of the application.
 * It extends Observable to notify registered observers about changes in its state.
 * <p>
 * This model manages flags for showing/hiding UI components such as the settings popup,
 * level selection overlay, and level builder. It also holds the current GameDef and
 * the Level being edited in the level builder.
 * </p>
 */
public class StartModel extends Observable {

    private GameDef gameDef;

    private boolean showSettings = false;

    private boolean showLevelOverlay = false;

    private boolean showBuilder = false;

    private Level builderLevel;

    /**
     * Constructs a new StartModel with a fresh GameDef.
     */
    public StartModel() {
        this.gameDef = new GameDef();
    }


    /**
     * Toggles the visibility state of the settings popup.
     * Notifies observers after changing the state.
     */
    public void toggleSettings() {
        showSettings = !showSettings;
        notifyObservers();
    }

    /**
     * Toggles the visibility state of the level selection overlay.
     * Notifies observers after changing the state.
     */
    public void toggleLevelOverlay() {
        showLevelOverlay = !showLevelOverlay;
        notifyObservers();
    }


    /**
     * Returns whether the settings popup should be shown.
     *
     * @return true if settings popup is visible, false otherwise
     */
    public boolean getShowSettings() {
        return showSettings;
    }

    /**
     * Returns whether the level selection overlay should be shown.
     *
     * @return true if level overlay is visible, false otherwise
     */
    public Boolean getShowLevelOverlay() {
        return showLevelOverlay;
    }

    /**
     * Returns whether the level builder window should be shown.
     *
     * @return true if builder window is visible, false otherwise
     */
    public Boolean getShowBuilder() {
        return showBuilder;

    }

    /**
     * Returns the current level being edited in the level builder.
     *
     * @return the builder Level instance
     */
    public Level getBuilderLevel() {
        return builderLevel;
    }


    /**
     * Returns the GameDef instance associated with this model.
     *
     * @return the GameDef instance
     */
    public GameDef getGameDef() {
        return this.gameDef;
    }


    /**
     * Starts a new level builder session by creating a new Level instance,
     * setting the builder visibility to true, and notifying observers.
     */
    public void startBuilder() {
        this.builderLevel = new Level();
        this.showBuilder = true;
        notifyObservers();
    }


    /**
     * Saves the current builder level to file using a directory chooser dialog,
     * closes the builder window, and notifies observers.
     * Prints debug information about objects in the level.
     *
     * @param view the StartView instance used to get the window for the directory chooser
     */
    public void saveAndCloseBuilder(StartView view) {
        this.showBuilder = false;

        try {
            System.out.println("Objects im Level: " + builderLevel.getObjects().size());

            for (ObjectConf obj : builderLevel.getObjects()) {
                System.out.println(obj.getClass().getSimpleName());
                obj.scaleDownRelative(view.getBuilder());
            }

            builderLevel.getStartPosition().x = builderLevel.getStartPosition().x * 100 / (float)view.getBuilder().getWidth();
            builderLevel.getStartPosition().y = builderLevel.getStartPosition().y * 100 / (float)view.getBuilder().getHeight();

            builderLevel.getWinCondition().winPosition.x = builderLevel.getWinCondition().winPosition.x  * 100 / (float)view.getBuilder().getWidth();
            builderLevel.getWinCondition().winPosition.y = builderLevel.getWinCondition().winPosition.y  * 100 / (float)view.getBuilder().getHeight();
            saveToFileWithDirectoryChooser(builderLevel, view.getRoot().getScene().getWindow());
        } catch(Exception e) {
            e.printStackTrace();
        }


        notifyObservers();
    }

}
