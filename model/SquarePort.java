package game.model;

public class SquarePort extends Port {
    public GeneralSystem system;
    public PortType portType;
    public boolean available;

    public SquarePort(GeneralSystem system, PortType portType) {
        super(system, portType);
        this.system = system;
        this.portType = portType;
        available = true;
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
