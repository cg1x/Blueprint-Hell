package game.service;

import game.model.SquarePacket;
import game.model.TrianglePacket;
import game.controller.GameController;
import game.model.collision.Collision;
import java.util.List;
import game.model.GameState;
import static game.model.collision.Collidable.collidables;
import game.model.SystemModel;
import game.model.StartSystem;

public class MovementService {
    private SystemService systemService = new SystemService();

    public void updatePackets(GameState gameState, GameController gameController) {
        List<TrianglePacket> trianglePackets = gameState.getTrianglePackets();
        List<SquarePacket> squarePackets = gameState.getSquarePackets();
        for (int i = 0; i < trianglePackets.size(); i++) {
            TrianglePacket packet = trianglePackets.get(i);
            if (packet.getWire().getEndPort().getSystem().getPendingPackets().contains(packet)) {
                continue;
            }
            packet.move();
            if (packet.reachedEndPort()) {
                packet.getWire().getNewPacket();
                if (packet.getWire().getEndPort().getSystem() instanceof SystemModel) {
                    systemService.decideForPacket((SystemModel) packet.getWire().getEndPort().getSystem(), packet, gameController);
                } else if (packet.getWire().getEndPort().getSystem() instanceof StartSystem) {
                    systemService.decideForPacket((StartSystem) packet.getWire().getEndPort().getSystem(), packet, gameController);
                }
            }
        }
        for (int i = 0; i < squarePackets.size(); i++) {
            SquarePacket packet = squarePackets.get(i);
            if (packet.getWire().getEndPort().getSystem().getPendingPackets().contains(packet)) {
                continue;
            }
            packet.move();
            if (packet.reachedEndPort()) {
                packet.getWire().getNewPacket();
                if (packet.getWire().getEndPort().getSystem() instanceof SystemModel) {
                    systemService.decideForPacket((SystemModel) packet.getWire().getEndPort().getSystem(), packet, gameController);
                } else if (packet.getWire().getEndPort().getSystem() instanceof StartSystem) {
                    systemService.decideForPacket((StartSystem) packet.getWire().getEndPort().getSystem(), packet, gameController);
                }
            }
        }
        for (int i = 0; i < collidables.size(); i++) {
            for (int j = i + 1; j < collidables.size(); j++) {
                Collision collision = collidables.get(i).collides(collidables.get(j));
                if (collision != null) {
                    collision.applyImpact();
                }
            }
        }
    }
}
