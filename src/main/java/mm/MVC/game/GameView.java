package mm.MVC.game;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import mm.MVC.View;

public class GameView extends View {


    private Label startLabel = new Label("TEST");


    private GameModel model;

    public GameView() {

        Pane layout = new Pane();


        setRoot(layout);
    }







    @Override
    public void update() {

    }
}
