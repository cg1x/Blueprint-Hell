package game.model.packets;

import game.model.Wire;

import java.util.ArrayList;

import static game.controller.Constants.PORT_SIZE;

public class TrianglePacket extends Packet {
    public double x;
    public double y;
    public double t;
    public double deflectionX;
    public double deflectionY;
    public Wire wire;
    public ArrayList<Packet> collidingWith = new ArrayList<>();
    public int health = 3;
    public double acceleration = 0.05;
    public double speed;
    private final int rewardValue = 2;

    public TrianglePacket() {
        this.x = 0;
        this.y = 0;
    }

    public void incrementSpeed() {
        speed += acceleration;
    }

    @Override
    public int getRewardValue() {
        return rewardValue;
    }

    public Wire getWire() {
        return wire;
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

    @Override
    public double getT() {
        return t;
    }

    @Override
    public void setT(double t) {
        this.t = t;
    }

    @Override
    public void incrementT(double dt) {
        t += dt;
    }

    public boolean isOnWire() {
        return onWire;
    }

    public void setOnWire(boolean onWire) {
        this.onWire = onWire;
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
    public double getCenterX() {
        return x;
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
