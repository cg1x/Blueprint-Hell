package game.model;

public class Wire {
    public Port startPort;
    public Port endPort;
    public WireType wireType;

    public Wire(Port startPort, Port endPort, WireType wireType) {
        this.startPort = startPort;
        this.endPort = endPort;
        this.wireType = wireType;
        update();
    }

    public void update() {
        startPort.system.updateIndicator();
        endPort.system.updateIndicator();
    }

}
