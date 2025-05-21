package game.model;

import game.view.WireView;

import java.util.ArrayList;

public class Wire {
    public Port startPort;
    public Port endPort;
    public WireType wireType;
    public WireView wireView;
    public ArrayList<Packet> packets = new ArrayList<>();
    public boolean available = true;

    public Wire(Port startPort, Port endPort, WireType wireType, WireView wireView) {
        this.startPort = startPort;
        startPort.setWire(this);
        this.endPort = endPort;
        endPort.setWire(this);
        this.wireType = wireType;
        this.wireView = wireView;
        update();
    }

    public void addPacket(Packet packet) {
        packets.add(packet);
    }

    public Packet getOnWirePacket() {
        return packets.getFirst();
    }

    public void removePacket(Packet packet) {
        packets.remove(packet);
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public Port getStartPort() {
        return startPort;
    }

    public Port getEndPort() {
        return endPort;
    }

    public WireType getWireType() {
        return wireType;
    }

    public WireView getWireView() {
        return wireView;
    }

    public void update() {
        startPort.system.updateIndicator();
        endPort.system.updateIndicator();
    }

}
