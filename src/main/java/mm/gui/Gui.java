package mm.gui;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.layout.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.text.*;
import mm.MVC.*;

public class Gui extends Application {

    @Override
    public void start(Stage primaryStage) {


        Model model = new Model();
        View view = new View();
        view.setModel(model);
        Controller controller = new Controller();
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
