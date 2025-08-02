package game.view.manager;

import game.model.packets.Packet;
import game.model.ports.Port;
import game.view.packets.PacketView;
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
        PacketView view1 = packetViewManager.getView(packet1);
        PacketView view2 = packetViewManager.getView(packet2);
        if (view1 == null || view2 == null) {
            return false;
        }
        if (!(view1.getShape().isVisible() && view2.getShape().isVisible())) {
            return false;
        }
        Shape shape = Shape.intersect(view1.getShape(), view2.getShape());
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
