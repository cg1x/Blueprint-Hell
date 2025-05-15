package game.view;

import game.model.SystemModel;
import javafx.scene.Group;
import javafx.scene.shape.Rectangle;

import static game.controller.Constants.*;

public abstract class ViewUtil {
    public static void paintSystem(double x, double y, SystemModel system) {
        Group shape = new Group();
        Rectangle mainRectangle = new Rectangle(x, y, SYSTEM_SIZE, SYSTEM_SIZE);
        mainRectangle.setFill(SYSTEM_COLOR);
        mainRectangle.setStroke(SYSTEM_COLOR);
        Rectangle topRectangle = new Rectangle(x, y, SYSTEM_SIZE, SYSTEM_TOP_HEIGHT);
        topRectangle.setFill(SYSTEM_TOP_COLOR);
        topRectangle.setStroke(SYSTEM_COLOR);
        shape.getChildren().addAll(mainRectangle, topRectangle);
        Root.getINSTANCE().getChildren().addAll(shape);
    }
}
