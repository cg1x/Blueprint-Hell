package game.view.ports;

import game.model.ports.Port;
import game.model.ports.PortType;
import game.service.WireService;
import game.view.Root;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;

public abstract class PortView {
    protected Port port;
    protected double x;
    protected double y;
    protected Shape shape;
    protected WireService wireService;
    protected Line wire;
    protected Color color;

    public PortView(Port port) {
        this.port = port;
    }

    public void enableDrawLine(WireService wireService) {
        this.wireService = wireService;
        if (port.isAvailable()) {
            shape.setOnMousePressed(this::startLine);
        }
    }

    public void startLine(MouseEvent e) {
        wire = new Line(getCenterX(), getCenterY(), e.getSceneX(), e.getSceneY());
        wire.setStroke(color);
        Root.getINSTANCE().getChildren().add(wire);
        Root.getINSTANCE().setOnMouseDragged(this::dragLine);
        Root.getINSTANCE().setOnMouseReleased(this::endLine);
    }

    public void dragLine(MouseEvent e) {
        if (wire != null && port.isAvailable() && (port.getPortType() == PortType.OUTPUT)) {
            wire.setEndX(e.getSceneX());
            wire.setEndY(e.getSceneY() - 2);
        }
    }

    public void endLine(MouseEvent e) {
        if (wire != null) {
            Object object = e.getPickResult().getIntersectedNode();
            if (object instanceof Polygon) {
                Object target = ((Polygon) object).getUserData();
                if (target instanceof PortView) {
                    wireService.handleConnection(this.port, ((PortView) target).getPort());
                }
            }
            Root.getINSTANCE().getChildren().remove(wire);
        }
        wire = null;
    }

    public Port getPort() {
        return port;
    }

    public Shape getShape() {
        return shape;
    }

    public abstract void paint();
    public abstract double getCenterX();
    public abstract double getCenterY();
}
