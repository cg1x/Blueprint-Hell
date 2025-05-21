package game.model;

import game.view.WireView;

import java.util.ArrayList;

public class Wire {
    public Port startPort;
    public Port endPort;
    public WireType wireType;
    public WireView wireView;
    public Packet packet;
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

    public Packet getPacket() {
        return packet;
    }

    public void setPacket(Packet packet) {
        this.packet = packet;
    }

    public void getNewPacket() {
        if (startPort.getSystem().getPendingPackets().isEmpty()) {
            packet = null;
        } else {
            packet = startPort.getSystem().getPendingPackets().getFirst();
            packet.setPort(startPort);
        }
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
