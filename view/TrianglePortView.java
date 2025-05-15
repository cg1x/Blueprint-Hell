package game.view;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

import static game.controller.Constants.PORT_SIZE;

public class TrianglePortView extends Polygon {
    public TrianglePortView(double x, double y) {
        super(x, y,
                x - PORT_SIZE/2, y + PORT_SIZE, x + PORT_SIZE/2, y + PORT_SIZE);
        setFill(Color.YELLOW);
        setStroke(Color.BLACK);
        setStrokeWidth(2);
        Root.getINSTANCE().getChildren().add(this);
        setOnMouseEntered(e -> setOpacity(0.5));
        setOnMouseExited(e -> setOpacity(1.0));
    }
}
