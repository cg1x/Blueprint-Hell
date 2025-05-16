package game.view;

import game.model.*;
import javafx.scene.Group;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;

import static game.controller.Constants.*;
import static game.controller.Constants.INDICATOR_HEIGHT;
import static game.controller.Constants.INDICATOR_MARGIN;
import static game.controller.Constants.INDICATOR_WIDTH;
import static game.controller.Constants.SYSTEM_COLOR;
import static game.controller.Constants.SYSTEM_SIZE;
import static game.controller.Constants.SYSTEM_TOP_COLOR;
import static game.controller.Constants.SYSTEM_TOP_HEIGHT;

public class EndSystemView {
    double x;
    double y;
    EndSystem system;
    ArrayList<Port> inputPorts;
    int blockCnt;
    Rectangle mainRectangle;
    Rectangle topRectangle;
    Group shape;
    Rectangle indicator;

    public EndSystemView(EndSystem system) {
        this.x = system.getInitialX();
        this.y = system.getInitialY();
        this.system = system;
        inputPorts = system.inputPorts;
        blockCnt = inputPorts.size();
        paint();
        enableDragging();
    }

    public GeneralSystem getModel() {
        return system;
    }

    public void enableDragging() {
        AtomicReference<Double> offsetX = new AtomicReference<>((double) 0);
        AtomicReference<Double> offsetY = new AtomicReference<>((double) 0);
        shape.setOnMousePressed((MouseEvent e) -> {
            offsetX.set(e.getX());
            offsetY.set(e.getY());
        });

        shape.setOnMouseDragged((MouseEvent e) -> {
            shape.setLayoutX(e.getSceneX() - offsetX.get());
            shape.setLayoutY(e.getSceneY() - offsetY.get());
        });
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
        // paint input ports
        for (Port port : inputPorts) {
            if (port instanceof SquarePort) {
                SquarePortView portView = new SquarePortView(x - 5, y + 10 + ((inputPorts.indexOf(port) + 1) * SYSTEM_TOP_HEIGHT));
                shape.getChildren().addAll(portView);
            }

            if (port instanceof TrianglePort) {
                TrianglePortView portView = new TrianglePortView(x, y + 10 + ((inputPorts.indexOf(port) + 1)* SYSTEM_TOP_HEIGHT));
                shape.getChildren().addAll(portView);
            }
        }
        Root.getINSTANCE().getChildren().addAll(shape);
    }
}
