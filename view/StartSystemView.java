package game.view;

import game.model.*;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

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

public class StartSystemView {
    double x;
    double y;
    StartSystem system;
    ArrayList<Port> outputPorts;
    int blockCnt;
    Rectangle mainRectangle;
    Rectangle topRectangle;
    Group shape;
    Rectangle indicator;

    public StartSystemView(StartSystem system) {
        this.x = system.getInitialX();
        this.y = system.getInitialY();
        this.system = system;
        outputPorts = system.outputPorts;
        blockCnt = outputPorts.size();
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
        // paint output ports
        for (Port port : outputPorts) {
            if (port instanceof SquarePort) {
                SquarePortView portView = new SquarePortView(x + SYSTEM_SIZE - 5, y + 10 + ((outputPorts.indexOf(port) + 1) * SYSTEM_TOP_HEIGHT));
                shape.getChildren().add(portView);
            }

            if (port instanceof TrianglePort) {
                TrianglePortView portView = new TrianglePortView(x + SYSTEM_SIZE, y + 10 + ((outputPorts.indexOf(port) + 1) * SYSTEM_TOP_HEIGHT));
                shape.getChildren().add(portView);
            }
        }
        //paint run button
        Button runButton = new Button("Run");
        runButton.setPrefWidth(60);
        runButton.setPrefHeight(20);
        runButton.setLayoutX(x + 30);
        runButton.setLayoutY(y + (blockCnt + 1) * SYSTEM_TOP_HEIGHT/2 + 5);
        runButton.setFont(new Font("Verdana", 12));
        shape.getChildren().add(runButton);

        Root.getINSTANCE().getChildren().addAll(shape);
    }
}
