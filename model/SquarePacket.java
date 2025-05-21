package game.model;

import game.model.movement.Direction;
import game.model.movement.Movable;
import game.view.Root;
import game.view.SquarePacketView;
import javafx.scene.shape.Shape;

import java.util.ArrayList;

import static game.controller.Constants.*;

public class SquarePacket extends Packet implements Movable {
    public double x;
    public double y;
    public Wire wire;
    public Direction direction;
    public SquarePacketView packetView;
    public boolean onWire;
    public static ArrayList<SquarePacket> squarePackets = new ArrayList<>();
    public double speed;

    public SquarePacket(Port port) {
        x = port.getPortView().getCenterX() - PORT_SIZE/2;
        y = port.getPortView().getCenterY() - PORT_SIZE/2;
        wire = port.getWire();
        wire.addPacket(this);
        setSpeed();
        direction = new Direction(wire);
        packetView = new SquarePacketView(this);
        squarePackets.add(this);
    }

    public boolean isOnWire() {
        return onWire;
    }

    public void setOnWire(boolean onWire) {
        this.onWire = onWire;
    }

    public boolean reachedEndPort() {
        Shape shape = Shape.intersect(this.packetView.getRect(), wire.getEndPort().getPortView());
        return shape.getBoundsInLocal().getWidth() != -1;
    }

    public void setSpeed() {
        if (wire.getWireType() == WireType.SQUARE) {
            this.speed = 2;
        }
        if (wire.getWireType() == WireType.TRIANGLE) {
            this.speed = 4;
        }
    }

    public void setPort(Port port) {
        wire.removePacket(this);
        x = port.getPortView().getCenterX() - PORT_SIZE/2;
        y = port.getPortView().getCenterY() - PORT_SIZE/2;
        wire = port.getWire();
        wire.addPacket(this);
        setSpeed();
        direction = new Direction(wire);
    }

    public Wire getWire() {
        return wire;
    }

    public SquarePacketView getPacketView() {
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
    }

    @Override
    public void move() {
        move(direction, speed);
    }

}
