package game.view.systems;

import game.model.ports.Port;
import game.model.systems.SystemModel;
import game.view.Root;
import game.view.ports.SquarePortView;
import javafx.scene.Group;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

import static game.controller.Constants.*;

public class SystemView extends GeneralSystemView {
    double x;
    double y;
    SystemModel system;
    ArrayList<Port> inputPorts;
    ArrayList<Port> outputPorts;
    int blockCnt;
    Rectangle mainRectangle;
    Rectangle topRectangle;
    Group shape;
    Group shapeWithoutPorts;
    Rectangle indicator;

    public SystemView(SystemModel system) {
        this.x = system.getInitialX();
        this.y = system.getInitialY();
        this.system = system;
        inputPorts = system.getInputPorts();
        outputPorts = system.getOutputPorts();
        blockCnt = Math.max(inputPorts.size(), outputPorts.size());
        enableDragging(false);
    }

    public SystemModel getModel() {
        return system;
    }

    public void enableDragging(boolean bool) {
        if (bool) {
            AtomicReference<Double> offsetX = new AtomicReference<>((double) 0);
            AtomicReference<Double> offsetY = new AtomicReference<>((double) 0);
            AtomicBoolean allowed = new AtomicBoolean(false);
            shape.setOnMousePressed((MouseEvent e) -> {
                offsetX.set(e.getX());
                offsetY.set(e.getY());
                Object target = e.getPickResult().getIntersectedNode();
                allowed.set(!(target instanceof SquarePortView));
            });
            shape.setOnMouseDragged((MouseEvent e) -> {
                if (allowed.get()) {
                    shape.setLayoutX(e.getSceneX() - offsetX.get());
                    shape.setLayoutY(e.getSceneY() - offsetY.get());
                }
            });
        }
    }

    @Override
    public void turnOnIndicator() {
        indicator.setFill(Color.CYAN);
    }
    @Override
    public void turnOffIndicator() {
        indicator.setFill(SYSTEM_TOP_COLOR);
    }

    public void paint() {
        shape = new Group();
        // painting the main rectangle
        mainRectangle = new Rectangle(x, y, SYSTEM_SIZE, (blockCnt + 1) * SYSTEM_TOP_HEIGHT);
        mainRectangle.setFill(SYSTEM_COLOR);
        mainRectangle.setStroke(SYSTEM_COLOR);
        // painting the top rectangle
        topRectangle = new Rectangle(x, y, SYSTEM_SIZE, SYSTEM_TOP_HEIGHT);
        topRectangle.setFill(SYSTEM_TOP_COLOR);
        topRectangle.setStroke(SYSTEM_COLOR);
        //painting the indicator
        indicator = new Rectangle(x + INDICATOR_MARGIN, y + INDICATOR_MARGIN, INDICATOR_WIDTH, INDICATOR_HEIGHT);
        indicator.setFill(SYSTEM_TOP_COLOR);
        indicator.setStroke(SYSTEM_COLOR);
        shape.getChildren().addAll(mainRectangle, topRectangle, indicator);
        Root.getINSTANCE().getChildren().addAll(shape);
    }

    public Shape getShape() { return mainRectangle; }
}
