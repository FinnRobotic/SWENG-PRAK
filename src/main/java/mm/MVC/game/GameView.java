package mm.MVC.game;

import javafx.animation.AnimationTimer;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import mm.MVC.View;
import mm.utilities.PhysicsObjects.RigidBody;

import static mm.utilities.Makros.*;

public class GameView extends View {


    private Pane gamePane;

    private Button btnStartStop = new Button();

    private GameModel model;

    private AnimationTimer gameTimer;

    public GameView() {

        gamePane = new Pane(); // Hier spielt die Action
        gamePane.setPrefSize(GAMEPANE_HEIGHT, GAMEPANE_WIDTH); // Deine Spielgröße
        gamePane.setStyle("-fx-background-color: lightgray;"); // Nur zur Ansicht


        VBox sidebarLeft = new VBox();
        sidebarLeft.setPrefWidth(SIDEBAR_LEFT_WIDTH);
        sidebarLeft.setStyle("-fx-background-color: rgba(0,255,0);");
        sidebarLeft.getChildren().add(btnStartStop);

        VBox sidebarRight = new VBox();
        sidebarRight.setPrefWidth(SIDEBAR_RIGHT_WIDTH);
        sidebarRight.setStyle("-fx-background-color: rgba(255,0,0);");

        HBox bottomBar = new HBox();
        bottomBar.setPrefHeight(BOTTOMBAR_HEIGHT);
        bottomBar.setStyle("-fx-background-color: rgba(0,0,0);");

        BorderPane layout = new BorderPane();
        layout.setCenter(gamePane);
        layout.setLeft(sidebarLeft);
        layout.setRight(sidebarRight);
        layout.setBottom(bottomBar);
        setRoot(layout);
    }

    public void startSimulation() {


        AddChildren();

        if(!model.isSimRunning()) {
            gameTimer = new AnimationTimer() {
                @Override
                public void handle(long now) {
                    if (model.getLastUpdate() == 0) {
                        model.setLastUpdate(now);
                        return;
                    }

                    // Zeit seit letztem Frame (in Sekunden)
                    float deltaTime = (now - model.getLastUpdate()) / 1_000_000_000f;
                    model.setLastUpdate(now);

                    // Simulationszeit aufholen (falls Frames gedroppt wurden)
                    model.incrAccumulator(deltaTime);
                    float timeStep = 1.0f / model.getGameDef().FPS;
                    while (model.getAccumulator() >= timeStep) {
                        model.simStep();
                        model.incrAccumulator(-timeStep);
                    }

                }
            };
            gameTimer.start();
            model.toggleSimRunning();
            btnStartStop.setText("Stop");
        }

    }

    private void AddChildren() {
        for(RigidBody bd : model.getGameDef().getBodies()) {
            gamePane.getChildren().add(bd.getShape());
        }
    }

    public GameModel getModel() {

        return this.model;
    }


    public void setModel(GameModel model) {
        this.model = model;
        model.addObserver(this);
    }


    public Button getStartStopBtn() {
        return btnStartStop;
    }

    public void setStartStopBtn(Button btnStartStop) {
        this.btnStartStop = btnStartStop;
    }






    @Override
    public void update() {
        double paneHeight = gamePane.getHeight();
        if(!model.isSimRunning()) {

            gameTimer.stop();
            btnStartStop.setText("Start");
            model.resetAccumulator();
            model.setLastUpdate(0);

        } else {
            gameTimer.start();
            btnStartStop.setText("Stop");
            for (RigidBody bd : model.getGameDef().getBodies()) {
                bd.getShape().setTranslateX(bd.body.getPosition().x * m_to_px_scale);
                bd.getShape().setTranslateY(paneHeight - bd.body.getPosition().y * m_to_px_scale);
                bd.getShape().setRotate(-Math.toDegrees(bd.body.getAngle()));
            }
        }
    }
}
