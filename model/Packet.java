package game.model;

import game.model.collision.Collidable;
import game.model.collision.Collision;
import game.model.movement.Movable;
import game.view.PacketView;

public abstract class Packet implements Movable, Collidable {
    public double x;
    public double y;
    public Wire wire;
    public PacketView packetView;

    public abstract boolean deflected();

    public abstract void kill();

    public abstract void reduceHealth();

    public abstract int getHealth();

    public abstract void setDeflectionX(double deflectionX);

    public abstract void setDeflectionY(double deflectionY);

    public abstract void setPort(Port port);

    public abstract double getX();

    public abstract void setX(double x);

    public abstract double getY();

    public abstract void setY(double y);

    @Override
    public abstract Packet getPacket();

    @Override
    public abstract void removeCollidable(Collidable collidable);

    @Override
    public abstract void addCollidable(Collidable collidable);

    @Override
    public abstract double getCenterX();

    @Override
    public abstract double getCenterY();

    @Override
    public PacketView getPacketView() {
        return packetView;
    }

    @Override
    public abstract boolean isCollidingWith(Collidable collidable);
}
