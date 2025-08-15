package game.view.systems;

import game.model.systems.GeneralSystem;
import game.view.Root;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

import static game.controller.Constants.*;
import static game.controller.Constants.INDICATOR_HEIGHT;
import static game.controller.Constants.INDICATOR_MARGIN;
import static game.controller.Constants.INDICATOR_WIDTH;
import static game.controller.Constants.SYSTEM_COLOR;
import static game.controller.Constants.SYSTEM_SIZE;
import static game.controller.Constants.SYSTEM_TOP_HEIGHT;

public abstract class GeneralSystemView<System extends GeneralSystem> {
    protected double x;
    protected double y;
    protected System system;
    protected int blockCnt;
    protected Rectangle mainRectangle;
    protected Rectangle topRectangle;
    protected Group shape;
    protected Rectangle indicator;

    public GeneralSystemView(System system) {
        this.system = system;
        this.x = system.getInitialX();
        this.y = system.getInitialY();
        initializeBlockCount();
    }

    public void initializeBlockCount() {
        int inputCnt = system.getInputPorts().size();
        int outputCnt = system.getOutputPorts().size();
        blockCnt = Math.max(inputCnt, outputCnt);
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

    public void turnOnIndicator() {
        indicator.setFill(Color.CYAN);
    }

    public void turnOffIndicator() {
        indicator.setFill(SYSTEM_TOP_COLOR);
    }

    public Shape getShape() { return mainRectangle; }

    public System getModel() { return system; }
}
