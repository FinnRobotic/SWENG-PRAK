package mm.gui;

import javafx.scene.Scene;
import javafx.stage.Stage;
import mm.MVC.game.GameController;
import mm.MVC.game.GameModel;
import mm.MVC.game.GameView;
import mm.MVC.start.StartController;
import mm.MVC.start.StartModel;
import mm.MVC.start.StartView;
import mm.utilities.GameDef;
import mm.utilities.Level;


/**
 * The ViewManager class manages the different views of the application
 * and controls the switching between the start view, game view, and editor view.
 */
public class ViewManager {

    private Stage stage;
    private Scene scene;


    /**
     * Creates a new ViewManager with the given Stage.
     *
     * @param stage The main stage (window) of the application where scenes are displayed.
     */
    public ViewManager(Stage stage) {
        this.stage = stage;
    }


    /**
     * Displays the start view of the application.
     * Initializes the model, view, and controller for the start screen,
     * sets the scene on the stage, and shows it in full screen mode.
     */
    public void showStartView() {
        StartModel model = new StartModel();
        StartView view = new StartView();
        view.setModel(model);
        StartController controller = new StartController();
        scene = new Scene(view.getRoot(),1280,720);
        stage.setScene(scene);
        stage.setFullScreen(true);
        stage.show();
        controller.setView(view, this);
    }


    /**
     * Displays the game view with a specific level and game definition.
     * Initializes the model, view, and controller for the game,
     * sets the scene on the stage, shows it in full screen mode,
     * and starts the game simulation.
     *
     * @param level The level to be loaded in the game.
     * @param gamedef The game definition describing the game setup.
     */
    public void showGameView(Level level, GameDef gamedef) {


        GameView view = new GameView();

        GameModel model = new GameModel(gamedef, level);

        view.setModel(model);

        GameController controller = new GameController();

        controller.setView(view, this);
        scene = new Scene(view.getRoot(), 1280, 720);
        stage.setScene(scene);
        stage.setFullScreen(true);

        view.startSimulation();

    }


    /**
     * Displays the editor view.
     * This method is currently not implemented.
     */
    public void showEditorView() {
        // Not implemented yet
    }

    public Stage getStage() {
        return stage;
    }
}
