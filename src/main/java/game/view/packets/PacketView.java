package game.view.packets;

import game.model.packets.Packet;
import game.view.Root;
import javafx.application.Platform;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

public abstract class PacketView {
    protected double initialX;
    protected double initialY;
    protected Packet packet;
    protected Color color;
    protected Shape shape;

    public PacketView(Packet packet) {
        this.packet = packet;
        this.initialX = packet.getX();
        this.initialY = packet.getY();
    }

    public Shape getShape() {
        return shape;
    }

    public void update() {
        shape.setLayoutX(packet.getX() - initialX);
        shape.setLayoutY(packet.getY() - initialY);
        shape.setVisible(packet.isOnWire());
    }

    public void remove() {
        Platform.runLater(() -> {
            Root.getINSTANCE().getChildren().remove(shape);
        });
    }

    public abstract void paint();
}
