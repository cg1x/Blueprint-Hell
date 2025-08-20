package game.model.packets;

import game.model.Wire;

import java.util.ArrayList;
import java.util.List;

public abstract class Packet {
    protected double x;
    protected double y;
    protected double t;
    protected double deflectionX;
    protected double deflectionY;
    protected List<Packet> collidingWith;
    protected Wire wire;
    protected boolean onWire;
    protected boolean trojan;
    protected int health;
    protected double speed;
    protected double acceleration;
    protected boolean movingForward;
    protected int initialHealth;
    protected int rewardValue;

    public Packet() {
        x = 0;
        y = 0;
        collidingWith = new ArrayList<>();
        trojan = false;
        movingForward = true;
    }

    public void reduceHealth() {
        health--;
    }

    public int getHealth() {
        return health;
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

    public Wire getWire() {
        return wire;
    }

    public boolean isOnWire() {
        return onWire;
    }

    public void setWire(Wire wire) {
        this.wire = wire;
    }

    public void setOnWire(boolean onWire) {
        this.onWire = onWire;
    }

    public boolean isTrojan() {
        return trojan;
    }

    public void setTrojan(boolean b) {
        this.trojan = b;
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

    public double getT() {
        return t;
    }

    public void setT(double t) {
        this.t = t;
    }

    public void incrementT(double dt) {
        t += dt;
    }

    public void decrementT(double dt) {
        t -= dt;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public void incrementSpeed() {
        speed += acceleration;
    }

    public int getRewardValue() {
        return rewardValue;
    }

    public void removeCollidable(Packet packet) {
        collidingWith.remove(packet);
    }

    public void addCollidable(Packet packet) {
        collidingWith.add(packet);
    }

    public double getAcceleration() {
        return acceleration;
    }

    public void setAcceleration(double acceleration) {
        this.acceleration = acceleration;
    }

    public boolean isCollidingWith(Packet packet) {
        return collidingWith.contains(packet);
    }

    public int getInitialHealth() {
        return initialHealth;
    }

    public boolean isMovingForward() {
        return movingForward;
    }

    public void setMovingForward(boolean movingForward) {
        this.movingForward = movingForward;
    }

    public abstract double getCenterX();

    public abstract double getCenterY();
}
