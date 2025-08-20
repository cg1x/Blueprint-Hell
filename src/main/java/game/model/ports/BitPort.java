package game.model.ports;

import game.model.systems.GeneralSystem;

import static game.controller.Constants.PORT_SIZE;

public class BitPort extends Port {
    public BitPort(GeneralSystem system, PortType portType) {
        super(system, portType);
        this.system = system;
        this.portType = portType;
        available = true;
    }

    @Override
    public double getCenterX() {
        return x + (PORT_SIZE / 2.0);
    }

    @Override
    public double getCenterY() {
        return y + (PORT_SIZE / 2.0);
    }
}
