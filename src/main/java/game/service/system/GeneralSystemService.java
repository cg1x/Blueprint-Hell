package game.service.system;


import game.model.GameState;
import game.model.GameStats;
import game.model.packets.Packet;
import game.model.packets.SquarePacket;
import game.model.packets.TrianglePacket;
import game.model.ports.Port;
import game.model.ports.SquarePort;
import game.model.ports.TrianglePort;
import game.model.systems.GeneralSystem;
import game.service.PacketService;

import static game.controller.Constants.PORT_SIZE;

public abstract class GeneralSystemService {
    protected GameState gameState;

    public GeneralSystemService(GameState gameState) {
        this.gameState = gameState;
    }

    public void handlePacketReached(Packet packet, PacketService packetService) {
        GameStats gameStats = gameState.getGameStats();
        gameStats.addCoins(packet.getRewardValue());
    }

    public void sendNewPacketTo(Port port) {
        GeneralSystem system = port.getSystem();
        if (system.canSendPacket()) {
            Packet packet = system.getPendingPackets().removeFirst();
            assignPacketToPort(packet, port);
        } else {
            port.getWire().setPacket(null);
        }
    }

    public void assignPacketToPort(Packet packet, Port port) {
        switch (packet) {
            case SquarePacket s -> assignSquarePacketToPort((SquarePacket) packet, port);
            case TrianglePacket t -> assignTrianglePacketToPort((TrianglePacket) packet, port);
            default -> {
                break;
            }
        }
        refreshPacket(packet, port);
    }

    private void assignSquarePacketToPort(SquarePacket packet, Port port) {
        packet.setX(port.getCenterX() - PORT_SIZE/2 + packet.getDeflectionX());
        packet.setY(port.getCenterY() - PORT_SIZE/2 + packet.getDeflectionY());
        if (port instanceof SquarePort) {
            packet.setSpeed(2);
        }
        if (port instanceof TrianglePort) {
            packet.setSpeed(4);
        }
    }

    private void assignTrianglePacketToPort(TrianglePacket packet, Port port) {
        packet.setX(port.getCenterX() - PORT_SIZE/2 + packet.getDeflectionX());
        packet.setY(port.getCenterY() - PORT_SIZE/2 + packet.getDeflectionY());
        if (port instanceof SquarePort) {
            packet.setSpeed(2);
        }
        if (port instanceof TrianglePort) {
            packet.setSpeed(4);
        }
    }

    private void refreshPacket(Packet packet, Port port) {
        packet.setT(0);
        packet.setWire(port.getWire());
        packet.getWire().setPacket(packet);
        packet.setOnWire(true);
    }
}
