package mm.MVC.game.elementsUI;

import javafx.animation.AnimationTimer;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import static mm.utilities.Makros.*;

public class SimView {



    public Button btnStartStop;

    public Button reset;

    public Button toEditor;

    public Button exitLevel;



    public Label simTimeLabel;



    public AnimationTimer gameTimer;

    public VBox sideBarLeft;

    public VBox sideBarRight;

    public HBox bottomBar;


    public SimView() {

        btnStartStop = new Button();
        reset = new Button("Reset");
        toEditor = new Button("Zum Editor");
        exitLevel = new Button("Exit Level");

        simTimeLabel = new Label("Zeit: 0.0s");

        sideBarLeft = new VBox();
        sideBarLeft.setPrefWidth(SIDEBAR_LEFT_WIDTH);
        sideBarLeft.setStyle("-fx-background-color: rgba(0,255,0);");
        sideBarLeft.getChildren().addAll(btnStartStop, reset);

        sideBarRight = new VBox();
        sideBarRight.setPrefWidth(SIDEBAR_RIGHT_WIDTH);
        sideBarRight.setStyle("-fx-background-color: rgba(255,0,0);");
        sideBarRight.getChildren().add(simTimeLabel);

        bottomBar = new HBox();
        bottomBar.setPrefHeight(BOTTOMBAR_HEIGHT);
        bottomBar.setStyle("-fx-background-color: rgba(0,0,0);");
        bottomBar.getChildren().addAll(toEditor, exitLevel);
    }


}
