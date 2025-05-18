package mm.MVC.start;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.shape.Shape;
import mm.MVC.View;
import javafx.stage.Stage;
import javafx.stage.Modality;
import javafx.scene.Scene;
import mm.utilities.Difficulty;

import static mm.utilities.Makros.*;

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



    private VBox mainLayout = new VBox(20);
    private Label startLabel = new Label("Crazy Machines");
    private Button startButton = new Button("Start");
    private Button levelBuilderBTN = new Button("Level Builder");
    private Button settingsButton = new Button("Settings");

    private Button saveSettingsButton = new Button("Save & Close");
    private ComboBox<Difficulty> difficultyBox = new ComboBox<>();
    private Slider fpsSlider = new Slider(FPS_MIN, FPS_DEFAULT, FPS_MAX );


    private  Stage popup = new Stage();
    private Label label = new Label("Hier sind die Einstellungen.");
    private VBox popupLayout = new VBox(15);
    private Scene popupScene = new Scene(popupLayout, SETTINGS_WIDTH, SETTINGS_HEIGHT);


    private VBox levelOverlay;
    private Button confirmButton = new Button("Level starten");
    private Button cancelButton = new Button("Zurück");
    private Button selectEasyLevelButton = new Button("Easy");
    private Button selectMediumLevelButton = new Button("Medium");
    private Button selectHardLevelButton = new Button("Hard");
    private Button selectCustomLevelButton = new Button("Custom Level");


    private Stage builderStage = new Stage();
    private BorderPane builderOverlay = new BorderPane();
    private Scene builderScene = new Scene(builderOverlay);
    private Pane builderPane;
    private Button placeBox = new Button("Box");
    private Button placeBall = new Button("Ball");
    private Button placeStart = new Button("StartPoint");
    private Button exitButton = new Button("Exit");
    private TextField gravityXInput = new TextField();
    private TextField gravityYInput = new TextField();
    private TextField nameInput = new TextField();


    private StartModel model;


    /**
     * Constructs the StartView, initializing all UI components, layouts, and styles.
     * Sets up the main menu, settings popup, level selection overlay,
     * and level builder window with proper configurations.
     */
    public StartView() {

        mainLayout.setAlignment(Pos.CENTER);
        mainLayout.getChildren().addAll(startLabel,
                                        startButton,
                                        levelBuilderBTN,
                                        settingsButton);

        // Overlay-Placeholder vorbereiten
        levelOverlay = createLevelOverlay();
        levelOverlay.setVisible(false); // Nur anzeigen, wenn gebraucht

        builderOverlay = createLevelBuilder();
        builderStage.setTitle("Level Builder");
        builderStage.setFullScreen(true);
        builderStage.initModality(Modality.APPLICATION_MODAL);
        builderStage.setScene(builderScene);


        root.getChildren().addAll(mainLayout, levelOverlay);
        setRoot(root);

        getRoot().getStylesheets().add(
                getClass().getResource("/style/style.css").toExternalForm()
        );

        popupScene.getStylesheets().add(getClass().getResource("/style/style.css").toExternalForm());

        startLabel.getStyleClass().add("label-title");
        settingsButton.getStyleClass().add("button");
        startButton.getStyleClass().add("button");
        label.getStyleClass().add("popup-label");
        popupLayout.getStyleClass().add("popup");

        popup.initModality(Modality.APPLICATION_MODAL);
        popup.setTitle("Settings");

        fpsSlider.setShowTickLabels(true);
        fpsSlider.setMajorTickUnit(30);
        fpsSlider.setShowTickMarks(true);

        difficultyBox.getItems().addAll(Difficulty.values());

        popupLayout.setAlignment(Pos.CENTER);
        popupLayout.setStyle("-fx-padding: 20;");
        popupLayout.getChildren().addAll(
                new Label("Difficulty:"), difficultyBox,
                new Label("FPS:"), fpsSlider,
                saveSettingsButton
        );
    }

    /**
     * Sets the Start Button of the Start View
     *
     * @param startButton the Button to associate with the Start Button of this view
     */
    public void setStartButton(Button startButton) {
        this.startButton = startButton;
    }

    /**
     * Returns the Start Button of the Start View
     *
     * @return the Button associated with the Start Button of this View
     */
    public Button getStartButton() {
        return startButton;
    }

    /**
     * Returns the LevelBuilder Button of the Start View
     *
     * @return the Button associated with the Level Builder Button of this View
     */
    public Button getLevelBuilderBTN() {
        return levelBuilderBTN;
    }

    public void setSettingsButton(Button settingsButton) {
        this.settingsButton = settingsButton;
    }

    /**
     * Returns the Settings Button of the Start View
     *
     * @return the Button associated with the Settings Button of this View
     */
    public Button getSettingsButton() {
        return settingsButton;
    }

    /**
     * Returns the Save Settings Button used to save the current Settings
     *
     * @return the Button associated with Save Settings Button of this View
     */
    public Button getSaveSettingsButton() {
        return saveSettingsButton;
    }

    /**
     * Returns the Cancel Button used to exit the Level Overlay into the Start View
     *
     * @return the Button associated with the Cancel Button
     */
    public Button getCancelButton() {
        return cancelButton;
    }

    /**
     * Returns the Button used to exit the Level Builder and save the currently build Level
     *
     * @return the Button associated with the ExitLevelBuilder Button
     */
    public Button getBuilderExitBTN(){
        return exitButton;
    }

    /**
     * Returns the Button used to choose to play the Easy Level
     *
     * @return the Button associated with the EasyLevel Button
     */
    public Button getEasyLevelButton() {
        return selectEasyLevelButton;
    }

    /**
     * Returns the Button used to choose to play the Medium Level
     *
     * @return the Button associated with the MediumLevel Button
     */
    public Button getMediumLevelButton() {
        return selectMediumLevelButton;
    }

    /**
     * Returns the Button used to choose to play the Hard Level
     *
     * @return the Button associated with the HardLevel Button
     */
    public Button getHardLevelButton() {
        return selectHardLevelButton;
    }

    /**
     * Returns the Button used to choose to play a Custom Level
     *
     * @return the Button associated with the CustomLevel Button
     */
    public Button getCustomLevelButton() {
        return selectCustomLevelButton;
    }

    /**
     * Returns the Box used to choose the Difficulty in Settings
     *
     * @return the ComboBox instance
     */
    public ComboBox<Difficulty> getDifficultyBox() {
        return difficultyBox;
    }

    /**
     * Returns the slider used to choose the FPS
     *
     * @return the Slider instance for choosing the FPS
     */
    public Slider getFpsSlider() {
        return fpsSlider;
    }

    /**
     * Returns the Textfield used to choose a name in the Level Builder
     *
     * @return the Textfield instance
     */
    public TextField getNameInput() {
        return nameInput;
    }


    /**
     * Returns the Textfield used to choose the gravity in the x-axis in the LevelBuilder
     *
     * @return the Textfield instance
     */
    public TextField getGravityXInput() {
        return gravityXInput;
    }

    /**
     * Returns the Textfield used to choose the gravity in the y-axis in the LevelBuilder
     *
     * @return the Textfield instance
     */
    public TextField getGravityYInput() {
        return gravityYInput;
    }

    /**
     * Returns the Button used to choose to place a Box in the LevelBuilder
     *
     * @return the Button associated with placing a Box
     */
    public Button getPlaceBox() {
        return placeBox;
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
        return builderPane;
    }


    /**
     * Returns the Window used to display the Level Builder
     *
     * @return the Stage containing all the UI for the Level Builder
     */
    public Stage getBuilderStage() {
        return builderStage;
    }


    /**
     * Updates the view according to the current state of the model.
     * Shows or hides the settings popup, level builder window, and level overlay.
     */
    @Override
    public void update() {

          if(model.getShowSettings()) {
              popup.setScene(popupScene);
              popup.showAndWait();
          }else  {
              popup.close();
          }

          if(model.getShowBuilder()) {

              builderStage.setScene(builderScene);
              builderStage.showAndWait();
          } else {
              builderStage.close();
          }


          levelOverlay.setVisible(model.getShowLevelOverlay());


    }

    /**
     * Creates the overlay used for level selection.
     *
     * @return a VBox containing buttons and labels for selecting levels
     */
    private VBox createLevelOverlay() {
        VBox overlay = new VBox(10);
        overlay.setAlignment(Pos.CENTER);
        overlay.setStyle("-fx-background-color: rgba(0, 0, 0, 1); -fx-padding: 20;");
        overlay.setMaxWidth(400);
        overlay.setMaxHeight(300);

        Label title = new Label("Level auswählen");
        title.getStyleClass().add("label-title");


        HBox selectLevel = new HBox(10, selectEasyLevelButton, selectMediumLevelButton, selectHardLevelButton);
        HBox cancelButtonRow = new HBox(10, confirmButton, cancelButton);
        cancelButtonRow.setAlignment(Pos.CENTER);

        overlay.getChildren().addAll(title, selectLevel,selectCustomLevelButton, cancelButtonRow);

        return overlay;
    }

    /**
     * Creates the layout for the level builder UI.
     * Sets up the main builder pane, sidebars, input fields, and bottom bar.
     *
     * @return a BorderPane containing the level builder UI components
     */
    private BorderPane createLevelBuilder() {
        builderPane = new Pane();
        builderPane.setPrefSize(GAMEPANE_HEIGHT, GAMEPANE_WIDTH);
        builderPane.setStyle("-fx-background-color: lightgray;");

        VBox sidebarLeft = new VBox();
        sidebarLeft.setPrefWidth(SIDEBAR_LEFT_WIDTH);
        sidebarLeft.setStyle("-fx-background-color: rgba(0,255,0);");
        sidebarLeft.getChildren().addAll(placeStart);

        VBox sidebarRight = new VBox();
        sidebarRight.setPrefWidth(SIDEBAR_RIGHT_WIDTH);
        sidebarRight.setStyle("-fx-background-color: rgba(255,0,0);");
        sidebarRight.getChildren().addAll(placeBall, placeBox);

        // Levelname-Eingabe
        Label nameLabel = new Label("Levelname:");
        nameInput.setPrefWidth(120);
        VBox nameBox = new VBox(5, nameLabel, nameInput);
        nameBox.setAlignment(Pos.CENTER_LEFT);

// Gravitation-Eingabe
        Label gravityTitleLabel = new Label("Gravitation:");
        Label gravityXLabel = new Label("X:");
        gravityXInput.setPrefWidth(60);

        Label gravityYLabel = new Label("Y:");
        gravityYInput.setPrefWidth(60);

        HBox gravityXYBox = new HBox(10,
                new VBox(5, gravityXLabel, gravityXInput),
                new VBox(5, gravityYLabel, gravityYInput)
        );
        gravityXYBox.setAlignment(Pos.CENTER_LEFT);

        VBox gravityBox = new VBox(5, gravityTitleLabel, gravityXYBox);
        gravityBox.setAlignment(Pos.CENTER_LEFT);

// BottomBar
        HBox bottomBar = new HBox(20); // Mehr Abstand für bessere Lesbarkeit
        bottomBar.setPrefHeight(BOTTOMBAR_HEIGHT);
        bottomBar.setPadding(new Insets(5));
        bottomBar.setStyle("-fx-background-color: rgba(0,0,0);");
        bottomBar.setAlignment(Pos.CENTER_LEFT);
        bottomBar.getChildren().addAll(
                exitButton,
                nameBox,
                gravityBox
        );

        BorderPane layout = new BorderPane();
        layout.setCenter(builderPane);
        layout.setLeft(sidebarLeft);
        layout.setRight(sidebarRight);
        layout.setBottom(bottomBar);

        builderScene = new Scene(layout);

        return layout;

    }


    /**
     * Clears the builder UI inputs and removes all child nodes from the builder pane.
     * Resets the builder to its initial empty state.
     */
    public void resetBuilderUI() {

        builderPane.getChildren().clear();

        nameInput.clear();
        gravityXInput.clear();
        gravityYInput.clear();
    }
}
