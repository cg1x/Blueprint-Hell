package game.model.systems;

import game.model.packets.Packet;
import game.model.ports.Port;
import game.model.ports.PortType;

import java.util.ArrayList;

public abstract class GeneralSystem {
    protected double initialX;
    protected double initialY;
    protected ArrayList<Port> inputPorts = new ArrayList<>();
    protected ArrayList<Port> outputPorts = new ArrayList<>();
    protected ArrayList<Packet> pendingPackets = new ArrayList<>();
    protected boolean ready;
    protected boolean active;
    protected int deactivationPeriod;

    public GeneralSystem(double x, double y) {
        initialX = x;
        initialY = y;
        ready = false;
        active = true;
        deactivationPeriod = 0;
    }

    public ArrayList<Packet> getPendingPackets() {
        return pendingPackets;
    }

    public void addPendingPacket(Packet packet) {
        pendingPackets.add(packet);
    }

    public void removePendingPacket(Packet packet) {
        pendingPackets.remove(packet);
    }

    public double getInitialY() {
        return initialY;
    }

    public double getInitialX() {
        return initialX;
    }

    public ArrayList<Port> getInputPorts() {
        return inputPorts;
    }

    public ArrayList<Port> getOutputPorts() {
        return outputPorts;
    }

    public abstract boolean canSendPacket();

    public abstract boolean canAcceptPacket();

    public void addPort(Port port, PortType portType) {
        if (portType == PortType.INPUT) {
            inputPorts.add(port);
        } else if (portType == PortType.OUTPUT) {
            outputPorts.add(port);
        }
    }

    public boolean isReady() {
        return ready;
    }

    public void setReady(boolean ready) {
        this.ready = ready;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public int getDeactivationPeriod() {
        return deactivationPeriod;
    }

    public void setDeactivationPeriod(int t) {
        deactivationPeriod = t;
    }

    public void decrementDeactivationPeriod() {
        deactivationPeriod--;
    }
}
