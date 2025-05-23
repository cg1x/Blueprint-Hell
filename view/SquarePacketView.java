package game.view;

import game.model.SquarePacket;
import javafx.application.Platform;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;

import static game.controller.Constants.PORT_SIZE;

public class SquarePacketView extends PacketView {
    public SquarePacket packet;
    public Color color = Color.GREEN;
    public Rectangle rect;

    public SquarePacketView(SquarePacket packet) {
        rect = new Rectangle(packet.getX(), packet.getY(), PORT_SIZE, PORT_SIZE);
        this.packet = packet;
        rect.setFill(color);
        rect.setStroke(color);
        rect.setStrokeWidth(2);
        rect.setVisible(false);
        Root.getINSTANCE().getChildren().add(rect);
    }

    @Override
    public Rectangle getShape() {
        return rect;
    }

    public void update() {
        rect.setX(packet.getX());
        rect.setY(packet.getY());
        rect.setVisible(!packet.getWire().getEndPort().getSystem().getPendingPackets().contains(packet));
    }

    public void remove() {
        Platform.runLater(() -> {
            Root.getINSTANCE().getChildren().remove(rect);
        });
    }
}
