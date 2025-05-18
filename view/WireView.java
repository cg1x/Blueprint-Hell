package game.view;

import game.model.Port;
import game.model.Wire;
import game.model.WireType;
import javafx.scene.input.MouseButton;
import javafx.scene.shape.Line;

public class WireView extends Line {
    Wire wire;
    Port startPort;
    Port endPort;

    public WireView() {
        this.setOnMouseClicked(e -> {
            if (e.getButton() == MouseButton.SECONDARY) {
                startPort.setAvailable(true);
                endPort.setAvailable(true);
                wire.update();
                Root.getINSTANCE().getChildren().remove(this);
            }
        });
    }

    public void setWireModel(SquarePortView startPort, SquarePortView endPort) {
        wire = new Wire(startPort.port, endPort.port, WireType.SQUARE);
        this.startPort = startPort.port;
        this.endPort = endPort.port;
    }

    public void setWireModel(TrianglePortView startPort, TrianglePortView endPort) {
        wire = new Wire(startPort.port, endPort.port, WireType.TRIANGLE);
        this.startPort = startPort.port;
        this.endPort = endPort.port;
    }

}
