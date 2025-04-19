package mm.gui;

import javafx.scene.Scene;
import javafx.stage.Stage;
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
        controller.setView(view);
        scene = new Scene(view);
        stage.setScene(scene);
        stage.show();
    }



}
