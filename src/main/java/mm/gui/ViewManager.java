package mm.gui;

import javafx.scene.Scene;
import javafx.stage.Stage;
import mm.MVC.game.GameController;
import mm.MVC.game.GameModel;
import mm.MVC.game.GameView;
import mm.MVC.start.StartController;
import mm.MVC.start.StartModel;
import mm.MVC.start.StartView;

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

    public void showGameView() {
        GameModel model = new GameModel();
        GameView view = new GameView();
        view.setModel(model);
        GameController controller = new GameController();
        controller.setView(view);
        scene = new Scene(view.getRoot(),500,500);
        stage.setScene(scene);
    }


    public void showEditorView() {

    }



}
