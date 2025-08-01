package game.model;

import game.model.collision.Collidable;
import game.model.collision.Collision;
import game.model.movement.Movable;

public abstract class Packet implements Movable, Collidable {
    public double x;
    public double y;
    protected Wire wire;
    protected boolean onWire;

    public abstract void remove();

    public abstract boolean deflected();

    public abstract void kill();

    public abstract void reduceHealth();

    public abstract int getHealth();

    public abstract double getDeflectionX();

    public abstract double getDeflectionY();

    public abstract void setDeflectionX(double deflectionX);

    public abstract void setDeflectionY(double deflectionY);

    public abstract void setPort(Port port);

    public abstract double getX();

    public abstract Wire getWire();

    public abstract boolean isOnWire();

    public abstract void setWire(Wire wire);

    public abstract void setOnWire(boolean onWire);

    public abstract void setX(double x);

    public abstract double getY();

    public abstract void setY(double y);

    public abstract void setSpeed(double speed);

    public abstract int getRewardValue();

    public abstract void removeCollidable(Packet packet);

    public abstract void addCollidable(Packet packet);

    public abstract double getCenterX();

    public abstract double getCenterY();

    public abstract boolean isCollidingWith(Packet packet);
}
