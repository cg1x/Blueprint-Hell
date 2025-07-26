package game.view;

import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;

public abstract class PacketView {
    public abstract Shape getShape();
    public abstract void update();
    public abstract void remove();
}
