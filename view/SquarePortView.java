package game.view;

import game.controller.Constants;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class SquarePortView extends Rectangle {

    public SquarePortView(double x, double y) {
        super(x, y, Constants.PORT_SIZE, Constants.PORT_SIZE);
        setFill(Color.GREEN);
        setStroke(Color.BLACK);
        setStrokeWidth(2);
        Root.getINSTANCE().getChildren().add(this);
        setOnMouseEntered(e -> setOpacity(0.5));
        setOnMouseExited(e -> setOpacity(1.0));
    }

}
