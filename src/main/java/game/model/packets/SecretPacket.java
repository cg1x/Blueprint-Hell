package game.model.packets;

import game.model.Wire;

public class SecretPacket extends Packet {

    @Override
    public void reduceHealth() {

    }

    @Override
    public int getHealth() {
        return 0;
    }

    @Override
    public boolean deflected() {
        return false;
    }

    @Override
    public double getDeflectionX() {
        return 0;
    }

    @Override
    public double getDeflectionY() {
        return 0;
    }

    @Override
    public void setDeflectionX(double deflectionX) {

    }

    @Override
    public void setDeflectionY(double deflectionY) {

    }

    @Override
    public Wire getWire() {
        return null;
    }

    @Override
    public boolean isOnWire() {
        return false;
    }

    @Override
    public void setWire(Wire wire) {

    }

    @Override
    public void setOnWire(boolean onWire) {

    }

    @Override
    public double getX() {
        return 0;
    }

    @Override
    public void setX(double x) {

    }

    @Override
    public double getY() {
        return 0;
    }

    @Override
    public void setY(double y) {

    }

    @Override
    public double getT() {
        return 0;
    }

    @Override
    public void setT(double t) {

    }

    @Override
    public void incrementT(double dt) {

    }

    @Override
    public void setSpeed(double speed) {

    }

    @Override
    public int getRewardValue() {
        return 0;
    }

    @Override
    public void removeCollidable(Packet packet) {

    }

    @Override
    public void addCollidable(Packet packet) {

    }

    @Override
    public double getCenterX() {
        return 0;
    }

    @Override
    public double getCenterY() {
        return 0;
    }

    @Override
    public boolean isCollidingWith(Packet packet) {
        return false;
    }
}
