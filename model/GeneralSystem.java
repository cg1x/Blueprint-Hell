package game.model;

import game.view.GeneralSystemView;
import game.view.SystemView;

import java.util.ArrayList;

public abstract class GeneralSystem {
    public double initialX;
    public double initialY;
    public GeneralSystemView systemView;
    public ArrayList<Port> inputPorts = new ArrayList<>();
    public ArrayList<Port> outputPorts = new ArrayList<>();
    public ArrayList<Packet> pendingPackets = new ArrayList<>();

    public GeneralSystem(double x, double y) {
        initialX = x;
        initialY = y;
    }

    public abstract void decideForPacket(TrianglePacket packet);
    public abstract void decideForPacket(SquarePacket packet);

    public ArrayList<Packet> getPendingPackets() {
        return pendingPackets;
    }

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

    public double getInitialY() {
        return initialY;
    }

    public double getInitialX() {
        return initialX;
    }

    public GeneralSystemView getView() {
        return systemView;
    }

    public void setView(GeneralSystemView systemView) {
        this.systemView = systemView;
    }

    public ArrayList<Port> getInputPorts() {
        return inputPorts;
    }

    public ArrayList<Port> getOutputPorts() {
        return outputPorts;
    }

    public void addPort(Port port, PortType portType) {
        if (portType == PortType.INPUT) {
            inputPorts.add(port);
        } else if (portType == PortType.OUTPUT) {
            outputPorts.add(port);
        }
    }

}
