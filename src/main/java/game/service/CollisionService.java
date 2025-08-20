package game.service;

import static game.controller.Constants.PORT_SIZE;

import java.util.ArrayList;
import java.util.List;

import game.controller.Utils;
import game.model.GameState;
import game.model.packets.Packet;
import game.model.Collision;
import game.model.Direction;
import game.view.manager.ViewManager;

public class CollisionService {
    private ViewManager viewManager;
    private PacketService packetService;
    private final double MAGNITUDE = PORT_SIZE/2;
    private final double impactRadius = 1600;

    public CollisionService(PacketService packetService, ViewManager viewManager) {
        this.packetService = packetService;
        this.viewManager = viewManager;
    }

    public void detectCollisions(GameState gameState) {
        List<Packet> packets = gameState.getPackets();

        for (int i = 0; i < packets.size(); i++) {
            if (!packets.get(i).isOnWire()) {
                continue;
            }
            for (int j = i + 1; j < packets.size(); j++) {
                if (!packets.get(j).isOnWire()) {
                    continue;
                }
                if (packets.get(i).isCollidingWith(packets.get(j))) {
                    checkForCollisionRemoval(packets.get(i), packets.get(j));
                }
                else if (checkCollision(packets.get(i), packets.get(j))) {
                    Collision collision = handleCollision(packets.get(i), packets.get(j));
                    System.out.println("x:" + collision.getX() + ", y: " + collision.getY());
                    applyImpact(collision, packets);
                }
            }
        }
    }

    public void applyImpact(Collision collision, List<Packet> packets) {
        for (Packet packet : packets) {
            if (packet.isOnWire() && packet != collision.collidable1 && packet != collision.collidable2) {
                applyImpact(collision, packet);
            }
        }
    }
    
    public void applyImpact(Collision collision, Packet packet) {
        Direction direction = new Direction(packet, collision);
        double distance = Utils.measureDistance(packet.getX(), packet.getY(), collision.getX(), collision.getY());
        double deflectionX = direction.getX() * MAGNITUDE * (1 - distance/impactRadius);
        double deflectionY = direction.getY() * MAGNITUDE * (1 - distance/impactRadius);
        packetService.handleDeflection(packet, deflectionX, deflectionY);
    }

    public void checkForCollisionRemoval(Packet packet1, Packet packet2) {
        if (!viewManager.AreIntersecting(packet1, packet2)) {
            packet1.removeCollidable(packet2);
            packet2.removeCollidable(packet1);
        }
    }

    public boolean checkCollision(Packet packet1, Packet packet2) {
        return viewManager.AreIntersecting(packet1, packet2);
    }

    public Collision handleCollision(Packet packet1, Packet packet2) {
        packetService.reduceHealth(packet1);
        packetService.reduceHealth(packet2);
        return new Collision(packet1, packet2);
    }

}
