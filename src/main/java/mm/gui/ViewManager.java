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

public class ViewManager {

    private Stage stage;
    private Scene scene;


    public ViewManager(Stage stage) {
        this.stage = stage;
    }


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

    public void showGameView(Level level, GameDef gamedef) {

        GameModel model = new GameModel(gamedef, level);

        GameView view = new GameView();

        view.setModel(model);

        GameController controller = new GameController();

        controller.setView(view, this);
        scene = new Scene(view.getRoot(), 1280, 720);
        stage.setScene(scene);
        stage.setFullScreen(true);

        view.startSimulation();

    }


    public void showEditorView() {

    }



}
