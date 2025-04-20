package mm.gui;

import javafx.application.Application;
import javafx.stage.Stage;

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
