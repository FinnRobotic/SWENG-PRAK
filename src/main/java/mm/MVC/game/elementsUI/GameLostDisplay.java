package mm.MVC.game.elementsUI;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class GameLostDisplay {

    public VBox endDisplay;

    public Label totalTimeLabel;

    public Button playAgain = new Button("Nochmal Spielen");
    public Button toStartScreen = new Button("Zum Startbildschirm");
    public Button differentLevel = new Button("Anderes Level spielen");

    public GameLostDisplay() {
        endDisplay = new VBox(20);
        endDisplay.setAlignment(Pos.CENTER);
        endDisplay.setStyle("-fx-background-color: rgba(0, 100, 1, 1); -fx-padding: 20;");
        endDisplay.setMaxWidth(400);
        endDisplay.setMaxHeight(300);

        Label gameOverLabel = new Label("Game Over");
        gameOverLabel.getStyleClass().add("label-title");

        totalTimeLabel = new Label();

        HBox firstRow = new HBox(10, playAgain, differentLevel);
        firstRow.setAlignment(Pos.CENTER);
        HBox secondRow = new HBox(10, toStartScreen);
        secondRow.setAlignment(Pos.CENTER);


        endDisplay.getChildren().addAll(gameOverLabel,totalTimeLabel, firstRow, secondRow);

        endDisplay.setVisible(false);
    }

    public void setTotalTimeLabel(float totalTime) {
        totalTimeLabel.setText(String.format("Zeit: %.2f", totalTime));
    }
}
