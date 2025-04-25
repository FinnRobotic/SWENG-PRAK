package mm.MVC.start;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import mm.MVC.View;

// To be implemented
public class StartView extends View {

    private Button startButton = new Button("Start");
    private Button settingsButton = new Button("Settings");

    private StartModel model;

    public StartView() {
        VBox layout = new VBox(20);
        layout.setAlignment(Pos.CENTER);

        Label startLabel = new Label("Crazy Machines");
        layout.getChildren().addAll(startLabel, startButton, settingsButton);

        setRoot(layout);
    }


    public void setStartButton(Button startButton) {
        this.startButton = startButton;
    }
    public Button getStartButton() {
        return startButton;
    }

    public void setSettingsButton(Button settingsButton) {
        this.settingsButton = settingsButton;
    }

    public Button getSettingsButton() {
        return settingsButton;
    }


    public StartModel getModel() {

        return this.model;
    }

    
    public void setModel(StartModel model) {
        this.model = model ;
        model.addObserver(this);
        update();
    }


    @Override
    public void update() {

    }
}
