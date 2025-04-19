package mm.MVC.start;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import mm.MVC.util.Observer;

// To be implemented
public class StartView extends StackPane implements Observer {

    private Label StartLabel = new Label("Crazy Machines");
    private Button StartButton = new Button("Start");
    private Button SettingsButton = new Button("Settings");

    private StartModel model;

    public StartView() {
        setAlignment(Pos.CENTER);
        getChildren().addAll(StartLabel, StartButton, SettingsButton);
    }


    public void setStartButton(Button startButton) {
        this.StartButton = startButton;
    }
    public Button getStartButton() {
        return StartButton;
    }

    public void setSettingsButton(Button settingsButton) {
        this.SettingsButton = settingsButton;
    }

    public Button getSettingsButton() {
        return SettingsButton;
    }

    public void setModel(StartModel model) {
        this.model = model;
        model.addObserver(this);
        update();
    }


    public StartModel getModel() {

        return this.model;
    }


    @Override
    public void update() {

    }
}
