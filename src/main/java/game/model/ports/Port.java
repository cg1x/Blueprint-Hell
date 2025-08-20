package game.model.ports;

import game.model.Wire;
import game.model.systems.GeneralSystem;

public abstract class Port {
    protected GeneralSystem system;
    protected PortType portType;
    protected boolean available;
    protected Wire wire;
    protected double x;
    protected double y;

    public Port(GeneralSystem system, PortType portType) {
        this.system = system;
        this.portType = portType;
        available = true;
        system.addPort(this, portType);
    }

    public Wire getWire() {
        return wire;
    }

    public void setWire(Wire wire) {
        this.wire = wire;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public GeneralSystem getSystem() {
        return system;
    }

    public PortType getPortType() {
        return portType;
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

    public abstract double getCenterX();
    public abstract double getCenterY();
}
