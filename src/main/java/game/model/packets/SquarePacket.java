package game.model.packets;

import game.model.Direction;
import game.model.Wire;

import java.util.ArrayList;
import static game.controller.Constants.*;

public class SquarePacket extends Packet {
    public double x;
    public double y;
    public double deflectionX;
    public double deflectionY;
    public Wire wire;
    public Direction direction;
    public boolean onWire;
    public ArrayList<Packet> collidingWith = new ArrayList<>();
    public int health = 2;
    public double speed;
    private final int rewardValue = 1;

    public SquarePacket() {
        this.x = 0;
        this.y = 0;
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

    public void setSpeed(double speed) {
        this.speed = speed;
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
    public double getCenterX() {
        return x + PORT_SIZE/2;
    }

    @Override
    public double getCenterY() {
        return y + PORT_SIZE/2;
    }

    @Override
    public void setWire(Wire wire) {
        this.wire = wire;
    }
}
