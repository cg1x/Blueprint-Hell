package game.model.systems;

import game.model.packets.Packet;
import game.model.ports.Port;
import game.model.ports.PortType;

import java.util.ArrayList;

public final class StartSystem extends GeneralSystem {
    public double initialX;
    public double initialY;
    public boolean ready = false;

    public ArrayList<Port> inputPorts = new ArrayList<>();
    public ArrayList<Port> outputPorts = new ArrayList<>();
    public ArrayList<Packet> pendingPackets = new ArrayList<>();

    public StartSystem(double x, double y) {
        super(x, y);
        initialX = x;
        initialY = y;
    }

    public boolean isReady() {
        return ready;
    }

    public boolean canSendPacket() {
        return !pendingPackets.isEmpty();
    }

    public boolean canAcceptPacket() {
        return true;
    }

    @Override
    public void setReady(boolean ready) {
        this.ready = ready;
    }

    public ArrayList<Packet> getPendingPackets() {
        return pendingPackets;
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
