package mm.MVC.start;


import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import mm.gui.ViewManager;

// To be implemented
public class StartController {


    public void setView(StartView view, ViewManager viewManager) {

        view.getStartButton().setOnAction(actionEvent -> {viewManager.showGameView(view.getModel().getGameDef());});
        view.getSettingsButton().setOnAction(e -> view.getModel().toggleSettings());
        view.getCloseButton().setOnAction(d -> view.getModel().toggleSettings());
    }

}





