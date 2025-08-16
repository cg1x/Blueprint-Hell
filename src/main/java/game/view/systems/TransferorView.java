package game.view.systems;

import game.model.ports.Port;
import game.model.systems.Transferor;
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

public class TransferorView extends GeneralSystemView<Transferor> {
    public TransferorView(Transferor system) {
        super(system);
        enableDragging(false);
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

}
