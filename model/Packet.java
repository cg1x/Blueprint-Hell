package game.model;

import game.model.movement.Movable;

public abstract class Packet implements Movable {
    public double x;
    public double y;
    public Wire wire;

    public abstract void setPort(Port port);

}
