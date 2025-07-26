package game.view;

import game.service.WireService;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;

public abstract class PortView {
    protected Polygon shape;
    protected WireService wireService;
    protected Line wire;
    public abstract void paint();
    public abstract double getCenterX();
    public abstract double getCenterY();
    public abstract void enableDrawLine(WireService wireService);
    public Polygon getShape() { return shape; }
}
