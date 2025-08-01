package game.model;

import game.model.collision.Collidable;
import game.model.movement.Direction;
import game.model.movement.Movable;
import game.view.TrianglePacketView;
import javafx.scene.shape.Shape;

import java.util.ArrayList;

import static game.controller.Constants.PORT_SIZE;
import static game.controller.Constants.WIRE_WIDTH;

public class TrianglePacket extends Packet implements Movable {
    public double x;
    public double y;
    public double deflectionX;
    public double deflectionY;
    public Wire wire;
    public Direction direction;
    public static ArrayList<TrianglePacket> trianglePackets = new ArrayList<>();
    public ArrayList<Collidable> collidingWith = new ArrayList<>();
    public boolean first = true;
    public int health = 3;
    public double acceleration = 0.05;
    public double speed;
    private final int rewardValue = 2;

    public TrianglePacket() {
        this.x = 0;
        this.y = 0;
    }

    @Override
    public int getRewardValue() {
        return rewardValue;
    }

    public boolean isFirst() {
        return first;
    }

    public void setSpeed() {
        if (wire.getWireType() == WireType.SQUARE) {
            this.speed = 1;
        }
        if (wire.getWireType() == WireType.TRIANGLE) {
            this.speed = 2;
        }
    }

    public void setPort(Port port) {
        x = port.getCenterX() + deflectionX;
        y = port.getCenterY() - PORT_SIZE/2 + deflectionY;
        wire = port.getWire();
        wire.setPacket(this);
        setSpeed();
        direction = new Direction(wire);
        collidables.add(this);
        first = false;
    }

    public Wire getWire() {
        return wire;
    }

    public void setWrie(Wire wire) {
        this.wire = wire;
    }

    @Override
    public boolean deflected() {
        return false;
    }

    @Override
    public int getHealth() {
        return health;
    }

    @Override
    public void reduceHealth() {
        health--;
    }

    public double getDeflectionX() {
        return deflectionX;
    }

    public double getDeflectionY() {
        return deflectionY;
    }

    @Override
    public void setDeflectionX(double deflectionX) {
        this.deflectionX += deflectionX;
        x += deflectionX;
    }

    @Override
    public void setDeflectionY(double deflectionY) {
        this.deflectionY += deflectionY;
        y += deflectionY;
    }

    @Override
    public boolean isCollidingWith(Packet packet) {
        return collidingWith.contains(packet);
    }

    @Override
    public void addCollidable(Packet packet) {
        collidingWith.add(packet);
    }

    @Override
    public void removeCollidable(Packet packet) {
        collidingWith.remove(packet);
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

    public boolean isOnWire() {
        return onWire;
    }

    public void setOnWire(boolean onWire) {
        this.onWire = onWire;
    }

    public Direction getDirection() {
        return direction;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public double getAcceleration() {
        return acceleration;
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
    public void remove() {
        collidables.remove(this);
        trianglePackets.remove(this);
    }

    @Override
    public void kill() {
        wire.getNewPacket();
        collidables.remove(this);
        trianglePackets.remove(this);
    }

    @Override
    public void setWire(Wire wire) {
        this.wire = wire;
    }
}
