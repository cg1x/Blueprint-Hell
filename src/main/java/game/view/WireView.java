package game.view;

import static game.controller.Constants.WIRE_WIDTH;

import game.model.Wire;
import game.model.WireType;
import game.service.WireService;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.QuadCurve;
import javafx.scene.shape.Shape;

import java.util.ArrayList;
import java.util.List;

public class WireView {
    private Shape shape;
    private final Wire wire;
    private WireService wireService;
    private final double startX;
    private final double startY;
    private double controlX;
    private double controlY;
    private final double endX;
    private final double endY;
    private Circle circle;
    private List<Line> controlLines = new ArrayList<>();
    private final Color color;
    private boolean isDragging;

    public WireView(Wire wire) {
        this.wire = wire;
        startX = wire.getStartX();
        startY = wire.getStartY();
        endX = wire.getEndX();
        endY = wire.getEndY();
        if (wire.getWireType() == WireType.SQUARE) {
            color = Color.GREEN;
        } else {
            color = Color.YELLOW;
        }
        initializeView();
    }

    private void initializeView() {
        shape = new Line(startX, startY, endX, endY);
        shape.setStroke(color);
        shape.setStrokeWidth(WIRE_WIDTH);
        Root.getINSTANCE().getChildren().add(shape);
    }

    public void enableEventHandling(WireService wireService) {
        this.wireService = wireService;
        shape.setOnMousePressed(e -> {
            if (e.getButton() == MouseButton.PRIMARY) {
                handleWirePress(e);
            }
            else if (e.getButton() == MouseButton.SECONDARY) {
                wireService.removeWire(wire);
                removeAllComponents();
            }
        });
    }

    private void handleWirePress(MouseEvent e) {
        isDragging = true;
        createControlLines(e.getX(), e.getY());
        createControlCircle(e.getX(), e.getY());
        wire.incrementControlPoints();
        Root.getINSTANCE().setOnMouseDragged(this::handleMouseDrag);
        Root.getINSTANCE().setOnMouseReleased(this::handleMouseRelease);
    }

    private void createControlLines(double x, double y)  {
        Line line1 = new Line(startX, startY, x, y);
        line1.setStroke(Color.CYAN);
        line1.setFill(null);
        controlLines.add(line1);
        Line line2 = new Line(x, y, endX, endY);
        line2.setStroke(Color.CYAN);
        line2.setFill(null);
        controlLines.add(line2);
        Root.getINSTANCE().getChildren().addAll(line1, line2);
    }

    private void createControlCircle(double x, double y) {
        circle = new Circle(x, y, 5);
        circle.setOnMousePressed(this::handleCirclePress);
        circle.setFill(Color.CYAN);
        Root.getINSTANCE().getChildren().add(circle);
    }

    private void handleMouseDrag(MouseEvent e) {
        if (!isDragging) {
            return;
        }
        controlX = e.getX();
        controlY = e.getY();

        updateShape();
        updateControlLines();
        circle.setCenterX(controlX);
        circle.setCenterY(controlY);
    }

    private void handleMouseRelease(MouseEvent e) {
        isDragging = false;
        circle.toFront();
        wire.setControlX(controlX);
        wire.setControlY(controlY);
        wireService.updateLength(wire);
        wireService.validateWire(wire);
        reduceOpacity();
        update();
    }

    private void handleCirclePress(MouseEvent e) {
        isDragging = true;
        increaseOpacity();
        circle.setOnMouseDragged(this::handleMouseDrag);
        circle.setOnMouseReleased(this::handleMouseRelease);
    }

    public void updateShape() {
        if (shape instanceof Line) {
            Color currentColor = (Color) shape.getStroke();
            Root.getINSTANCE().getChildren().remove(shape);
            createNewShape(currentColor);
        }
        ((QuadCurve) shape).setControlX(controlX);
        ((QuadCurve) shape).setControlY(controlY);
    }

    private void updateControlLines() {
        controlLines.getFirst().setEndX(controlX);
        controlLines.getFirst().setEndY(controlY);
        controlLines.get(1).setStartX(controlX);
        controlLines.get(1).setStartY(controlY);
    }

    private void createNewShape(Color color) {
        shape = new QuadCurve(startX, startY, controlX, controlY, endX, endY);
        shape.setStroke(color);
        shape.setStrokeWidth(WIRE_WIDTH);
        shape.setFill(null);
        Root.getINSTANCE().getChildren().add(shape);
        enableEventHandling(wireService);
    }

    private void increaseOpacity() {
        circle.setOpacity(1);
        for (Line line : controlLines) {
            line.setOpacity(1);
        }
    }

    private void reduceOpacity() {
        circle.setOpacity(0.2);
        for (Line line : controlLines) {
            line.setOpacity(0.1);
        }
    }

    public void update() {
        if (wire.isValid()) {
            shape.setStroke(color);
        } else {
            shape.setStroke(Color.RED);
        }
    }

    public void disableControlComponents() {
        if (circle == null) {
            return;
        }
        circle.setVisible(false);
        for (Line line : controlLines) {
            line.setVisible(false);
        }
    }

    private void removeAllComponents() {
        Root.getINSTANCE().getChildren().removeAll(shape, circle);
        for (Line line : controlLines) {
            Root.getINSTANCE().getChildren().remove(line);
        }
    }

    public Shape getShape() {
        return shape;
    }

}
