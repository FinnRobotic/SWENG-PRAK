package mm.MVC.start;


import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import mm.gui.ViewManager;
import mm.utilities.GameDef;

// To be implemented
public class StartController {


    public void setView(StartView view, ViewManager viewManager) {

        view.getStartButton().setOnAction(actionEvent -> {view.getModel().toggleLevelOverlay();});

        view.getSettingsButton().setOnAction(e -> view.getModel().toggleSettings());

        view.getSaveSettingsButton().setOnAction(e -> {
            GameDef gameDef = view.getModel().getGameDef();
            gameDef.difficulty = view.getDifficultyBox().getValue();
            gameDef.FPS = (int) view.getFpsSlider().getValue();

            System.out.println("Settings saved: Difficulty = " + gameDef.difficulty + ", FPS = " + gameDef.FPS);
            view.getModel().toggleSettings();
        });
    }

}





