package game.model;

public class SquarePort extends Port {
    public SystemModel system;
    public PortType portType;

    public SquarePort(SystemModel system, PortType portType) {
        super(system, portType);
    }

    @Override
    public SystemModel getSystem() {
        return system;
    }
    @Override
    public PortType getPortType() {
        return portType;
    }
}
