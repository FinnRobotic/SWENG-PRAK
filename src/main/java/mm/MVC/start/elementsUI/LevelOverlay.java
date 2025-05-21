package mm.MVC.start.elementsUI;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class LevelOverlay {

    public VBox levelOverlay;
    public Button cancelButton = new Button("Zurück");
    public Button selectEasyLevelButton = new Button("Easy");
    public Button selectMediumLevelButton = new Button("Medium");
    public Button selectHardLevelButton = new Button("Hard");
    public Button selectCustomLevelButton = new Button("Custom Level");


    public LevelOverlay() {
        levelOverlay = new VBox(10);
        levelOverlay.setAlignment(Pos.CENTER);
        levelOverlay.setStyle("-fx-background-color: rgba(0, 0, 0, 1); -fx-padding: 20;");
        levelOverlay.setMaxWidth(400);
        levelOverlay.setMaxHeight(300);

        Label title = new Label("Level auswählen");
        title.getStyleClass().add("label-title");


        HBox selectLevel = new HBox(10, selectEasyLevelButton, selectMediumLevelButton, selectHardLevelButton);
        selectLevel.setAlignment(Pos.CENTER);
        HBox cancelButtonRow = new HBox(10, cancelButton);
        cancelButtonRow.setAlignment(Pos.CENTER);


        levelOverlay.getChildren().addAll(title, selectLevel,selectCustomLevelButton, cancelButtonRow);

        levelOverlay.setVisible(false);

    }


}
