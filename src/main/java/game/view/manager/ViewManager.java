package game.view.manager;

import game.model.Wire;
import game.model.packets.Packet;
import game.model.systems.GeneralSystem;
import game.view.packets.PacketView;
import javafx.scene.shape.Shape;

import static game.controller.Constants.PORT_SIZE;

public class ViewManager {
    private PacketViewManager packetViewManager;
    private WireViewManager wireViewManager;
    private PortViewManager portViewManager;
    private SystemViewManager systemViewManager;

    public ViewManager(PacketViewManager packetViewManager, WireViewManager wireViewManager,
                       PortViewManager portViewManager, SystemViewManager systemViewManager) {
        this.packetViewManager = packetViewManager;
        this.wireViewManager = wireViewManager;
        this.portViewManager = portViewManager;
        this.systemViewManager = systemViewManager;
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

    public boolean AreIntersecting(Wire wire, GeneralSystem system) {
        Shape shape = Shape.intersect(wireViewManager.getView(wire).getShape(), systemViewManager.getView(system).getShape());
        return shape.getBoundsInLocal().getWidth() > PORT_SIZE/2;
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
