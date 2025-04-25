package mm.MVC.game;

import javafx.animation.AnimationTimer;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import mm.MVC.View;

public class GameView extends View {


    private Label startLabel = new Label("TEST");


    private GameModel model;

    public GameView() {

        Pane layout = new Pane();


        setRoot(layout);
    }

    public void startSimulation() {

        AnimationTimer timer = new AnimationTimer() {
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
    }







    @Override
    public void update() {

    }
}
