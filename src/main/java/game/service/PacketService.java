package game.service;

import game.model.GameState;
import game.model.GameStats;
import game.model.packets.PacketType;
import game.model.packets.SquarePacket;
import game.model.systems.StartSystem;
import game.model.systems.SystemModel;
import game.model.packets.TrianglePacket;
import game.view.manager.PacketViewManager;
import game.view.manager.ViewManager;
import game.model.packets.Packet;

import static game.controller.Constants.PORT_SIZE;
import static game.controller.Constants.WIRE_WIDTH;

import java.util.ArrayList;
import java.util.List;

public class PacketService {
    private MovementService movementService;
    private ViewManager viewManager;
    private SystemService systemService;
    private GameState gameState;
    private PacketViewManager manager;

    public PacketService(GameState gameState, SystemService systemService,
                         MovementService movementService, ViewManager viewManager) {
        this.gameState = gameState;
        this.systemService = systemService;
        this.viewManager = viewManager;
        this.manager = viewManager.getPacketViewManager();
        this.movementService = movementService;
    }

    public void movePackets(GameState gameState) {
        List<SquarePacket> squarePackets = gameState.getSquarePackets();
        List<TrianglePacket> trianglePackets = gameState.getTrianglePackets();
        for (int i = 0; i < squarePackets.size(); i++) {
            SquarePacket packet = squarePackets.get(i);
            if (packet.isOnWire()) {
                movementService.movePacket(packet);
                if (packet.getT() == 1) {
                    if (packet.getWire().getEndPort().getSystem().canAcceptPacket()) {
                        handlePacketReached(packet);
                    } else {
                        handlePacketLost(packet);
                    }
                }
            }
        }
        for (int i = 0; i < trianglePackets.size(); i++) {
            TrianglePacket packet = trianglePackets.get(i);
            if (packet.isOnWire()) {
                movementService.movePacket(packet);
                if (packet.getT() == 1) {
                    if (packet.getWire().getEndPort().getSystem().canAcceptPacket()) {
                        handlePacketReached(packet);
                    } else {
                        handlePacketLost(packet);
                    }
                }
            }
        }

    }

    public void handlePacketReached(Packet packet) {
        GameStats gameStats = gameState.getGameStats();
        systemService.sendNewPacketTo(packet.getWire().getStartPort());
        gameStats.addCoins(packet.getRewardValue());
        if (packet.getWire().getEndPort().getSystem() instanceof SystemModel) {
            systemService.decideForPacket((SystemModel) packet.getWire().getEndPort().getSystem(), packet);
        } else if (packet.getWire().getEndPort().getSystem() instanceof StartSystem) {
            gameStats.incrementSuccessfulPackets();
            gameStats.decrementInNetworkPackets();
            removePacket(packet);
        }
    }

    public void handlePacketLost(Packet packet) {
        GameStats gameStats = gameState.getGameStats();
        removePacket(packet);
        systemService.sendNewPacketTo(packet.getWire().getStartPort()); 
        gameStats.incrementLostPackets();
        gameStats.decrementInNetworkPackets();
    }

    public void handleDeflection(Packet packet, double deflectionX, double deflectionY) {
        packet.setDeflectionX(deflectionX);
        if (packet.getDeflectionX() > (PORT_SIZE + WIRE_WIDTH) / 2) {
            handlePacketLost(packet);
        }
        packet.setDeflectionY(deflectionY);
        if (packet.getDeflectionY() > (PORT_SIZE + WIRE_WIDTH) / 2) {
            handlePacketLost(packet);
        }
    }

    public Packet createPacket(PacketType packetType) {
        Packet packet = null;
        if (packetType == PacketType.SQUARE) {
            packet = new SquarePacket();
            gameState.addSquarePacket((SquarePacket) packet);
        } else if (packetType == PacketType.TRIANGLE) {
            packet = new TrianglePacket();
            gameState.addTrianglePacket((TrianglePacket) packet);
        }
        gameState.getSystems().getFirst().getPendingPackets().add(packet);
        manager.addPacket(packet);
        return packet;
    }

    public void createPacketsForLevel(int level) {
        List<Packet> packets = new ArrayList<>();
        if (level == 1) {
            packets.add(createPacket(PacketType.TRIANGLE));
            packets.add(createPacket(PacketType.SQUARE));
            packets.add(createPacket(PacketType.TRIANGLE));
            packets.add(createPacket(PacketType.SQUARE));
            packets.add(createPacket(PacketType.TRIANGLE));
            packets.add(createPacket(PacketType.SQUARE));
        } else if (level == 2) {
            for (int i = 0; i < 5; i++) {
                packets.add(createPacket(PacketType.TRIANGLE));
                packets.add(createPacket(PacketType.SQUARE));
            }
        }
        
        int totalPackets = gameState.getTrianglePackets().size() + gameState.getSquarePackets().size();
        gameState.getGameStats().setTotalPackets(totalPackets);
    }

    public void reduceHealth(Packet packet) {
        packet.reduceHealth();
        if (packet.getHealth() == 0) {
            handlePacketLost(packet);
        }
    }

    public void removePacket(Packet packet) {
        gameState.removePacket(packet);
        viewManager.getPacketViewManager().removePacket(packet);
    }
}
