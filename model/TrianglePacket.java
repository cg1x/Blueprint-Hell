package game.model;

import game.model.collision.Collidable;
import game.model.collision.Collision;
import game.model.movement.Direction;
import game.model.movement.Movable;
import game.view.TrianglePacketView;
import javafx.scene.shape.Shape;

import java.util.ArrayList;

import static game.controller.Constants.PORT_SIZE;

public class TrianglePacket extends Packet implements Movable, Collidable {
    public double x;
    public double y;
    public double deflectionX;
    public double deflectionY;
    public Wire wire;
    public Direction direction;
    public TrianglePacketView packetView;
    public static ArrayList<TrianglePacket> trianglePackets = new ArrayList<>();
    public ArrayList<Collidable> collidingWith = new ArrayList<>();
    public double acceleration = 0.05;
    public double speed;

    public TrianglePacket() {
        deflectionX = 0;
        deflectionY = 0;
        Port port = StartSystem.getINSTANCE().getInputPorts().getFirst();
        x = port.getPortView().getCenterX();
        y = port.getPortView().getCenterY() - PORT_SIZE/2;
        wire = port.getWire();
        StartSystem.getINSTANCE().decideForPacket(this);
        packetView = new TrianglePacketView(this);
        trianglePackets.add(this);
    }

    public boolean reachedEndPort() {
        Shape shape = Shape.intersect(this.packetView.getShape(), wire.getEndPort().getPortView());
        return shape.getBoundsInLocal().getWidth() != -1;
    }

    public void setPort(Port port) {
        x = port.getPortView().getCenterX() + deflectionX;
        y = port.getPortView().getCenterY() - PORT_SIZE/2 + deflectionY;
        wire = port.getWire();
        wire.setPacket(this);
        speed = 2;
        direction = new Direction(wire);
        collidables.add(this);
    }

    public Wire getWire() {
        return wire;
    }

    @Override
    public void setDeflectionX(double deflectionX) {
        this.deflectionX = deflectionX;
        x += deflectionX;
    }

    @Override
    public void setDeflectionY(double deflectionY) {
        this.deflectionY = deflectionY;
        y += deflectionY;
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
    public TrianglePacketView getPacketView() {
        return packetView;
    }

    public double getX() {
        return x;
    }

    @Override
    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    @Override
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

    @Override
    public double getCenterX() {
        return x;
    }

    @Override
    public double getCenterY() {
        return y + PORT_SIZE/2;
    }

    @Override
    public TrianglePacket getPacket() {
        return this;
    }
}
