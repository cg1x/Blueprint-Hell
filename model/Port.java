package game.model;

public abstract class Port {
    public GeneralSystem system;
    public PortType portType;

    public Port(GeneralSystem system, PortType portType) {
        this.system = system;
        this.portType = portType;
        system.addPort(this, portType);
    }

    public GeneralSystem getSystem() {
        return system;
    }

    public PortType getPortType() {
        return portType;
    }
}
