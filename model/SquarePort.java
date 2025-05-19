package game.model;

import game.view.PortView;
import game.view.SquarePortView;

public class SquarePort extends Port {
    public GeneralSystem system;
    public PortType portType;
    public boolean available;
    public SquarePortView portView;
    public Wire wire;

    public SquarePort(GeneralSystem system, PortType portType) {
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
    public SquarePortView getPortView() {
        return portView;
    }

    @Override
    public void setPortView(PortView portView) {
        this.portView = (SquarePortView) portView;
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
