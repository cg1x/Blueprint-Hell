package game.model;

import game.view.GeneralSystemView;
import game.view.SystemView;

import java.util.ArrayList;

public class SystemModel extends GeneralSystem {
    public double initialX;
    public double initialY;
    public SystemView systemView;
    public ArrayList<Port> inputPorts = new ArrayList<>();
    public ArrayList<Port> outputPorts = new ArrayList<>();

    public SystemModel(double x, double y) {
        super(x, y);
        initialX = x;
        initialY = y;
    }

    @Override
    public void updateIndicator() {
        int availablePorts = inputPorts.size() + outputPorts.size();

        for (Port port : inputPorts) {
            if (!(port.isAvailable())) {
                availablePorts--;
            }
        }

        for (Port port : outputPorts) {
            if (!(port.isAvailable())) {
                availablePorts--;
            }
        }

        if (availablePorts == 0) {
            systemView.turnOnIndicator();
        } else {
            systemView.turnOffIndicator();
        }
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
    public void setView(GeneralSystemView systemView) {
        this.systemView = (SystemView) systemView;
    }

    @Override
    public ArrayList<Port> getInputPorts() {
        return inputPorts;
    }

    @Override
    public ArrayList<Port> getOutputPorts() {
        return outputPorts;
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
