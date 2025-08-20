package game.view.wire;

import static game.controller.Constants.WIRE_WIDTH;

import game.model.Wire;
import game.model.WireType;
import game.service.WireService;
import game.view.Root;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;

import java.util.ArrayList;
import java.util.List;

public class WireView {
    private Shape shape;
    private final Wire wire;
    private WireService wireService;
    private final List<Double> controlX = new ArrayList<>(5);
    private final List<Double> controlY = new ArrayList<>(5);
    private final List<Circle> controlCircles = new ArrayList<>(3);
    private Circle currentCircle;
    private List<Line> controlLines = new ArrayList<>(4);
    private final Color color;
    private boolean isDragging;
    private int currentIndex;

    public WireView(Wire wire) {
        this.wire = wire;
        controlX.add(wire.getControlX(0));
        controlY.add(wire.getControlY(0));
        controlX.add(wire.getControlX(1));
        controlY.add(wire.getControlY(1));
        if (wire.getWireType() == WireType.SQUARE) {
            color = Color.GREEN;
        } else if (wire.getWireType() == WireType.TRIANGLE) {
            color = Color.YELLOW;
        } else {
            color = Color.BLUEVIOLET;
        }
        initializeView();
    }

    private void initializeView() {
        shape = new Line(controlX.getFirst(), controlY.getFirst(),
                controlX.getLast(), controlY.getLast());
        shape.setStroke(color);
        shape.setStrokeWidth(WIRE_WIDTH);
        Root.getINSTANCE().getChildren().add(shape);
    }

    public void enableEventHandling(WireService wireService) {
        this.wireService = wireService;
        shape.setOnMousePressed(e -> {
            if (e.getButton() == MouseButton.PRIMARY) {
                if (controlX.size() < 5 && wireService.canAddNewCurve()) {
                    handleWirePress(e);
                }
            }
            else if (e.getButton() == MouseButton.SECONDARY) {
                wireService.removeWire(wire);
                removeAllComponents();
            }
        });
    }

    private void handleWirePress(MouseEvent e) {
        isDragging = true;
        createNewControlPoint(e.getX(), e.getY());
        increaseOpacity();
        Root.getINSTANCE().setOnMouseDragged(this::handleMouseDrag);
        Root.getINSTANCE().setOnMouseReleased(this::handleMouseRelease);
    }

    private void createNewControlPoint(double x, double y) {
        addControlPointToList(x, y);
        createControlLines(x, y);
        createControlCircle(x, y);
        wire.addControlX(currentIndex, x);
        wire.addControlY(currentIndex, y);
        wire.incrementControlPoints();
    }

    private void addControlPointToList(double x, double y) {
        if (controlX.getFirst() > controlX.getLast()) {
            for (int i = 0; i < controlX.size(); i++) {
                if (x > controlX.get(i)) {
                    controlX.add(i, x);
                    controlY.add(i, y);
                    currentIndex = i;
                    return;
                }
            }
        }
        for (int i = 0; i < controlX.size(); i++) {
            if (x < controlX.get(i)) {
                controlX.add(i, x);
                controlY.add(i, y);
                currentIndex = i;
                return;
            }
        }
        controlX.add(x);
        controlY.add(y);
    }

    private void createControlLines(double x, double y)  {
        switch (controlX.size()) {
            case 3: {
                Line line1 = new Line(controlX.getFirst(), controlY.getFirst(), x, y);
                styleControlLine(line1);
                controlLines.add(line1);
                Line line2 = new Line(x, y, controlX.getLast(), controlY.getLast());
                styleControlLine(line2);
                controlLines.add(line2);
                break;
            }
            case 4, 5: {
                Line line = new Line(controlX.get(currentIndex), controlY.get(currentIndex),
                        controlX.get(currentIndex + 1), controlY.get(currentIndex + 1));
                controlLines.add(currentIndex, line);
                styleControlLine(line);
                break;
            }
        }
        for (int i = 0; i < controlLines.size(); i++) {
            Line line = controlLines.get(i);
            line.setStartX(controlX.get(i));
            line.setStartY(controlY.get(i));
            line.setEndX(controlX.get(i + 1));
            line.setEndY(controlY.get(i + 1));
        }
    }

    private void createControlCircle(double x, double y) {
        currentCircle = new Circle(x, y, 5);
        currentCircle.setOnMousePressed(this::handleCirclePress);
        currentCircle.setFill(Color.CYAN);
        controlCircles.add(currentIndex - 1, currentCircle);
        Root.getINSTANCE().getChildren().add(currentCircle);
    }

    private void handleMouseDrag(MouseEvent e) {
        if (!isDragging) {
            return;
        }
        controlX.set(currentIndex, e.getX());
        controlY.set(currentIndex, e.getY());

        updateShape();
        updateControlLines();
        currentCircle.setCenterX(e.getX());
        currentCircle.setCenterY(e.getY());
    }

    private void handleMouseRelease(MouseEvent e) {
        isDragging = false;
        for (Circle circle : controlCircles) {
            circle.toFront();
        }
        wire.setControlX(currentIndex, controlX.get(currentIndex));
        wire.setControlY(currentIndex, controlY.get(currentIndex));
        wireService.updateLength(wire);
        wireService.validateWire(wire);
        reduceOpacity();
        update();
    }

    private void handleCirclePress(MouseEvent e) {
        isDragging = true;
        increaseOpacity();
        currentCircle = (Circle) e.getSource();
        currentIndex = controlCircles.indexOf(currentCircle) + 1;
        currentCircle.setOnMouseDragged(this::handleMouseDrag);
        currentCircle.setOnMouseReleased(this::handleMouseRelease);
    }

    public void updateShape() {
        switch (controlX.size()) {
            case 3:
                updateQuad();
                break;
            case 4:
                updateCubic();
                break;
            case 5:
                updateQuartic();
                break;
        }
    }

    private void updateControlLines() {
        Line line1 = controlLines.get(currentIndex - 1);
        Line line2 = controlLines.get(currentIndex);
        line1.setEndX(controlX.get(currentIndex));
        line1.setEndY(controlY.get(currentIndex));
        line2.setStartX(controlX.get(currentIndex));
        line2.setStartY(controlY.get(currentIndex));
    }

    private void updateQuad() {
        if (shape instanceof Line) {
            Color currentColor = (Color) shape.getStroke();
            Root.getINSTANCE().getChildren().remove(shape);
            createNewShape(currentColor);
        }
        ((QuadCurve) shape).setControlX(controlX.get(1));
        ((QuadCurve) shape).setControlY(controlY.get(1));
    }

    private void updateCubic() {
        if (shape instanceof QuadCurve) {
            Color currentColor = (Color) shape.getStroke();
            Root.getINSTANCE().getChildren().remove(shape);
            createNewShape(currentColor);
        }
        ((CubicCurve) shape).setControlX1(controlX.get(1));
        ((CubicCurve) shape).setControlY1(controlY.get(1));
        ((CubicCurve) shape).setControlX2(controlX.get(2));
        ((CubicCurve) shape).setControlY2(controlY.get(2));
    }

    private void updateQuartic() {
        if (shape instanceof CubicCurve) {
            Color currentColor = (Color) shape.getStroke();
            Root.getINSTANCE().getChildren().remove(shape);
            createNewShape(currentColor);
        }
        ((QuarticCurve) shape).updateCurve(controlX, controlY);
    }

    private void createNewShape(Color color) {
        switch (controlX.size()) {
            case 3: {
                shape = new QuadCurve(controlX.getFirst(), controlY.getFirst(), controlX.get(1), controlY.get(1),
                        controlX.get(2), controlY.get(2));
                styleCurve(color);
                enableEventHandling(wireService);
                break;
            }
            case 4: {
                shape = new CubicCurve(controlX.getFirst(), controlY.getFirst(), controlX.get(1), controlY.get(1),
                        controlX.get(2), controlY.get(2), controlX.get(3), controlY.get(3));
                styleCurve(color);
                enableEventHandling(wireService);
                break;
            }
            case 5: {
                shape = new QuarticCurve(controlX, controlY);
                styleCurve(color);
                enableEventHandling(wireService);
                break;
            }
        }
    }

    private void increaseOpacity() {
        for (Circle circle : controlCircles) {
            circle.setOpacity(1);
        }
        for (Line line : controlLines) {
            line.setOpacity(1);
        }
    }

    private void reduceOpacity() {
        for (Circle circle : controlCircles) {
            circle.setOpacity(0.2);
        }
        for (Line line : controlLines) {
            line.setOpacity(0.1);
        }
    }

    private void styleCurve(Color color) {
        shape.setStroke(color);
        shape.setStrokeWidth(WIRE_WIDTH);
        shape.setFill(null);
        Root.getINSTANCE().getChildren().add(shape);
        enableEventHandling(wireService);
    }

    private void styleControlLine(Line line) {
        line.setStroke(Color.CYAN);
        line.setFill(null);
        Root.getINSTANCE().getChildren().add(line);
    }

    public void update() {
        if (wire.isValid()) {
            shape.setStroke(color);
        } else {
            shape.setStroke(Color.RED);
        }
    }

    public void disableControlComponents() {
        if (controlCircles.isEmpty()) {
            return;
        }
        for (Circle circle : controlCircles) {
            circle.setVisible(false);
        }
        for (Line line : controlLines) {
            line.setVisible(false);
        }
    }

    private void removeAllComponents() {
        Root.getINSTANCE().getChildren().remove(shape);
        for (Circle circle : controlCircles) {
            Root.getINSTANCE().getChildren().remove(circle);
        }
        for (Line line : controlLines) {
            Root.getINSTANCE().getChildren().remove(line);
        }
    }

    public Shape getShape() {
        return shape;
    }

}
