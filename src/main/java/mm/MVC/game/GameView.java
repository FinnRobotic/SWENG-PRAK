package mm.MVC.game;

import javafx.animation.AnimationTimer;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import mm.MVC.View;
import mm.MVC.game.elementsUI.GameLostDisplay;
import mm.MVC.game.elementsUI.GameWonDisplay;
import mm.MVC.game.elementsUI.SimView;
import mm.MVC.start.StartModel;
import mm.utilities.PhysicsObjects.RigidBody;
import mm.utilities.WinCondition;
import org.jbox2d.common.Vec2;

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


    private StackPane gamePane;

    private SimView simView;

    private GameWonDisplay gameWonDisplay;

    private GameLostDisplay gameLostDisplay;

    private GameModel model;

    private Rectangle winField;



    /**
     * Constructs a new GameView with a layout consisting of a central
     * game pane for rendering, left and right sidebars, and a bottom bar.
     * Sets default styles and sizes for components.
     */
    public GameView() {

        gamePane = new StackPane();
        gamePane.setPrefSize(GAMEPANE_HEIGHT, GAMEPANE_WIDTH);
        gamePane.setStyle("-fx-background-color: lightgray;");

        gameWonDisplay = new GameWonDisplay();

        gameLostDisplay = new GameLostDisplay();


        gamePane.getChildren().addAll(gameWonDisplay.endDisplay, gameLostDisplay.endDisplay);

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
                winField.toFront();
                if(checkGameOver(model.getGameDef().gameBall.getPosition(), model.getGameTime())) {
                    model.toggleSimRunning();
                    model.gameIsOver();
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

        Pane simulationLayer = new Pane(); // Für Shapes
        for (RigidBody bd : model.getGameDef().getBodies()) {
            bd.getShape().setTranslateY(gamePane.getHeight() - bd.body.getPosition().y * m_to_px_scale);
            bd.getShape().setTranslateX(bd.body.getPosition().x * m_to_px_scale);
            bd.getShape().setRotate(-Math.toDegrees(bd.body.getAngle()));
            simulationLayer.getChildren().add(bd.getShape());
        }

        createWinField(model.getGameDef().currentLevel.getWinCondition());
        simulationLayer.getChildren().add(winField);

        gamePane.getChildren().addAll(simulationLayer, gameWonDisplay.endDisplay, gameLostDisplay.endDisplay);
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

    public Button getWonCloseLevelBTN() {
        return gameWonDisplay.toStartScreen;
    }

    public Button getLostCloseLevelBTN() {
        return gameLostDisplay.toStartScreen;
    }

    public Button getWonPlayAgainBTN() {
        return gameWonDisplay.playAgain;
    }

    public Button getLostPlayAgainBTN() {
        return gameLostDisplay.playAgain;
    }

    public Button getWonDifferentLvlBTN() {
        return gameWonDisplay.differentLevel;
    }

    public Button getLostDifferentLvlBTN() {
        return gameLostDisplay.differentLevel;
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

        if(model.isResetLevel() && !model.isGameOver()) {

            simView.gameTimer.stop();

            gamePane.getChildren().clear();

            simView.btnStartStop.setText("Start");

            setSimulation();


            model.toogleResetLevel();
        }

        if(!model.isSimRunning() && !model.isGameOver()) {

            simView.gameTimer.stop();
            simView.btnStartStop.setText("Start");
            simView.simTimeLabel.setText(String.format("Zeit: %.1fs", model.getGameTime()));


            model.resetAccumulator();
            model.setLastUpdate(0);

        } else if(model.isSimRunning() && !model.isGameOver()) {
            simView.gameTimer.start();
            simView.btnStartStop.setText("Stop");

        }

        if(model.isGameOver()) {

            if (model.gameWon()) {
                gameWonDisplay.setTotalTimeLabel(model.getGameTime());
                gameWonDisplay.endDisplay.setVisible(true);
                gameWonDisplay.endDisplay.toFront();
            } else {
                gameLostDisplay.setTotalTimeLabel(model.getGameTime());
                gameLostDisplay.endDisplay.setVisible(true);
                gameLostDisplay.endDisplay.toFront();
            }

        }
    }

    private void createWinField(WinCondition winCondition) {

        double height = winCondition.height;
        double width = winCondition.width;
        double x = winCondition.winPosition.x;
        double y = winCondition.winPosition.y;

        double paneHeight = gamePane.getHeight();

        winField = new Rectangle(width* m_to_px_scale, height * m_to_px_scale, Color.RED);
        winField.setX(-width* m_to_px_scale/ 2.0f);
        winField.setY(-height* m_to_px_scale/ 2.0f);
        winField.setTranslateX(x * m_to_px_scale);
        winField.setTranslateY(paneHeight - y * m_to_px_scale);

        winField.setOpacity(0.4f);
        winField.setStroke(Color.DARKGRAY);
        winField.setStrokeWidth(5);

        gamePane.getChildren().add(winField);
        winField.toFront();
    }


    private Boolean checkGameOver(Vec2 gameBallPos, float currentTime) {

        WinCondition win = model.getGameDef().currentLevel.getWinCondition();

        float ballLeft   = gameBallPos.x - RADIUS_GAMEBALL_PX * px_to_m_scale;
        float ballRight  = gameBallPos.x + RADIUS_GAMEBALL_PX * px_to_m_scale;
        float ballBottom = gameBallPos.y - RADIUS_GAMEBALL_PX * px_to_m_scale;
        float ballTop    = gameBallPos.y + RADIUS_GAMEBALL_PX * px_to_m_scale;

        float winLeft   = win.winPosition.x - win.width / 2;
        float winRight  = win.winPosition.x + win.width / 2;
        float winBottom = win.winPosition.y - win.height / 2;
        float winTop    = win.winPosition.y + win.height / 2;

        if(

            ballLeft   >= winLeft &&
                    ballRight  <= winRight &&
                    ballBottom >= winBottom &&
                    ballTop    <= winTop

        ) {
            return model.gameWon();

        } else if(model.getGameDef().currentLevel.getMaxTime() < currentTime) {

            return model.gameLost();

        } else {
            return false;
        }
    }
}
