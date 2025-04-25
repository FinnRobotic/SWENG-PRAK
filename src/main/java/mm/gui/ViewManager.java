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
        controller.setView(view, this);
        scene = new Scene(view.getRoot(),500,500);
        stage.setScene(scene);
        stage.show();
    }

    public void showGameView(GameDef gamedef) {
        GameModel model = new GameModel(gamedef);

        GameView view = new GameView();

        view.setModel(model);

        GameController controller = new GameController();

        controller.setView(view, this);
        Scene scene = new Scene(view.getRoot(), 1280, 720);
        stage.setScene(scene);
        stage.setFullScreen(true);

        view.startSimulation();

    }


    public void showEditorView() {

    }



}
