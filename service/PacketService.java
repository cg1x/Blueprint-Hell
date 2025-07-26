package game.service;

import game.model.GameState;
import game.model.PacketType;
import game.model.SquarePacket;
import game.model.TrianglePacket;
import game.view.PacketViewManager;
import game.model.Packet;
import java.util.ArrayList;
import java.util.List;

public class PacketService {
    private MovementService movementService;

    public PacketService(MovementService movementService) {
        this.movementService = movementService;
    }

    public void movePackets(GameState gameState) {

    }

    public Packet createPacket(PacketType packetType, GameState gameState, PacketViewManager manager) {
        Packet packet = null;
        if (packetType == PacketType.SQUARE) {
            packet = new SquarePacket();
            gameState.addSquarePacket((SquarePacket) packet);
        } else if (packetType == PacketType.TRIANGLE) {
            packet = new TrianglePacket();
            gameState.addTrianglePacket((TrianglePacket) packet);
        }
        manager.addPacket(packet);
        return packet;
    }

    public List<Packet> createPacketsForLevel(int level, GameState gameState, PacketViewManager manager) {
        List<Packet> packets = new ArrayList<>();
        if (level == 1) {
            packets.add(createPacket(PacketType.TRIANGLE, gameState, manager));
            packets.add(createPacket(PacketType.SQUARE, gameState, manager));
            packets.add(createPacket(PacketType.TRIANGLE, gameState, manager));
            packets.add(createPacket(PacketType.SQUARE, gameState, manager));
            packets.add(createPacket(PacketType.TRIANGLE, gameState, manager));
            packets.add(createPacket(PacketType.SQUARE, gameState, manager));
        } else if (level == 2) {
            for (int i = 0; i < 5; i++) {
                packets.add(createPacket(PacketType.TRIANGLE, gameState, manager));
                packets.add(createPacket(PacketType.SQUARE, gameState, manager));
            }
        }
        return packets;
    }

    public void removePacket(Packet packet, PacketViewManager manager) {
        manager.removePacket(packet);
        packet.remove();
    }
}
