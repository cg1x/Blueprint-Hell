package game.model;

import game.model.packets.Packet;
import game.model.ports.Port;

public class Wire {
    private double startX;
    private double startY;
    private double controlX;
    private double controlY;
    private double endX;
    private double endY;
    private int controlPointsCount;
    private double length;
    public Port startPort;
    public Port endPort;
    public WireType wireType;
    public Packet packet;
    public boolean valid;

    public Wire(Port startPort, Port endPort, WireType wireType) {
        this.startPort = startPort;
        startPort.setWire(this);
        this.endPort = endPort;
        endPort.setWire(this);
        this.wireType = wireType;
        this.startX = startPort.getCenterX();
        this.startY = startPort.getCenterY();
        this.endX = endPort.getCenterX();
        this.endY = endPort.getCenterY();
        controlPointsCount = 2;
    }

    public void incrementControlPoints() {
        controlPointsCount++;
    }

    public Packet getPacket() { return packet; }
    public void setPacket(Packet packet) { this.packet = packet; }
    public Port getStartPort() { return startPort; }
    public Port getEndPort() { return endPort; }
    public WireType getWireType() { return wireType; }
    public double getStartX() { return startX; }
    public double getStartY() { return startY; }
    public double getControlX() { return controlX; }
    public double getControlY() { return controlY; }
    public void setControlX(double x) { this.controlX = x; }
    public void setControlY(double y) { this.controlY = y; }
    public double getEndX() { return endX; }
    public double getEndY() { return endY; }
    public int getControlPointsCount() { return controlPointsCount; }
    public double getLength() { return length; }
    public void setLength(double length) { this.length = length; }
    public boolean isValid() { return valid; }
    public void setValid(boolean valid) { this.valid = valid; }
}
