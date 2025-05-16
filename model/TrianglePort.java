package game.model;

public class TrianglePort extends Port {
    public GeneralSystem system;
    public PortType portType;

    public TrianglePort(GeneralSystem system, PortType portType) {
        super(system, portType);
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
