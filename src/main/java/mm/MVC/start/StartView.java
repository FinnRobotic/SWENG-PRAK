package mm.MVC.start;


import javafx.scene.control.*;
import javafx.scene.layout.*;
import mm.MVC.View;
import javafx.stage.Stage;
import mm.MVC.start.elementsUI.LevelBuilder;
import mm.MVC.start.elementsUI.LevelOverlay;
import mm.MVC.start.elementsUI.Settings;
import mm.MVC.start.elementsUI.StartScreen;
import mm.utilities.Difficulty;

/**
 * The StartView class represents the start screen of the application.
 * It provides the main menu with options to start the game, open the level builder,
 * and change settings. It also manages popups and overlays for level selection
 * and the level builder UI.
 * <p>
 * This class extends the abstract View class and implements the Observer interface
 * to update the view according to changes in the StartModel.
 * </p>
 */
public class StartView extends View {

    private StackPane root = new StackPane();


    private StartScreen startScreen;

    private LevelOverlay levelOverlay;

    private Settings settings;

    private LevelBuilder levelBuilder;

    private StartModel model;


    /**
     * Constructs the StartView, initializing all UI components, layouts, and styles.
     * Sets up the main menu, settings popup, level selection overlay,
     * and level builder window with proper configurations.
     */
    public StartView() {

        startScreen = new StartScreen();

        levelOverlay = new LevelOverlay();

        settings = new Settings();

        levelBuilder = new LevelBuilder();

        root.getChildren().addAll(startScreen.mainLayout, levelOverlay.levelOverlay);
        setRoot(root);


        levelBuilder.builderStage.setScene(levelBuilder.builderScene);
        levelBuilder.builderStage.show();
        levelBuilder.builderStage.close();


        getRoot().getStylesheets().add(
                getClass().getResource("/style/style.css").toExternalForm()
        );


    }

    /**
     * Sets the Start Button of the Start View
     *
     * @param startButton the Button to associate with the Start Button of this view
     */
    public void setStartButton(Button startButton) {
        this.startScreen.startButton = startButton;
    }

    /**
     * Returns the Start Button of the Start View
     *
     * @return the Button associated with the Start Button of this View
     */
    public Button getStartButton() {
        return startScreen.startButton;
    }

    /**
     * Returns the LevelBuilder Button of the Start View
     *
     * @return the Button associated with the Level Builder Button of this View
     */
    public Button getLevelBuilderBTN() {
        return startScreen.levelBuilderBTN;
    }

    public Button getCloseGameBTN() {

        return startScreen.closerButton;
    }

    /**
     * Returns the Settings Button of the Start View
     *
     * @return the Button associated with the Settings Button of this View
     */
    public Button getSettingsButton() {
        return startScreen.settingsButton;
    }

    /**
     * Returns the Save Settings Button used to save the current Settings
     *
     * @return the Button associated with Save Settings Button of this View
     */
    public Button getSaveSettingsButton() {
        return settings.saveSettingsButton;
    }

    /**
     * Returns the Cancel Button used to exit the Level Overlay into the Start View
     *
     * @return the Button associated with the Cancel Button
     */
    public Button getCancelButton() {
        return levelOverlay.cancelButton;
    }

    /**
     * Returns the Button used to exit the Level Builder and save the currently build Level
     *
     * @return the Button associated with the ExitLevelBuilder Button
     */
    public Button getBuilderExitBTN(){
        return levelBuilder.exitButton;
    }

    /**
     * Returns the Button used to choose to play the Easy Level
     *
     * @return the Button associated with the EasyLevel Button
     */
    public Button getEasyLevelButton() {
        return levelOverlay.selectEasyLevelButton;
    }

    /**
     * Returns the Button used to choose to play the Medium Level
     *
     * @return the Button associated with the MediumLevel Button
     */
    public Button getMediumLevelButton() {
        return levelOverlay.selectMediumLevelButton;
    }

    /**
     * Returns the Button used to choose to play the Hard Level
     *
     * @return the Button associated with the HardLevel Button
     */
    public Button getHardLevelButton() {
        return levelOverlay.selectHardLevelButton;
    }

    /**
     * Returns the Button used to choose to play a Custom Level
     *
     * @return the Button associated with the CustomLevel Button
     */
    public Button getCustomLevelButton() {
        return levelOverlay.selectCustomLevelButton;
    }

    /**
     * Returns the Box used to choose the Difficulty in Settings
     *
     * @return the ComboBox instance
     */
    public ComboBox<Difficulty> getDifficultyBox() {
        return settings.difficultyBox;
    }

    /**
     * Returns the slider used to choose the FPS
     *
     * @return the Slider instance for choosing the FPS
     */
    public Slider getFpsSlider() {
        return settings.fpsSlider;
    }

    /**
     * Returns the Textfield used to choose a name in the Level Builder
     *
     * @return the Textfield instance
     */
    public TextField getNameInput() {
        return levelBuilder.nameInput;
    }


    /**
     * Returns the Textfield used to choose the gravity in the x-axis in the LevelBuilder
     *
     * @return the Textfield instance
     */
    public TextField getGravityXInput() {
        return levelBuilder.gravityXInput;
    }

    /**
     * Returns the Textfield used to choose the gravity in the y-axis in the LevelBuilder
     *
     * @return the Textfield instance
     */
    public TextField getGravityYInput() {
        return levelBuilder.gravityYInput;
    }

    /**
     * Returns the Button used to choose to place a Box in the LevelBuilder
     *
     * @return the Button associated with placing a Box
     */
    public Button getPlaceBox() {
        return levelBuilder.placeBox;
    }


    public Button getPlaceBall() {
        return levelBuilder.placeBall;
    }

    public Button getStartPointButton() {
        return levelBuilder.placeStart;
    }
    /**
     * Returns the current StartModel associated with this view.
     *
     * @return the StartModel instance
     */
    public StartModel getModel() {

        return this.model;
    }

    /**
     * Sets the StartModel associated with this view.
     * Registers this view as an observer of the model and triggers an initial update.
     *
     * @param model the StartModel to associate with this view
     */
    public void setModel(StartModel model) {
        this.model = model ;
        model.addObserver(this);
        update();
    }

    /**
     * Returns the Builder Pane containing the Shapes currently used in the Level Builder
     *
     * @return the Pane instance of the BuilderPane
     */
    public Pane getBuilder() {
        return levelBuilder.builderPane;
    }


    /**
     * Returns the Window used to display the Level Builder
     *
     * @return the Stage containing all the UI for the Level Builder
     */
    public Stage getBuilderStage() {
        return levelBuilder.builderStage;
    }


    /**
     * Updates the view according to the current state of the model.
     * Shows or hides the settings popup, level builder window, and level overlay.
     */
    @Override
    public void update() {

          if(model.getShowSettings()) {
              settings.popup.setScene(settings.popupScene);
              settings.popup.showAndWait();
          } else  {
              settings.popup.close();
          }

          if(model.getShowBuilder()) {

              levelBuilder.builderStage.setScene(levelBuilder.builderScene);
              levelBuilder.builderStage.showAndWait();
          } else {

              levelBuilder.builderStage.close();
          }


          levelOverlay.levelOverlay.setVisible(model.getShowLevelOverlay());


    }



    /**
     * Clears the builder UI inputs and removes all child nodes from the builder pane.
     * Resets the builder to its initial empty state.
     */
    public void resetBuilderUI() {

        levelBuilder.builderPane.getChildren().clear();

        levelBuilder.nameInput.clear();
        levelBuilder.gravityXInput.clear();
        levelBuilder.gravityYInput.clear();
    }
}
