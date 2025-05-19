package game.view;

import game.model.SquarePacket;
import game.model.TrianglePacket;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;

import static game.controller.Constants.PORT_SIZE;

public class TrianglePacketView extends PacketView {
    double x;
    double y;
    public TrianglePacket packet;
    public Color color = Color.YELLOW;
    public Polygon shape;

    public TrianglePacketView(TrianglePacket packet) {
        shape = new Polygon(packet.getX(), packet.getY(),
                packet.getX() + PORT_SIZE/2, packet.getY() + PORT_SIZE,
                packet.getX() - PORT_SIZE/2, packet.getY() + PORT_SIZE);
        this.x = packet.getX();
        this.y = packet.getY();
        this.packet = packet;
        shape.setFill(color);
        shape.setStroke(color);
        shape.setStrokeWidth(2);
        Root.getINSTANCE().getChildren().add(shape);
    }

    public Polygon getShape() {
        return shape;
    }

    public void update() {
        shape.setLayoutX(packet.getX() - x);
        shape.setLayoutY(packet.getY() - y);
    }
}
