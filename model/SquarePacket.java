package game.model;

import game.model.movement.Direction;
import game.model.movement.Movable;
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
    public static ArrayList<SquarePacket> squarePackets = new ArrayList<>();
    public final double SPEED = 2;

    public SquarePacket(Port port) {
        x = port.getPortView().getX();
        y = port.getPortView().getY();
        wire = port.getWire();
        direction = new Direction(wire);
        packetView = new SquarePacketView(this);
        squarePackets.add(this);
    }

    public boolean reachedEndPort() {
        Shape shape = Shape.intersect(this.packetView.getRect(), wire.getEndPort().getPortView());
        return shape.getBoundsInLocal().getWidth() != -1;
    }

    public void setPort(Port port) {
        x = port.getPortView().getCenterX() - PORT_SIZE/2;
        y = port.getPortView().getCenterY() - PORT_SIZE/2;
        wire = port.getWire();
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
        move(direction,SPEED);
    }

}
