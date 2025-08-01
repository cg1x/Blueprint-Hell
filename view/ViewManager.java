package game.view;

import game.model.Packet;
import game.model.Port;
import javafx.scene.shape.Shape;

public class ViewManager {
    private PacketViewManager packetViewManager;
    private WireViewManager wireViewManager;
    private PortViewManager portViewManager;

    public ViewManager(PacketViewManager packetViewManager, WireViewManager wireViewManager, PortViewManager portViewManager) {
        this.packetViewManager = packetViewManager;
        this.wireViewManager = wireViewManager;
        this.portViewManager = portViewManager;
    }   

    public boolean AreIntersecting(Packet packet, Port port) {
        //remember to try the other way
        Shape shape = Shape.intersect(packetViewManager.getView(packet).getShape(), portViewManager.getView(port).getShape());
        return shape.getBoundsInLocal().getWidth() != -1;
    }

    public boolean AreIntersecting(Packet packet1, Packet packet2) {
        Shape shape = Shape.intersect(packetViewManager.getView(packet1).getShape(), packetViewManager.getView(packet2).getShape());
        return shape.getBoundsInLocal().getWidth() != -1;
    }

    public PacketViewManager getPacketViewManager() {
        return packetViewManager;
    }

    public WireViewManager getWireViewManager() {
        return wireViewManager;
    }

    public PortViewManager getPortViewManager() {
        return portViewManager;
    }
}
