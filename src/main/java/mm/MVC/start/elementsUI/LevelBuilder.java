package mm.MVC.start.elementsUI;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import static mm.utilities.Makros.*;
import static mm.utilities.Makros.BOTTOMBAR_HEIGHT;
import static mm.utilities.Makros.SIDEBAR_RIGHT_WIDTH;

public class LevelBuilder {



    public Stage builderStage;
    public BorderPane builderOverlay;
    public Scene builderScene;
    public Pane builderPane;
    public Button placeBox = new Button("Box");
    public Button placeBall = new Button("Ball");
    public Button placeStart = new Button("StartPoint");
    public Button placeWin = new Button("WinField");
    public Button exitButton = new Button("Exit");
    public TextField gravityXInput = new TextField();
    public TextField gravityYInput = new TextField();
    public TextField nameInput = new TextField();

    public Label smallBoxValue;
    public Button smallBoxDown;
    public Button smallBoxUp;

    public Label mediumBoxValue;
    public Button mediumBoxDown;
    public Button mediumBoxUp;

    public Label bigBoxValue;
    public Button bigBoxDown;
    public Button bigBoxUp;

    public Label smallBallValue;
    public Button smallBallDown;
    public Button smallBallUp;

    public Label mediumBallValue;
    public Button mediumBallDown;
    public Button mediumBallUp;

    public Label bigBallValue;
    public Button bigBallDown;
    public Button bigBallUp;




    public LevelBuilder() {


        builderPane = new Pane();
        builderPane.setPrefSize(GAMEPANE_HEIGHT, GAMEPANE_WIDTH);
        builderPane.setStyle("-fx-background-color: lightgray;");
        builderPane.setOnMousePressed(e -> {
            System.out.println("Mouse pressed at sceneX=" + e.getSceneX() + ", sceneY=" + e.getSceneY());
        });


        HBox boxCountersRow = new HBox(20,
                createCounterBox("Small Box", 3, smallBoxValue, smallBoxUp, smallBoxDown),
                createCounterBox("Medium Box", 3, mediumBoxValue, mediumBoxUp, mediumBoxDown),
                createCounterBox("Big Box", 3,bigBoxValue, bigBoxUp, bigBoxDown)
        );

        boxCountersRow.setAlignment(Pos.CENTER);

        HBox ballCountersRow = new HBox(20,
                createCounterBox("Small Ball", 3, smallBallValue, smallBallUp, smallBallDown),
                createCounterBox("Medium Ball", 3, mediumBallValue, mediumBallUp, mediumBallDown),
                createCounterBox("Big Ball", 3,bigBallValue, bigBallUp, bigBallDown)
        );

        ballCountersRow.setAlignment(Pos.CENTER);

        VBox sidebarLeft = new VBox();
        sidebarLeft.setPrefWidth(SIDEBAR_LEFT_WIDTH);
        sidebarLeft.setStyle("-fx-background-color: rgba(0,255,0);");
        sidebarLeft.getChildren().addAll(
                placeStart,
                new Label("Boxen:"),
                boxCountersRow,
                new Label("Bälle:"),
                ballCountersRow,
                placeWin
                );



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
        HBox bottomBar = new HBox(); // Mehr Abstand für bessere Lesbarkeit
        bottomBar.setPrefHeight(BOTTOMBAR_HEIGHT);
        bottomBar.setStyle("-fx-background-color: rgba(0,0,0);");
        bottomBar.setAlignment(Pos.CENTER_LEFT);
        bottomBar.getChildren().addAll(
                exitButton,
                nameBox,
                gravityBox
        );

        builderOverlay = new BorderPane();
        builderOverlay.setCenter(builderPane);
        builderOverlay.setLeft(sidebarLeft);
        builderOverlay.setRight(sidebarRight);
        builderOverlay.setBottom(bottomBar);

        builderScene = new Scene(builderOverlay,1280,720);

        builderStage = new Stage();
        builderStage.setTitle("Level Builder");
        builderStage.setScene(builderScene);
        builderStage.setFullScreen(true);



    }

    private VBox createCounterBox(String labelText, int startValue, Label valueLabel, Button upButton, Button downButton) {
        Label label = new Label(labelText);
        valueLabel = new Label(String.valueOf(startValue));
        valueLabel.setPrefWidth(30);
        valueLabel.setAlignment(Pos.CENTER);

        upButton = new Button("↑");
        downButton = new Button("↓");

        VBox controls = new VBox(2, upButton, downButton);
        controls.setAlignment(Pos.CENTER);

        VBox labels = new VBox(5, label, valueLabel);


        HBox counterRow = new HBox(5, labels, controls);
        counterRow.setAlignment(Pos.CENTER);

        VBox container = new VBox(counterRow);
        container.setAlignment(Pos.CENTER);


        return container;
    }

}
