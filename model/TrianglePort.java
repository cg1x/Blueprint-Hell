package game.model;

public class TrianglePort extends Port {
    public SystemModel system;
    public PortType portType;

    public TrianglePort(SystemModel system, PortType portType) {
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
