package mm.MVC.start;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import mm.MVC.View;
import javafx.stage.Stage;
import javafx.stage.Modality;
import javafx.scene.Scene;
import mm.utilities.Difficulty;

import static mm.utilities.Makros.*;

// To be implemented
public class StartView extends View {

    private Button startButton = new Button("Start");
    private Button settingsButton = new Button("Settings");

    private Button saveSettingsButton = new Button("Save & Close");
    private ComboBox<Difficulty> difficultyBox = new ComboBox<>();
    private Slider fpsSlider = new Slider(FPS_MIN, FPS_DEFAULT, FPS_MAX );


    private  Stage popup = new Stage();
    private Label label = new Label("Hier sind die Einstellungen.");
    private VBox popupLayout = new VBox(15);
    private Scene popupScene = new Scene(popupLayout, SETTINGS_WIDTH, SETTINGS_HEIGHT);


    VBox levelOverlay;
    Button confirmButton = new Button("Level starten");
    Button cancelButton = new Button("Zurück");
    Button selectEasyLevelButton = new Button("Easy");
    Button selectMediumLevelButton = new Button("Medium");
    Button selectHardLevelButton = new Button("Hard");
    Button selectCustomLevelButton = new Button("Custom Level");







    private StartModel model;

    public StartView() {
        StackPane root = new StackPane();
        VBox mainLayout = new VBox(20);
        mainLayout.setAlignment(Pos.CENTER);

        Label startLabel = new Label("Crazy Machines");
        mainLayout.getChildren().addAll(startLabel, startButton, settingsButton);

        // Overlay-Placeholder vorbereiten
        levelOverlay = createLevelOverlay();
        levelOverlay.setVisible(false); // Nur anzeigen, wenn gebraucht

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


    public void setStartButton(Button startButton) {
        this.startButton = startButton;
    }
    public Button getStartButton() {
        return startButton;
    }

    public void setSettingsButton(Button settingsButton) {
        this.settingsButton = settingsButton;
    }

    public Button getSettingsButton() {
        return settingsButton;
    }

    public Button getSaveSettingsButton() {
        return saveSettingsButton;
    }

    public Button getConfirmButton() {
        return confirmButton;
    }

    public Button getCancelButton() {
        return cancelButton;
    }


    public Button getEasyLevelButton() {
        return selectEasyLevelButton;
    }

    public Button getMediumLevelButton() {
        return selectMediumLevelButton;
    }

    public Button getCustomLevelButton() {
        return selectCustomLevelButton;
    }

    public ComboBox<Difficulty> getDifficultyBox() {
        return difficultyBox;
    }

    public Slider getFpsSlider() {
        return fpsSlider;
    }

    public StartModel getModel() {

        return this.model;
    }

    
    public void setModel(StartModel model) {
        this.model = model ;
        model.addObserver(this);
        update();
    }


    @Override
    public void update() {

          if(model.getShowSettings()) {
              popup.setScene(popupScene);
              popup.showAndWait();
          }else  {
              popup.close();
          }

          levelOverlay.setVisible(model.getShowLevelOverlay());

    }

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
}
