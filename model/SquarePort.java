package game.model;

public class SquarePort extends Port {
    public GeneralSystem system;
    public PortType portType;

    public SquarePort(GeneralSystem system, PortType portType) {
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
