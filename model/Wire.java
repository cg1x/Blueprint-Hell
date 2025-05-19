package game.model;

import game.view.WireView;

public class Wire {
    public Port startPort;
    public Port endPort;
    public WireType wireType;
    public WireView wireView;

    public Wire(Port startPort, Port endPort, WireType wireType, WireView wireView) {
        this.startPort = startPort;
        startPort.setWire(this);
        this.endPort = endPort;
        endPort.setWire(this);
        this.wireType = wireType;
        this.wireView = wireView;
        update();
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
