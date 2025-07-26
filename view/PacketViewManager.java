package game.view;

import game.model.Packet;
import game.model.SquarePacket;
import game.model.TrianglePacket;

import java.util.HashMap;
import java.util.Map;

public class PacketViewManager {
    private final Map<Packet, PacketView> packetViews = new HashMap<>();

    public void addPacket(Packet packet) {
        if (packet instanceof SquarePacket) {
            packetViews.put(packet, new SquarePacketView((SquarePacket) packet));
        } else if (packet instanceof TrianglePacket) {
            packetViews.put(packet, new TrianglePacketView((TrianglePacket) packet));
        }
    }

    public void removePacket(Packet packet) {
        PacketView view = packetViews.remove(packet);
        if (view != null) {
            view.remove();
        }
    }

    public void updateAll() {
        for (PacketView view : packetViews.values()) {
            view.update();
        }
    }

    public void clear() {
        for (PacketView view : packetViews.values()) {
            view.remove();
        }
        packetViews.clear();
    }
} 