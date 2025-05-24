package mm.MVC.game;

import javafx.animation.AnimationTimer;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import mm.MVC.View;
import mm.MVC.game.elementsUI.SimView;
import mm.MVC.start.StartModel;
import mm.utilities.PhysicsObjects.RigidBody;

import static mm.utilities.Makros.*;


/**
 * The GameView class represents the visual interface for the game simulation.
 *
 * It contains the game pane where physics objects are rendered,
 * control buttons such as start/stop, and manages the animation timer
 * that updates the simulation frames.
 *
 * Extends the base View class and implements Observer pattern to
 * react to model updates.
 */
public class GameView extends View {


    private Pane gamePane;

    private SimView simView;

    private GameModel model;



    /**
     * Constructs a new GameView with a layout consisting of a central
     * game pane for rendering, left and right sidebars, and a bottom bar.
     * Sets default styles and sizes for components.
     */
    public GameView() {

        gamePane = new Pane();
        gamePane.setPrefSize(GAMEPANE_HEIGHT, GAMEPANE_WIDTH);
        gamePane.setStyle("-fx-background-color: lightgray;");

        gamePane.setOnMousePressed(e -> {
            System.out.println("Mouse pressed at sceneX=" + e.getSceneX() + ", sceneY=" + e.getSceneY());
        });

        simView = new SimView();

        BorderPane layout = new BorderPane();
        layout.setCenter(gamePane);
        layout.setLeft(simView.sideBarLeft);
        layout.setRight(simView.sideBarRight);
        layout.setBottom(simView.bottomBar);
        setRoot(layout);
    }


    /**
     * Starts the physics simulation if it is not already running.
     *
     * Sets up an AnimationTimer that updates the game state each frame,
     * handling the fixed timestep simulation logic, including accumulating
     * delta times and advancing simulation steps accordingly.
     * Also updates the start/stop button text.
     */
    public void setSimulation() {

        model.initPlayground((float)gamePane.getHeight(), (float)gamePane.getWidth());
        addChildren();

        simView.gameTimer = new AnimationTimer() {


            @Override
            public void handle(long now) {
                if (model.getLastUpdate() == 0) {
                    model.setLastUpdate(now);
                    return;
                }


                // Zeit seit letztem Frame (in Sekunden)
                float deltaTime = (now - model.getLastUpdate()) / 1_000_000_000f;
                model.setLastUpdate(now);

                model.incrementGameTime(deltaTime);
                simView.simTimeLabel.setText(String.format("Zeit: %.1fs", model.getGameTime()));

                // Simulationszeit aufholen (falls Frames gedroppt wurden)
                model.incrAccumulator(deltaTime);
                float timeStep = 1.0f / model.getGameDef().FPS;
                while (model.getAccumulator() >= timeStep) {
                    model.simStep();
                    model.incrAccumulator(-timeStep);
                }
                for (RigidBody bd : model.getGameDef().getBodies()) {
                    bd.getShape().setTranslateX(bd.body.getPosition().x * m_to_px_scale);
                    bd.getShape().setTranslateY(gamePane.getHeight() - bd.body.getPosition().y * m_to_px_scale);
                    bd.getShape().setRotate(-Math.toDegrees(bd.body.getAngle()));
                }

            }
        };

        simView.btnStartStop.setText("Start");


    }

    /**
     * Adds the physics bodies' shapes to the gamePane for rendering.
     * Prints their initial translate coordinates for debugging.
     */
    private void addChildren() {
        gamePane.getChildren().clear();
        for(RigidBody bd : model.getGameDef().getBodies()) {
            bd.getShape().setTranslateY(gamePane.getHeight() - bd.body.getPosition().y * m_to_px_scale);
            bd.getShape().setTranslateX(bd.body.getPosition().x * m_to_px_scale);
            bd.getShape().setRotate(-Math.toDegrees(bd.body.getAngle()));
            gamePane.getChildren().add(bd.getShape());
        }
        for(Node sh : gamePane.getChildren()) {
            System.out.println(sh.getTranslateX()  +", " +sh.getTranslateY());
        };
    }

    /**
     * Returns the current GameModel associated with this view.
     *
     * @return the GameModel instance
     */
    public GameModel getModel() {

        return this.model;
    }


    /**
     * Sets the GameModel for this view and registers this view as
     * an observer to receive updates.
     *
     * @param model the GameModel to associate with this view
     */
    public void setModel(GameModel model) {
        this.model = model;
        model.addObserver(this);

    }

    /**
     * Returns the Start/Stop button control.
     *
     * @return the start/stop Button
     */
    public Button getStartStopBtn() {
        return simView.btnStartStop;
    }

    public Button getResetBtn() {
        return simView.reset;
    }

    public Button getToEditorBTN() {
        return simView.toEditor;
    }

    public Button getExitLevelBTN() {
        return simView.exitLevel;
    }


    /**
     * Called when the model notifies observers to update the view.
     *
     * Updates the physics objects' positions and rotations in the UI
     * according to the simulation state.
     * Starts or stops the AnimationTimer based on whether the simulation
     * is running.
     */
    @Override
    public void update() {
        double paneHeight = gamePane.getHeight();

        if(model.isResetLevel()) {

            simView.gameTimer.stop();

            gamePane.getChildren().clear();

            simView.btnStartStop.setText("Start");

            setSimulation();


            model.toogleResetLevel();
        }






        if(!model.isSimRunning()) {

            simView.gameTimer.stop();
            simView.btnStartStop.setText("Start");
            simView.simTimeLabel.setText(String.format("Zeit: %.1fs", model.getGameTime()));


            model.resetAccumulator();
            model.setLastUpdate(0);

        } else {
            simView.gameTimer.start();
            simView.btnStartStop.setText("Stop");

        }
    }
}
