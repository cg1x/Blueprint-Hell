package game.model;

import game.view.SystemView;

import java.util.ArrayList;

public class EndSystem extends GeneralSystem {
    public double initialX;
    public double initialY;
    public SystemView systemView;
    public ArrayList<Port> inputPorts = new ArrayList<>();
    public ArrayList<Port> outputPorts = new ArrayList<>();

    public EndSystem(double x, double y) {
        super(x, y);
        initialX = x;
        initialY = y;
        outputPorts = null;
    }
    @Override
    public double getInitialY() {
        return initialY;
    }
    @Override
    public double getInitialX() {
        return initialX;
    }
    @Override
    public SystemView getView() {
        return systemView;
    }
    @Override
    public void setView(SystemView systemView) {
        this.systemView = systemView;
    }
    @Override
    public void addPort(Port port, PortType portType) {
        if (portType == PortType.INPUT) {
            inputPorts.add(port);
        } else if (portType == PortType.OUTPUT) {
            outputPorts.add(port);
        }
    }
}
