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
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import mm.utilities.ObjectsConf.BallConf;
import mm.utilities.ObjectsConf.BoxConf;
import mm.utilities.ObjectsConf.ObjectConf;
import org.jbox2d.common.Vec2;

import static mm.utilities.Makros.*;


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
    public static ObjectConf addDraggableBox(Pane targetPane) {

        float height = (float)targetPane.getHeight();

        BoxConf conf = new BoxConf();

        Rectangle rect = new Rectangle(100, 50);
        rect.setFill(Color.DODGERBLUE);
        rect.setStroke(Color.BLACK);

        // Ursprung in der Mitte des Rechtecks
        rect.setX(-rect.getWidth() / 2);
        rect.setY(-rect.getHeight() / 2);

        // Anfangsposition im Pane
        rect.setLayoutX(200);
        rect.setLayoutY(200);

        // Initialwerte im Conf-Objekt
        conf.x = (GAMEPANE_HEIGHT - 200) * px_to_m_scale;
        conf.y = 200 * px_to_m_scale;
        conf.width = 100 * px_to_m_scale;
        conf.height = 50 * px_to_m_scale;
        conf.angle = 0;

        // Drag-Deltas
        final class Delta {
            double x, y;
        }
        Delta dragDelta = new Delta();

        rect.setOnMousePressed(e -> {
            rect.requestFocus();
            dragDelta.x = e.getSceneX() - rect.getLayoutX();
            dragDelta.y = e.getSceneY() - rect.getLayoutY();
        });



        rect.setOnMouseDragged(e -> {
            double newX = e.getSceneX() - dragDelta.x;
            double newY = e.getSceneY() - dragDelta.y;

            double angle = Math.toRadians(rect.getRotate());
            double w = rect.getWidth();
            double h = rect.getHeight();

            // Effektive Breite und Höhe nach Rotation (Bounding Box)
            double rotatedWidth = Math.abs(w * Math.cos(angle)) + Math.abs(h * Math.sin(angle));
            double rotatedHeight = Math.abs(w * Math.sin(angle)) + Math.abs(h * Math.cos(angle));

            // Halbe Breite/Höhe für spätere Begrenzung (da Mittelpunkt gesetzt wird)
            double halfW = rotatedWidth / 2;
            double halfH = rotatedHeight / 2;

            // Begrenzung
            double clampedX = Math.max(halfW, Math.min(targetPane.getWidth() - halfW, newX));
            double clampedY = Math.max(halfH, Math.min(targetPane.getHeight() - halfH, newY));

            rect.setLayoutX(clampedX);
            rect.setLayoutY(clampedY);

            System.out.printf("layoutX: %.2f, layoutY: %.2f%n", rect.getLayoutX(), rect.getLayoutY());
            System.out.printf("conf.x: %.2f, conf.y: %.2f%n", conf.x, conf.y);
            System.out.println(height - newY);
        });

        rect.setOnKeyPressed(e -> {
            switch (e.getCode()) {
                case LEFT:
                    rect.setRotate(rect.getRotate() - 5);
                    break;
                case RIGHT:
                    rect.setRotate(rect.getRotate() + 5);
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
            rect.setX(-rect.getWidth() / 2);
            rect.setY(-rect.getHeight() / 2);

            // Berechnung Bounding-Box
            double angle = Math.toRadians(rect.getRotate());
            double w = rect.getWidth();
            double h = rect.getHeight();

            double rotatedWidth = Math.abs(w * Math.cos(angle)) + Math.abs(h * Math.sin(angle));
            double rotatedHeight = Math.abs(w * Math.sin(angle)) + Math.abs(h * Math.cos(angle));
            double halfW = rotatedWidth / 2;
            double halfH = rotatedHeight / 2;

            // Begrenzung der Position
            double clampedX = Math.max(halfW, Math.min(targetPane.getWidth() - halfW, rect.getLayoutX()));
            double clampedY = Math.max(halfH, Math.min(targetPane.getHeight() - halfH, rect.getLayoutY()));
            rect.setLayoutX(clampedX);
            rect.setLayoutY(clampedY);

            conf.width = (float) rect.getWidth() * px_to_m_scale;
            conf.height = (float) rect.getHeight() * px_to_m_scale;
            conf.angle = (float) -rect.getRotate();
            conf.x = (float) clampedX * px_to_m_scale;
            conf.y = (float)(targetPane.getHeight() - clampedY) * px_to_m_scale;
        });

        targetPane.getChildren().add(rect);
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

    public static BallConf addDraggableBall(Pane targetPane) {

        float height = (float) targetPane.getHeight();

        float initialRadius = 25; // px

        Circle circle = new Circle(initialRadius, Color.ORANGE);
        circle.setStroke(Color.BLACK);

        // Anfangsposition
        double initialX = 200;
        double initialY = 200;
        circle.setLayoutX(initialX);
        circle.setLayoutY(initialY);

        BallConf conf = new BallConf(
                (float) initialX * px_to_m_scale,
                (float) (height - initialY) * px_to_m_scale,
                initialRadius * px_to_m_scale
        );

        // Drag helper
        final class Delta {
            double x, y;
        }
        Delta dragDelta = new Delta();

        circle.setOnMousePressed(e -> {
            circle.requestFocus();
            dragDelta.x = e.getSceneX() - circle.getLayoutX();
            dragDelta.y = e.getSceneY() - circle.getLayoutY();
        });

        circle.setOnMouseDragged(e -> {
            double newX = e.getSceneX() - dragDelta.x;
            double newY = e.getSceneY() - dragDelta.y;
            double r = circle.getRadius();

            // Grenzen berechnen
            double clampedX = Math.max(r, Math.min(targetPane.getWidth() - r, newX));
            double clampedY = Math.max(r, Math.min(targetPane.getHeight() - r, newY));

            circle.setLayoutX(clampedX);
            circle.setLayoutY(clampedY);

            conf.x = (float) clampedX * px_to_m_scale;
            conf.y = (float) (height - clampedY) * px_to_m_scale;

            System.out.printf("Ball pos: layoutX=%.2f layoutY=%.2f -> conf.x=%.3f conf.y=%.3f%n",
                    clampedX, clampedY, conf.x, conf.y);
        });

        // Radius ändern über Tastatur (z. B. W/S)
        circle.setOnKeyPressed(e -> {
            double newRadius = circle.getRadius();
            switch (e.getCode()) {
                case W:
                    newRadius += 2;
                    break;
                case S:
                    newRadius -= 2;
                    break;
                default:
                    return;
            }
            circle.setRadius(newRadius);
            conf.radius = (float) (newRadius * px_to_m_scale);

            // Ball-Position ggf. korrigieren, damit er nicht über Rand steht
            double cx = circle.getLayoutX();
            double cy = circle.getLayoutY();

            double clampedX = Math.max(newRadius, Math.min(targetPane.getWidth() - newRadius, cx));
            double clampedY = Math.max(newRadius, Math.min(targetPane.getHeight() - newRadius, cy));

            circle.setLayoutX(clampedX);
            circle.setLayoutY(clampedY);

            conf.x = (float) clampedX * px_to_m_scale;
            conf.y = (float)(height - clampedY) * px_to_m_scale;
        });

        targetPane.getChildren().add(circle);
        return conf;
    }


    public static Vec2 addDraggableStartPoint(Pane targetPane) {
        Vec2 startpoint = new Vec2();

        float height = (float) targetPane.getHeight();

        float initialRadius = 5; // px

        Circle circle = new Circle(initialRadius, Color.RED);
        circle.setStroke(Color.BLACK);

        double initialX = 200;
        double initialY = 200;
        circle.setLayoutX(initialX);
        circle.setLayoutY(initialY);

        // Drag helper
        final class Delta {
            double x, y;
        }
        Delta dragDelta = new Delta();

        circle.setOnMousePressed(e -> {
            circle.requestFocus();
            dragDelta.x = e.getSceneX() - circle.getLayoutX();
            dragDelta.y = e.getSceneY() - circle.getLayoutY();
        });

        circle.setOnMouseDragged(e -> {
            double newX = e.getSceneX() - dragDelta.x;
            double newY = e.getSceneY() - dragDelta.y;

            double r = RADIUS_GAMEBALL_PX;

            // Begrenzung, sodass Gameball nicht aus Pane ragt
            double clampedX = Math.max(r, Math.min(targetPane.getWidth() - r, newX));
            double clampedY = Math.max(r, Math.min(targetPane.getHeight() - r, newY));

            circle.setLayoutX(clampedX);
            circle.setLayoutY(clampedY);

            startpoint.x = (float) clampedX * px_to_m_scale;
            startpoint.y = (float)(height - clampedY) * px_to_m_scale;

            System.out.printf("StartPoint pos: layoutX=%.2f layoutY=%.2f -> conf.x=%.3f conf.y=%.3f%n",
                    clampedX, clampedY, startpoint.x, startpoint.y);
        });
        targetPane.getChildren().add(circle);
        return startpoint;
    }


}
