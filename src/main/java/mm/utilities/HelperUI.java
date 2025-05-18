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
import mm.utilities.ObjectsConf.ObjectConf;

import static mm.utilities.Makros.px_to_m_scale;


/**
 * Utility class providing helper methods to create UI elements
 * for game object configuration, like draggable, resizable, and rotatable boxes.
 */
public class HelperUI {


    /**
     * Creates a draggable, resizable, and rotatable box on the given target pane.
     * Returns an {@link ObjectConf} representing the box's configuration,
     * which updates dynamically with user interaction.
     *
     * @param targetPane the Pane to which the box will be added
     * @return an ObjectConf instance representing the configured box
     */
    public static ObjectConf addDraggableResizableRotatableBox(Pane targetPane) {
        BoxConf conf = new BoxConf();

        Rectangle rect = new Rectangle(100, 50);
        rect.setFill(Color.DODGERBLUE);
        rect.setStroke(Color.BLACK);

        Group group = new Group(rect);
        group.setLayoutX(200);
        group.setLayoutY(200);

        conf.x = 200 * px_to_m_scale;
        conf.y = 200* px_to_m_scale;
        conf.width = 100* px_to_m_scale;
        conf.height = 50* px_to_m_scale;
        conf.angle = 0;

        // Ursprung in der Mitte
        rect.setTranslateX(-rect.getWidth() / 2);
        rect.setTranslateY(-rect.getHeight() / 2);

        // Drag-Deltas kapseln
        final class Delta {
            double x, y;
        }
        Delta dragDelta = new Delta();

        group.setOnMousePressed(e -> {
            group.requestFocus(); // Tastatur-Fokus setzen
            dragDelta.x = e.getSceneX() - group.getLayoutX();
            dragDelta.y = e.getSceneY() - group.getLayoutY();
        });

        group.setOnMouseDragged(e -> {
            double newX = e.getSceneX() - dragDelta.x;
            double newY = e.getSceneY() - dragDelta.y;
            group.setLayoutX(newX);
            group.setLayoutY(newY);
            float paneHeight = (float)targetPane.getHeight();
            conf.x = (float) newX* px_to_m_scale;;
            conf.y = (paneHeight- (float)newY) * px_to_m_scale;;
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

            conf.width = (float) rect.getWidth()* px_to_m_scale*2;;
            conf.height = (float) rect.getHeight()* px_to_m_scale*2;;
            conf.angle = (float) group.getRotate();
        });

        targetPane.getChildren().add(group);

        // Konfigurationspanel anzeigen
        showBoxConfigPanel(conf);
        return conf;
    }

    /**
     * Opens a new window (stage) that allows configuring
     * physical properties of the BoxConf such as density, friction, and static state.
     *
     * @param conf the BoxConf instance to configure
     */
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


}
