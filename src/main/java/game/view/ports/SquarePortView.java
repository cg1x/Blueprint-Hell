package game.view.ports;

import game.model.ports.SquarePort;
import game.view.Root;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

import static game.controller.Constants.PORT_SIZE;

public class SquarePortView extends PortView {
    public SquarePortView(SquarePort port) {
        super(port);
        color = Color.GREEN;
    }

    @Override
    public void paint() {
        this.x = port.getX();
        this.y = port.getY();
        shape = new Polygon(
            x, y,
            x + PORT_SIZE, y,
            x + PORT_SIZE, y + PORT_SIZE,
            x, y + PORT_SIZE
        );
        shape.setFill(color);
        shape.setStroke(Color.BLACK);
        shape.setStrokeWidth(2);
        shape.setUserData(this);
        Root.getINSTANCE().getChildren().add(shape);
        shape.setOnMouseEntered(e -> shape.setOpacity(0.5));
        shape.setOnMouseExited(e -> shape.setOpacity(1.0));
    }

    @Override
    public double getCenterX() {
        return x + PORT_SIZE/2;
    }

    @Override
    public double getCenterY() {
        return y + PORT_SIZE/2;
    }
}

