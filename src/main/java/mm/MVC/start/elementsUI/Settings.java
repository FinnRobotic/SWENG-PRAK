package mm.MVC.start.elementsUI;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import mm.utilities.Difficulty;

import static mm.utilities.Makros.*;

public class Settings {


    public Stage popup = new Stage();
    public Label Settingslabel = new Label("Hier sind die Einstellungen.");
    public VBox popupLayout = new VBox(15);
    public Scene popupScene = new Scene(popupLayout, SETTINGS_WIDTH, SETTINGS_HEIGHT);


    public Button saveSettingsButton = new Button("Save & Close");
    public ComboBox<Difficulty> difficultyBox = new ComboBox<>();
    public Slider fpsSlider = new Slider(FPS_MIN, FPS_DEFAULT, FPS_MAX );



    public Settings() {

        popupScene.getStylesheets().add(getClass().getResource("/style/style.css").toExternalForm());

        popupScene.getStylesheets().add(getClass().getResource("/style/style.css").toExternalForm());


        Settingslabel.getStyleClass().add("popup-label");
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
}
