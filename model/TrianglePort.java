package game.model;

import game.view.PortView;
import game.view.SquarePortView;
import game.view.TrianglePortView;

public class TrianglePort extends Port {
    public GeneralSystem system;
    public PortType portType;
    public boolean available;
    public Wire wire;

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
}
