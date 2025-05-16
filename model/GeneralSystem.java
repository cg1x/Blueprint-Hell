package game.model;

import game.view.SystemView;

import java.util.ArrayList;

public abstract class GeneralSystem {
    public double initialX;
    public double initialY;
    public SystemView systemView;
    public ArrayList<Port> inputPorts = new ArrayList<>();
    public ArrayList<Port> outputPorts = new ArrayList<>();

    public GeneralSystem(double x, double y) {
        initialX = x;
        initialY = y;
    }

    public double getInitialY() {
        return initialY;
    }

    public double getInitialX() {
        return initialX;
    }

    public SystemView getView() {
        return systemView;
    }

    public void setView(SystemView systemView) {
        this.systemView = systemView;
    }

    public void addPort(Port port, PortType portType) {
        if (portType == PortType.INPUT) {
            inputPorts.add(port);
        } else if (portType == PortType.OUTPUT) {
            outputPorts.add(port);
        }
    }
}
