package mm.utilities;

import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import mm.utilities.ObjectsConf.BoxConf;

public class HelperUI {

    public static void addDraggableResizableRotatableBox(Pane targetPane) {
        BoxConf conf = new BoxConf();

        Rectangle rect = new Rectangle(100, 50);
        rect.setFill(Color.DODGERBLUE);
        rect.setStroke(Color.BLACK);

        Group group = new Group(rect);
        group.setLayoutX(200);
        group.setLayoutY(200);

        conf.x = 200;
        conf.y = 200;
        conf.width = 100;
        conf.height = 50;
        conf.angle = 0;

        // Ursprung in der Mitte
        rect.setTranslateX(-rect.getWidth() / 2);
        rect.setTranslateY(-rect.getHeight() / 2);

        // Drag
        group.setOnMousePressed(e -> {
            group.requestFocus(); // Für Tastatur
            dragDeltaX = e.getSceneX() - group.getLayoutX();
            dragDeltaY = e.getSceneY() - group.getLayoutY();
        });

        group.setOnMouseDragged(e -> {
            double newX = e.getSceneX() - dragDeltaX;
            double newY = e.getSceneY() - dragDeltaY;
            group.setLayoutX(newX);
            group.setLayoutY(newY);
            conf.x = (float) newX;
            conf.y = (float) newY;
        });

        // Tastatur: Rotation + Skalierung
        group.setOnKeyPressed(e -> {
            switch (e.getCode()) {
                case LEFT:
                    group.setRotate(group.getRotate() - 5);
                    break;
                case RIGHT:
                    group.setRotate(group.getRotate() + 5);
                    break;
                case W:
                    rect.setHeight(rect.getHeight() - 5);
                    break;
                case S:
                    rect.setHeight(rect.getHeight() + 5);
                    break;
                case A:
                    rect.setWidth(rect.getWidth() - 5);
                    break;
                case D:
                    rect.setWidth(rect.getWidth() + 5);
                    break;
                default:
                    break;
            }

            // Update Conf
            conf.width = (float) rect.getWidth();
            conf.height = (float) rect.getHeight();
            conf.angle = (float) group.getRotate();
        });

        targetPane.getChildren().add(group);

        // Optional: gleich Einstellungsfenster zeigen
        showBoxConfigPanel(conf);
    }

    private static void showBoxConfigPanel(BoxConf conf) {
        Stage configStage = new Stage();
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(10));

        TextField densityField = new TextField("1.0");
        TextField frictionField = new TextField("0.5");
        CheckBox staticBox = new CheckBox("Statisch");

        densityField.textProperty().addListener((obs, old, val) -> {
            try { conf.density = Float.parseFloat(val); } catch (Exception ignored) {}
        });

        frictionField.textProperty().addListener((obs, old, val) -> {
            try { conf.friction = Float.parseFloat(val); } catch (Exception ignored) {}
        });

        staticBox.selectedProperty().addListener((obs, old, isSelected) -> conf.isStatic = isSelected);

        layout.getChildren().addAll(
                new Label("Dichte:"), densityField,
                new Label("Reibung:"), frictionField,
                staticBox
        );

        configStage.setScene(new Scene(layout));
        configStage.setTitle("Box-Konfiguration");
        configStage.show();
    }

    private static class Delta {
        double x, y;
    }

}
