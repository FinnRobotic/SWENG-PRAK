package mm.MVC.start;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import mm.MVC.View;
import javafx.stage.Stage;
import javafx.stage.Modality;
import javafx.scene.Scene;

import static mm.utilities.Makros.SETTINGS_HEIGHT;
import static mm.utilities.Makros.SETTINGS_WIDTH;

// To be implemented
public class StartView extends View {

    private Button startButton = new Button("Start");
    private Button settingsButton = new Button("Settings");
    private Button closeButton = new Button("Schließen");
    private  Stage popup = new Stage();
    private Label label = new Label("Hier sind die Einstellungen.");
    private  VBox popupLayout = new VBox(10, label, closeButton);
    private Scene popupScene = new Scene(popupLayout, SETTINGS_WIDTH, SETTINGS_HEIGHT);

    private StartModel model;

    public StartView() {
        VBox layout = new VBox(20);
        layout.setAlignment(Pos.CENTER);


        Label startLabel = new Label("Crazy Machines");
        layout.getChildren().addAll(startLabel, startButton, settingsButton);

        settingsButton.getStyleClass().add("settings-button");

        popup.initModality(Modality.APPLICATION_MODAL);
        popup.setTitle("Settings");

        popupLayout.setAlignment(Pos.CENTER);
        popupLayout.setStyle("-fx-padding: 20;");

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

    public void setCloseButton(Button closeButton) {
        this.closeButton = closeButton;
    }

    public Button getCloseButton() {
        return closeButton;
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

          if(model.getShowSettings()) {
              popup.setScene(popupScene);
              popup.showAndWait();
          }else  {
              popup.close();
          }





    }
}
