package game.view;

import javafx.scene.shape.Polygon;

public abstract class PortView extends Polygon {
    double x;
    double y;

    public PortView(double x1, double y1, double x2, double y2, double x3, double y3) {
        super(x1, y1, x2, y2, x3, y3);
        x = x1;
        y = y1;
    }
    public PortView(double x, double y, double width, double height) {
        super(x, y, x + width, y, x + width, y + height, x, y + height);
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public abstract double getCenterX();

    public abstract double getCenterY();

}
