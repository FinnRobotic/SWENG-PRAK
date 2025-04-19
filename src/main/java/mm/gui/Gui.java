package mm.gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import mm.MVC.start.StartController;
import mm.MVC.start.StartModel;
import mm.MVC.start.StartView;

import javax.swing.text.View;

public class Gui extends Application {

    @Override
    public void start(Stage primaryStage) {


        ViewManager viewManager = new ViewManager(primaryStage);

        viewManager.showStartView();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
