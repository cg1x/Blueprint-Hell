package game.model;

import game.model.packets.Packet;
import game.model.ports.Port;

import java.util.ArrayList;
import java.util.List;

public class Wire {
    private int controlPointsCount;
    private double length;
    private List<Double> controlX = new ArrayList<>(5);
    private List<Double> controlY = new ArrayList<>(5);
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
        controlX.add(startPort.getCenterX());
        controlY.add(startPort.getCenterY());
        controlX.add(endPort.getCenterX());
        controlY.add(endPort.getCenterY());
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
    public void addControlX(int i, double x) { controlX.add(i, x); }
    public void addControlY(int i, double y) { controlY.add(i, y); }
    public double getControlX(int i) { return controlX.get(i); }
    public double getControlY(int i) { return controlY.get(i); }
    public void setControlX(int i, double x) { controlX.set(i, x); }
    public void setControlY(int i, double y) { controlY.set(i, y); }
    public int getControlPointsCount() { return controlPointsCount; }
    public double getLength() { return length; }
    public void setLength(double length) { this.length = length; }
    public boolean isValid() { return valid; }
    public void setValid(boolean valid) { this.valid = valid; }



}
