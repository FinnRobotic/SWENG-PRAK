package mm.MVC.game;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import mm.MVC.View;
import mm.MVC.start.StartModel;

public class GameView extends View {


    private Label startLabel = new Label("TEST");


    private GameModel model;

    public GameView() {

        VBox layout = new VBox(20);
        layout.setAlignment(Pos.CENTER);

        layout.getChildren().addAll(startLabel);

        setRoot(layout);
    }







    @Override
    public void update() {

    }
}
