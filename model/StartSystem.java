package game.model;

import game.view.GeneralSystemView;
import game.view.StartSystemView;

import java.util.ArrayList;

import static game.model.collision.Collidable.collidables;


public final class StartSystem extends GeneralSystem {

    private static StartSystem INSTANCE;
    public double initialX;
    public double initialY;
    public boolean ready = false;

    public ArrayList<Port> inputPorts = new ArrayList<>();
    public ArrayList<Port> outputPorts = new ArrayList<>();
    public ArrayList<Packet> pendingPackets = new ArrayList<>();

    public StartSystem(double x, double y) {
        super(x, y);
        INSTANCE = this;
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

    public static StartSystem getINSTANCE() {
        return INSTANCE;
    }
}
