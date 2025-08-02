package game.model;

import game.model.packets.Packet;
import game.model.ports.Port;
import game.view.WireView;

public class Wire {
    private double startX;
    private double startY;
    private double endX;
    private double endY;
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
        this.startX = startPort.getCenterX();
        this.startY = startPort.getCenterY();
        this.endX = endPort.getCenterX();
        this.endY = endPort.getCenterY();
    }

    public Packet getPacket() { return packet; }
    public void setPacket(Packet packet) { this.packet = packet; }
    public Port getStartPort() { return startPort; }
    public Port getEndPort() { return endPort; }
    public WireType getWireType() { return wireType; }
    public double getStartX() { return startX; }
    public double getStartY() { return startY; }
    public double getEndX() { return endX; }
    public double getEndY() { return endY; }
}
