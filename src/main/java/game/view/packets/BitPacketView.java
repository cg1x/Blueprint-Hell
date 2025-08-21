package game.view.packets;

import game.model.packets.BitPacket;
import game.model.packets.SquarePacket;
import game.view.Root;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;

import static game.controller.Constants.PORT_SIZE;

public class BitPacketView extends PacketView {
    public BitPacketView(BitPacket packet) {
        super(packet);
        color = Color.BLUEVIOLET;
        paint();
    }

    public void paint() {
        shape = new Rectangle(packet.getX(), packet.getY(), PORT_SIZE, PORT_SIZE);
        Rotate rotate = new Rotate(45, packet.getCenterX(), packet.getCenterY());
        shape.getTransforms().add(rotate);
        shape.setFill(color);
        shape.setStroke(color);
        shape.setStrokeWidth(2);
        shape.setVisible(false);
        Root.getINSTANCE().getChildren().add(shape);
    }
}
