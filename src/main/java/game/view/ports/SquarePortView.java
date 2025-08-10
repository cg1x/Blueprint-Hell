package game.view.ports;

import game.model.ports.PortType;
import game.model.ports.SquarePort;
import game.service.WireService;
import game.view.Root;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;

import static game.controller.Constants.PORT_SIZE;

public class SquarePortView extends PortView {
    public SquarePort port;
    public Color color = Color.GREEN;
    public double x;
    public double y;

    public SquarePortView(SquarePort port) {
        this.port = port;
    }

    public void enableDrawLine(WireService wireService) {
        this.wireService = wireService;
        if (port.available) {
            shape.setOnMousePressed(this::startLine);
        }
    }

    public void startLine(MouseEvent e) {
        if (e.getButton() != MouseButton.PRIMARY) {
            return;
        }
        wire = new Line(getCenterX(), getCenterY(), e.getSceneX(), e.getSceneY());
        wire.setStroke(color);
        Root.getINSTANCE().getChildren().add(wire);
        Root.getINSTANCE().setOnMouseDragged(this::dragLine);
        Root.getINSTANCE().setOnMouseReleased(this::endLine);
    }

    public void dragLine(MouseEvent e) {
        if (e.getButton() != MouseButton.PRIMARY) {
            return;
        }
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
                if (target instanceof SquarePortView) {
                    wireService.handleConnection(this.port, ((SquarePortView) target).getPort());
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

    public SquarePort getPort() {
        return port;
    }
}

