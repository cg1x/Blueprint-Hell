package game.model;

public class Wire {
    public Port startPort;
    public Port endPort;
    public WireType wireType;

    public Wire(Port startPort, Port endPort) {
        this.startPort = startPort;
        this.endPort = endPort;
    }
    

}
