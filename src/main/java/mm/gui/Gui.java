package mm.gui;

import javafx.application.Application;
import javafx.stage.Stage;


/**
 * Entry point for launching the application.
 * <p>
 * This class initializes and starts the graphical user interface (GUI)
 * of the Crazy Machines game prototype.
 * </p>
 */
public class Gui extends Application {

    @Override
    public void start(Stage primaryStage) {


        ViewManager viewManager = new ViewManager(primaryStage);

        viewManager.showStartView();

    }

    /**
     * Main method of the application.
     * <p>
     * This method is invoked when the program starts. It delegates
     * the execution to the GUI's main method.
     * </p>
     *
     * @param args the command-line arguments passed to the program
     */
    public static void main(String[] args) {
        launch(args);
    }
}
