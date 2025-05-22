package game.view;

import game.controller.Constants;
import game.controller.Controller;
import game.model.PortType;
import game.model.SquarePort;
import javafx.geometry.Bounds;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import static game.controller.Constants.PORT_SIZE;

public class SquarePortView extends PortView {

    public double x;
    public double y;
    public SquarePort port;
    public Color color = Color.GREEN;
    public WireView wire;

    public SquarePortView(double x, double y, SquarePort port) {
        super(x, y, PORT_SIZE, PORT_SIZE);
        this.x = x;
        this.y = y;
        this.port = port;
        port.setPortView(this);
        setFill(color);
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
            if (target instanceof SquarePortView && Controller.connectable(this, (SquarePortView) target)) {
                wire.setEndX(((SquarePortView) target).getCenterX());
                wire.setEndY(((SquarePortView) target).getCenterY());
                wire.setStrokeWidth(Constants.WIRE_WIDTH);
                port.available = false;
                ((SquarePortView) target).port.available = false;
                wire.setWireModel(this, (SquarePortView) target);
            } else {
                Root.getINSTANCE().getChildren().remove(wire);
            }
        }

        wire = null;
    }

    public double getCenterX() {
//        Bounds bounds = this.localToScene(this.getBoundsInLocal());
//        double x = bounds.getMinX();
        return x + PORT_SIZE/2;
    }

    public double getCenterY() {
//        Bounds bounds = this.localToScene(this.getBoundsInLocal());
//        double y = bounds.getMinY();
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
