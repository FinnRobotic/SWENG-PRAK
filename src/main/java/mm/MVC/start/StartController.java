package mm.MVC.start;


import javafx.event.Event;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import mm.gui.ViewManager;
import mm.utilities.GameDef;
import mm.utilities.Level;


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

        // Toggle level selection overlay on start and cancel button presses
        view.getStartButton().setOnAction(actionEvent -> view.getModel().toggleLevelOverlay());
        view.getCancelButton().setOnAction(actionEvent -> view.getModel().toggleLevelOverlay());

        // Toggle settings popup visibility
        view.getSettingsButton().setOnAction(e -> view.getModel().toggleSettings());

        // Start the level builder
        view.getLevelBuilderBTN().setOnAction(e -> view.getModel().startBuilder());

        // Save and close builder on exit button press, reset UI
        view.getBuilderExitBTN().setOnAction(e -> {
            view.getModel().saveAndCloseBuilder(view);
            view.resetBuilderUI();
        });

        // Save settings from UI controls and toggle settings popup off
        view.getSaveSettingsButton().setOnAction(e -> {
            GameDef gameDef = view.getModel().getGameDef();
            gameDef.difficulty = view.getDifficultyBox().getValue();
            gameDef.FPS = (int) view.getFpsSlider().getValue();

            System.out.println("Settings saved: Difficulty = " + gameDef.difficulty + ", FPS = " + gameDef.FPS);
            view.getModel().toggleSettings();
        });


        // Load medium difficulty level from file and show game view
        view.getMediumLevelButton().setOnAction(e -> loadMediumLevel(view, viewManager));


        // Load custom level from user-selected directory and start game
        view.getCustomLevelButton().setOnAction(e -> {
            try {
                Level level = loadLevelFromDirectory(view.getRoot().getScene().getWindow());
                System.out.println("Level Name: " + level.getName());
                System.out.println("Difficulty: " + level.getDifficulty());
                viewManager.showGameView(level,view.getModel().getGameDef());

            } catch(Exception ex) {
                ex.printStackTrace();
            }
        });

        // Listen for key presses in builder stage to process gravity and name input
        view.getBuilderStage().getScene().setOnKeyPressed(e -> {checkBoxInputs(view, e);});

        // Add a new draggable, resizable, rotatable box to the builder level on button press
        view.getPlaceBox().setOnAction(e -> {
            view.getModel().getBuilderLevel().addObject(addDraggableResizableRotatableBox(view.getBuilder()));
        });
    }

    /**
     * Loads a medium difficulty level from a fixed file path,
     * prints level details, and starts the game view.
     *
     * @param view the StartView instance for UI context
     * @param viewManager the ViewManager to switch to the game view
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
     * Processes input fields for gravity values and level name when Enter key is pressed.
     * Parses gravity X and Y input as floats and updates the builder level accordingly.
     * Updates level name text if that input is focused.
     *
     * @param view the StartView instance containing the input fields
     * @param e the KeyEvent triggered by user input
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





