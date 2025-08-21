package game.view.manager;

import game.model.packets.BitPacket;
import game.model.packets.Packet;
import game.model.packets.SquarePacket;
import game.model.packets.TrianglePacket;
import game.view.packets.BitPacketView;
import game.view.packets.PacketView;
import game.view.packets.SquarePacketView;
import game.view.packets.TrianglePacketView;

import java.util.HashMap;
import java.util.Map;

public class PacketViewManager {
    private final Map<Packet, PacketView> packetViews = new HashMap<>();

    public void addPacket(Packet packet) {
        if (packet instanceof SquarePacket) {
            packetViews.put(packet, new SquarePacketView((SquarePacket) packet));
        } else if (packet instanceof TrianglePacket) {
            packetViews.put(packet, new TrianglePacketView((TrianglePacket) packet));
        } else if (packet instanceof BitPacket) {
            packetViews.put(packet, new BitPacketView((BitPacket) packet));
        }
    }

    public PacketView getView(Packet packet) {
        return packetViews.get(packet);
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