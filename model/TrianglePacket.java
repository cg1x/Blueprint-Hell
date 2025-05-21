package game.model;

import game.model.movement.Direction;
import game.model.movement.Movable;
import game.view.TrianglePacketView;
import javafx.scene.shape.Shape;

import java.util.ArrayList;

import static game.controller.Constants.PORT_SIZE;

public class TrianglePacket extends Packet implements Movable {
    public double x;
    public double y;
    public Wire wire;
    public Direction direction;
    public TrianglePacketView packetView;
    public static ArrayList<TrianglePacket> trianglePackets = new ArrayList<>();
    public double acceleration = 0.05;
    public double speed;

    public TrianglePacket(Port port) {
        x = port.getPortView().getCenterX();
        y = port.getPortView().getCenterY() - PORT_SIZE/2;
        wire = port.getWire();
        wire.setPacket(this);
        speed = 2;
        direction = new Direction(wire);
        packetView = new TrianglePacketView(this);
        trianglePackets.add(this);
    }

    public boolean reachedEndPort() {
        Shape shape = Shape.intersect(this.packetView.getShape(), wire.getEndPort().getPortView());
        return shape.getBoundsInLocal().getWidth() != -1;
    }

    public void setPort(Port port) {
        x = port.getPortView().getCenterX();
        y = port.getPortView().getCenterY() - PORT_SIZE/2;
        wire = port.getWire();
        wire.setPacket(this);
        speed = 2;
        direction = new Direction(wire);
    }

    public Wire getWire() {
        return wire;
    }

    public TrianglePacketView getPacketView() {
        return packetView;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    @Override
    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    @Override
    public void move(Direction direction, double speed) {
        this.x += direction.getX() * speed;
        this.y += direction.getY() * speed;
        if (wire.getWireType() == WireType.SQUARE) {
            this.speed += acceleration;
        }
    }

    @Override
    public void move() {
        move(direction, speed);
    }
}
