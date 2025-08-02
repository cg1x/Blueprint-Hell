package game.model.systems;

import game.model.packets.Packet;
import game.model.ports.Port;

import java.util.ArrayList;

public class SystemModel extends GeneralSystem {
    public double initialX;
    public double initialY;
    public boolean ready = false;
    public ArrayList<Port> inputPorts = new ArrayList<>();
    public ArrayList<Port> outputPorts = new ArrayList<>();
    public ArrayList<Packet> pendingPackets = new ArrayList<>();

    public SystemModel(double x, double y) {
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
        return pendingPackets.size() < 5;
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
}
