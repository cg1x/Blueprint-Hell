package game.view;

import game.controller.Constants;
import game.model.SquarePort;
import javafx.geometry.Bounds;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import static game.controller.Constants.PORT_SIZE;

public class SquarePortView extends Rectangle {

    SquarePort port;
    Color color = Color.GREEN;
    WireView wire;

    public SquarePortView(double x, double y, SquarePort port) {
        super(x, y, PORT_SIZE, PORT_SIZE);
        this.port = port;
        setFill(color);
        setStroke(Color.BLACK);
        setStrokeWidth(2);
        Root.getINSTANCE().getChildren().add(this);
        setOnMouseEntered(e -> setOpacity(0.5));
        setOnMouseExited(e -> setOpacity(1.0));
        enableDrawLine(true);
    }

    public void enableDrawLine(boolean bool) {
        if (bool) {
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
        if (wire != null) {
            wire.setEndX(e.getSceneX() + 1);
            wire.setEndY(e.getSceneY() + 1);
        }
    }

    public void endLine(MouseEvent e) {
        if (wire != null) {
            Object target = e.getPickResult().getIntersectedNode();
            if (target instanceof SquarePortView) {
                wire.setEndX(((SquarePortView) target).getCenterX());
                wire.setEndY(((SquarePortView) target).getCenterY());
                wire.setStrokeWidth(4);


            } else {
                Root.getINSTANCE().getChildren().remove(wire);
                enableDrawLine(false);

            }
        }

        wire = null;
    }


    public double getCenterX() {
        Bounds bounds = this.localToScene(this.getBoundsInLocal());
        double x = bounds.getMinX();
        return x + PORT_SIZE/2;
    }

    public double getCenterY() {
        Bounds bounds = this.localToScene(this.getBoundsInLocal());
        double y = bounds.getMinY();
        return y + PORT_SIZE/2;
    }

}
