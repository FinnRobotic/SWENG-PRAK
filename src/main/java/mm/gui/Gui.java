package mm.gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import mm.MVC.start.StartController;
import mm.MVC.start.StartModel;
import mm.MVC.start.StartView;

public class Gui extends Application {

    @Override
    public void start(Stage primaryStage) {


        StartModel model = new StartModel();
        StartView view = new StartView();
        view.setModel(model);
        StartController controller = new StartController();
        controller.setView(view);


        primaryStage.setTitle("Crazy Machines");

        Scene scene = new Scene(view, 300, 300);

        primaryStage.setScene(scene);

        primaryStage.show();


    }

    public static void main(String[] args) {
        launch(args);
    }
}
