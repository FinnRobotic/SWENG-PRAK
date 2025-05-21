package mm.MVC.start;


import javafx.event.Event;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import mm.gui.ViewManager;
import mm.utilities.GameDef;
import mm.utilities.Level;


import javax.swing.text.View;
import java.io.IOException;

import static mm.utilities.HelperUI.addDraggableResizableRotatableBox;
import static mm.utilities.JSON.JSONLevelIO.loadLevelFromDirectory;
import static mm.utilities.JSON.JSONLevelIO.loadLevelFromFile;

/**
 * Controller class for the Start view.
 *
 * Manages user interactions with the StartView UI components,
 * processes input events, and coordinates view changes via ViewManager.
 * Handles toggling UI elements, loading levels, starting the builder,
 * and processing inputs from builder controls.
 */
public class StartController {


    /**
     * Sets up event handlers and binds the StartView components
     * to actions, connecting UI interaction to model updates and
     * navigation logic via the ViewManager.
     *
     * @param view the StartView instance to control
     * @param viewManager the ViewManager to switch between views
     */
    public void setView(StartView view, ViewManager viewManager) {

       setStart(view, viewManager);

       setLevelBuilder(view, viewManager);

       setLevelOverlay(view, viewManager);


       setSettings(view, viewManager);



    }

    /**
     * Sets up actions for the main start menu buttons (start, settings, builder, etc.).
     *
     * @param view        The StartView.
     * @param viewManager The ViewManager.
     */
    private void setStart(StartView view, ViewManager viewManager) {
        view.getStartButton().setOnAction(e -> {
            view.getModel().toggleLevelOverlay();
        });

        view.getSettingsButton().setOnAction(e -> {
            view.getModel().toggleSettings();
        });

        view.getLevelBuilderBTN().setOnAction(e -> {
            view.getModel().startBuilder();
        });

    }


    private void setLevelOverlay(StartView view, ViewManager viewManager) {


        view.getMediumLevelButton().setOnAction(e -> {
            loadMediumLevel(view, viewManager);
        });

        view.getCustomLevelButton().setOnAction(e -> {
            loadCustomLevel(view, viewManager);
        });

        view.getCancelButton().setOnAction(e -> {
            view.getModel().toggleLevelOverlay();
        });
    }



    /**
     * Loads a predefined medium difficulty level from file and opens the game view.
     *
     * @param view        The StartView.
     * @param viewManager The ViewManager.
     */
    public void loadMediumLevel(StartView view,ViewManager viewManager) {
        String filePath = "src/main/resources/level/medium.json";
        try {
            Level level = loadLevelFromFile(filePath);

            // Ausgabe der geladenen Level-Daten
            System.out.println("Level Name: " + level.getName());
            System.out.println("Difficulty: " + level.getDifficulty());
            viewManager.showGameView(level,view.getModel().getGameDef());

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Fehler beim Laden des Levels.");
        }
    }
    /**
     * Opens a file chooser for the user to load a custom level and switches to the game view.
     *
     * @param view        The StartView.
     * @param viewManager The ViewManager.
     */
    public void loadCustomLevel(StartView view, ViewManager viewManager) {
        try {
            Level level = loadLevelFromDirectory(view.getRoot().getScene().getWindow());
            System.out.println("Level Name: " + level.getName());
            System.out.println("Difficulty: " + level.getDifficulty());
            viewManager.showGameView(level,view.getModel().getGameDef());

        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }


    private void setSettings(StartView view, ViewManager viewManager) {
        // Save settings from UI controls and toggle settings popup off
        view.getSaveSettingsButton().setOnAction(e -> {
            GameDef gameDef = view.getModel().getGameDef();
            gameDef.difficulty = view.getDifficultyBox().getValue();
            gameDef.FPS = (int) view.getFpsSlider().getValue();

            System.out.println("Settings saved: Difficulty = " + gameDef.difficulty + ", FPS = " + gameDef.FPS);
            view.getModel().toggleSettings();
        });

    }



    /**
     * Sets up controls for the level builder UI (placing objects, exiting builder mode, etc.).
     *
     * @param view        The StartView.
     * @param viewManager The ViewManager.
     */
    private void setLevelBuilder(StartView view, ViewManager viewManager) {
        view.getBuilderExitBTN().setOnAction(e -> {
            view.getModel().saveAndCloseBuilder(view);
            view.resetBuilderUI();
        });
        view.getBuilderStage().getScene().setOnKeyPressed(e -> {checkBoxInputs(view, e);});
        view.getPlaceBox().setOnAction(e -> {
            view.getModel().getBuilderLevel().addObject(addDraggableResizableRotatableBox(view.getBuilder()));
        });
    }

    /**
     * Checks and processes user inputs in the level builder settings fields (gravity and name).
     *
     * @param view The StartView.
     * @param e    The KeyEvent received from input.
     */
    public void checkBoxInputs(StartView view, KeyEvent e) {

        Boolean gravityXBoxSelected = view.getGravityXInput().isFocused();
        Boolean gravityYBoxSelected = view.getGravityYInput().isFocused();
        Boolean textFieldSelected = view.getNameInput().isFocused();

        if (e.getCode() == KeyCode.ENTER) {
            if (gravityXBoxSelected) {
                try {
                    String inputText = view.getGravityXInput().getText().replace(",", ".");
                    float gravityX = Float.parseFloat(inputText);
                    view.getModel().getBuilderLevel().setGravityX(gravityX);
                    System.out.println("GravityX selected: " + gravityX);
                } catch (NumberFormatException ex) {
                    System.out.println("Ungültige Eingabe: Keine gültige Kommazahl");
                    System.out.println("GravityBox X");
                }
            }

            if (gravityYBoxSelected) {
                try {
                    String inputText = view.getGravityYInput().getText().replace(",", ".");
                    float gravityY = Float.parseFloat(inputText);
                    view.getModel().getBuilderLevel().setGravityY(gravityY);
                    System.out.println("GravityY selected: " + gravityY);
                } catch (NumberFormatException ex) {
                    System.out.println("Ungültige Eingabe: Keine gültige Kommazahl");
                    System.out.println("GravityBox Y");
                }
            }
            if (textFieldSelected) {

                view.getModel().getBuilderLevel().setName(view.getNameInput().getText());
                System.out.println("Name selected: " + view.getNameInput().getText());
            }
        }

    }

}





