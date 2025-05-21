package mm.MVC.start.elementsUI;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class StartScreen {


    public VBox mainLayout = new VBox(20);

    public Label startLabel = new Label("Crazy Machines");

    public Button startButton = new Button("Start");

    public Button levelBuilderBTN = new Button("Level Builder");

    public Button settingsButton = new Button("Settings");

    public Button closerButton = new Button("Close Game");




    public StartScreen() {

        mainLayout.setAlignment(Pos.CENTER);

        mainLayout.getChildren().addAll(startLabel,
                startButton,
                levelBuilderBTN,
                settingsButton,
                closerButton);

        startLabel.getStyleClass().add("label-title");
        settingsButton.getStyleClass().add("button");
        startButton.getStyleClass().add("button");
        levelBuilderBTN.getStyleClass().add("button");
        closerButton.getStyleClass().add("button");

    }




}
