package game.view.ports;

import game.model.ports.PortType;
import game.model.ports.TrianglePort;
import game.service.WireService;
import game.view.Root;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;

import static game.controller.Constants.PORT_SIZE;

public class TrianglePortView extends PortView {
    public TrianglePort port;
    public Color color = Color.YELLOW;
    public double x;
    public double y;

    public TrianglePortView(TrianglePort port) {
        this.port = port;
    }

    public void enableDrawLine(WireService wireService) {
        this.wireService = wireService;
        if (port.available) {
            shape.setOnMousePressed(this::startLine);
        }
    }

    public void startLine(MouseEvent e) {
        wire = new Line();
        wire.setStroke(color);
        wire.setStartX(getCenterX());
        wire.setStartY(getCenterY());
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
            Object object = e.getPickResult().getIntersectedNode();
            if (object instanceof Polygon) {
                Object target = ((Polygon) object).getUserData();
                if (target instanceof TrianglePortView) {
                    wireService.handleConnection(this.port, ((TrianglePortView) target).getPort());
                }
            }
            Root.getINSTANCE().getChildren().remove(wire);
        }
        wire = null;
    }

    @Override
    public void paint() {
        this.x = port.getX();
        this.y = port.getY();
        this.shape = new Polygon(
            x, y,
            x + PORT_SIZE/2, y + PORT_SIZE,
            x - PORT_SIZE/2, y + PORT_SIZE
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
        return x;
    }

    @Override
    public double getCenterY() {
        return y + PORT_SIZE/2;
    }

    public TrianglePort getPort() {
        return port;
    }
}

