package game.model;

public abstract class Port {
    public SystemModel system;
    public PortType portType;

    public Port(SystemModel system, PortType portType) {
        this.system = system;
        this.portType = portType;
        system.addPort(this, portType);
    }

    public SystemModel getSystem() {
        return system;
    }

    public PortType getPortType() {
        return portType;
    }
}
