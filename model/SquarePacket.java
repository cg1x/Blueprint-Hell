package game.model;

import game.model.collision.Collidable;
import game.model.movement.Direction;
import game.model.movement.Movable;
import game.view.SquarePacketView;
import javafx.scene.shape.Shape;

import java.util.ArrayList;

import static game.controller.Constants.*;

public class SquarePacket extends Packet implements Movable {
    public double x;
    public double y;
    public double deflectionX;
    public double deflectionY;
    public Wire wire;
    public Direction direction;
    public boolean first = true;
    public boolean onWire;
    public static ArrayList<SquarePacket> squarePackets = new ArrayList<>();
    public ArrayList<Collidable> collidingWith = new ArrayList<>();
    public int health = 2;
    public double speed;
    private final int rewardValue = 1;

    public SquarePacket() {
        this.x = 0;
        this.y = 0;
    }

    public boolean isFirst() {
        return first;
    }

    public boolean isOnWire() {
        return onWire;
    }

    public void setOnWire(boolean onWire) {
        this.onWire = onWire;
    }

    @Override
    public int getRewardValue() {
        return rewardValue;
    }


    public boolean reachedEndPort() {
        return false;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public void setSpeed() {
        if (wire.getWireType() == WireType.SQUARE) {
            this.speed = 2;
        }
        if (wire.getWireType() == WireType.TRIANGLE) {
            this.speed = 4;
        }
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

    public void setPort(Port port) {
        x = port.getCenterX() - PORT_SIZE/2 + deflectionX;
        y = port.getCenterY() - PORT_SIZE/2 + deflectionY;
        wire = port.getWire();
        wire.setPacket(this);
        setSpeed();
        direction = new Direction(wire);
        collidables.add(this);
        first = false;
    }

    public void setWrie(Wire wire) {
        this.wire = wire;
    }

    public Wire getWire() {
        return wire;
    }

    public double getDeflectionX() {
        return deflectionX;
    }

    public double getDeflectionY() {
        return deflectionY;
    }

    public void setDeflectionX(double deflectionX) {
        this.deflectionX += deflectionX;
        x += deflectionX;
    }

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

    public Direction getDirection() {
        return direction;
    }

    public double getSpeed() {
        return speed;
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


    @Override
    public void remove() {
        collidables.remove(this);
        squarePackets.remove(this);
    }

    @Override
    public void kill() {
        wire.getNewPacket();
        collidables.remove(this);
        squarePackets.remove(this);
    }

    @Override
    public void setWire(Wire wire) {
        this.wire = wire;
    }
}
