package game.model.ports;

import game.model.Wire;
import game.model.systems.GeneralSystem;

import static game.controller.Constants.PORT_SIZE;

public class TrianglePort extends Port {
    public GeneralSystem system;
    public PortType portType;
    public boolean available;
    public Wire wire;
    private double x;
    private double y;

    public TrianglePort(GeneralSystem system, PortType portType) {
        super(system, portType);
        this.system = system;
        this.portType = portType;
        available = true;
    }

    @Override
    public void setWire(Wire wire) {
        super.setWire(wire);
    }

    @Override
    public Wire getWire() {
        return super.getWire();
    }

    @Override
    public boolean isAvailable() {
        return available;
    }

    @Override
    public void setAvailable(boolean available) {
        this.available = available;
    }

    @Override
    public GeneralSystem getSystem() {
        return system;
    }
    @Override
    public PortType getPortType() {
        return portType;
    }

    @Override
    public double getX() {
        return x;
    }

    @Override
    public void setX(double x) {
        this.x = x;
    }

    @Override
    public double getY() {
        return y;
    }

    @Override
    public void setY(double y) {
        this.y = y;
    }

    @Override
    public double getCenterX() {
        return x;
    }

    @Override
    public double getCenterY() {
        return y + (PORT_SIZE / 2.0);
    }
}
