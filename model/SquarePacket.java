package game.model;

import game.model.collision.Collidable;
import game.model.collision.Collision;
import game.model.movement.Direction;
import game.model.movement.Movable;
import game.view.SquarePacketView;
import javafx.scene.shape.Shape;

import java.util.ArrayList;

import static game.controller.Constants.*;

public class SquarePacket extends Packet implements Movable, Collidable {
    public double x;
    public double y;
    public Wire wire;
    public Direction direction;
    public SquarePacketView packetView;
    public boolean onWire;
    public static ArrayList<SquarePacket> squarePackets = new ArrayList<>();
    public ArrayList<Collidable> collidingWith = new ArrayList<>();
    public double speed;

    public SquarePacket() {
        Port port = StartSystem.getINSTANCE().getInputPorts().getFirst();
        x = port.getPortView().getCenterX() - PORT_SIZE/2;
        y = port.getPortView().getCenterY() - PORT_SIZE/2;
        wire = port.getWire();
        StartSystem.getINSTANCE().decideForPacket(this);
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
        Shape shape = Shape.intersect(this.packetView.getShape(), wire.getEndPort().getPortView());
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
        x = port.getPortView().getCenterX() - PORT_SIZE/2;
        y = port.getPortView().getCenterY() - PORT_SIZE/2;
        wire = port.getWire();
        wire.setPacket(this);
        setSpeed();
        direction = new Direction(wire);
        collidables.add(this);
    }

    public Wire getWire() {
        return wire;
    }

    @Override
    public boolean isCollidingWith(Collidable collidable) {
        return collidingWith.contains(collidable);
    }

    @Override
    public void addCollidable(Collidable collidable) {
        collidingWith.add(collidable);
    }

    @Override
    public void removeCollidable(Collidable collidable) {
        collidingWith.remove(collidable);
    }

    @Override
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

    @Override
    public double getCenterX() {
        return x + PORT_SIZE/2;
    }

    @Override
    public double getCenterY() {
        return y + PORT_SIZE/2;
    }
}
