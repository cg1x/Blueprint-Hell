package game.view;

import game.controller.Controller;
import game.model.PortType;
import game.model.SquarePort;
import game.model.TrianglePort;
import javafx.geometry.Bounds;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

import static game.controller.Constants.PORT_SIZE;

public class TrianglePortView extends PortView {
    double x;
    double y;
    public TrianglePort port;
    public Color color = Color.YELLOW;
    public WireView wire;

    public TrianglePortView(double x, double y, TrianglePort port) {
        super(x, y,
                x - PORT_SIZE/2, y + PORT_SIZE, x + PORT_SIZE/2, y + PORT_SIZE);
        this.x = x;
        this.y = y;
        this.port = port;
        port.setPortView(this);
        setFill(Color.YELLOW);
        setStroke(Color.BLACK);
        setStrokeWidth(2);
        Root.getINSTANCE().getChildren().add(this);
        setOnMouseEntered(e -> setOpacity(0.5));
        setOnMouseExited(e -> setOpacity(1.0));
        enableDrawLine();
    }

    public void enableDrawLine() {
        if (port.available) {
            this.setOnMousePressed(this::startLine);
        }
    }

    public void startLine(MouseEvent e) {
        wire = new WireView();
        wire.setStroke(color);
        wire.setStartX(this.getCenterX());
        wire.setStartY(this.getCenterY());
        wire.setEndX(e.getSceneX());
        wire.setEndY(e.getSceneY());
        Root.getINSTANCE().getChildren().add(wire);
        Root.getINSTANCE().setOnMouseDragged(this::dragLine);
        Root.getINSTANCE().setOnMouseReleased(this::endLine);
    }

    public void dragLine(MouseEvent e) {
        if (wire != null && port.available && (port.getPortType() == PortType.OUTPUT)) {
            wire.setEndX(e.getSceneX());
            wire.setEndY(e.getSceneY() + 1);
        }
    }

    public void endLine(MouseEvent e) {
        if (wire != null) {
            Object target = e.getPickResult().getIntersectedNode();
            if (target instanceof TrianglePortView && Controller.connectable(this, (TrianglePortView) target)) {
                wire.setEndX(((TrianglePortView) target).getCenterX());
                wire.setEndY(((TrianglePortView) target).getCenterY());
                wire.setStrokeWidth(4);
                port.available = false;
                ((TrianglePortView) target).port.available = false;
                wire.setWireModel(this, (TrianglePortView) target);

            } else {
                Root.getINSTANCE().getChildren().remove(wire);
            }
        }

        wire = null;
    }

    public double getCenterX() {
        return x ;
    }

    public double getCenterY() {
        return y + PORT_SIZE/2;
    }

    @Override
    public double getX() {
        return x;
    }

    @Override
    public double getY() {
        return y;
    }
}
