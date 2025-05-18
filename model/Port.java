package game.model;

public abstract class Port {
    public GeneralSystem system;
    public PortType portType;
    public boolean available;

    public Port(GeneralSystem system, PortType portType) {
        this.system = system;
        this.portType = portType;
        available = true;
        system.addPort(this, portType);
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public GeneralSystem getSystem() {
        return system;
    }

    public PortType getPortType() {
        return portType;
    }
}
