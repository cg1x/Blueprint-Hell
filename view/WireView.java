package game.view;

import static game.controller.Constants.WIRE_WIDTH;

import game.model.Port;
import game.model.Wire;
import game.model.WireType;
import game.service.WireService;
import javafx.scene.input.MouseButton;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class WireView {
    private Line shape;
    Wire wire;
    Port startPort;
    Port endPort;

    public WireView(Wire wire) {
        this.wire = wire;
        shape = new Line(wire.getStartX(), wire.getStartY(), wire.getEndX(), wire.getEndY());
        if (wire.getWireType() == WireType.SQUARE) {
            shape.setStroke(Color.GREEN);
        } else if (wire.getWireType() == WireType.TRIANGLE) {
            shape.setStroke(Color.YELLOW);
        }
        shape.setStrokeWidth(WIRE_WIDTH);
        Root.getINSTANCE().getChildren().add(shape);
    }

    public void enableWireRemoval(WireService wireService) {
        shape.setOnMouseClicked(e -> {
            if (e.getButton() == MouseButton.SECONDARY) {
                wireService.removeWire(wire);
                Root.getINSTANCE().getChildren().remove(shape);
            }
        });
    }

}
