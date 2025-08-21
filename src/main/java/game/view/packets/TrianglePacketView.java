package game.view.packets;

import game.model.packets.TrianglePacket;
import game.view.Root;
import javafx.application.Platform;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

import static game.controller.Constants.PORT_SIZE;

public class TrianglePacketView extends PacketView {
    public TrianglePacketView(TrianglePacket packet) {
        super(packet);
        color = Color.YELLOW;
        paint();
    }

    public void paint() {
        shape = new Polygon(packet.getX(), packet.getY(),
                packet.getX() + PORT_SIZE/2, packet.getY() + PORT_SIZE,
                packet.getX() - PORT_SIZE/2, packet.getY() + PORT_SIZE);
        shape.setFill(color);
        shape.setStroke(color);
        shape.setStrokeWidth(2);
        shape.setVisible(false);
        Root.getINSTANCE().getChildren().add(shape);
    }
}
