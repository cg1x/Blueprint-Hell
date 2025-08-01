package game.service;

import game.model.SquarePacket;
import game.model.TrianglePacket;
import game.model.WireType;
import game.controller.GameController;
import game.model.collision.Collision;
import game.model.movement.Direction;

import java.util.List;
import game.model.GameState;
import game.model.Packet;

import static game.model.collision.Collidable.collidables;
import game.model.SystemModel;
import game.model.StartSystem;

public class MovementService {

    public void movePacket(SquarePacket packet) {
        double speed = packet.getSpeed();
        Direction direction = packet.getDirection();
        packet.setX(packet.getX() + direction.getX() * speed);
        packet.setY(packet.getY() + direction.getY() * speed);
    }

    public void movePacket(TrianglePacket packet) {
        double speed = packet.getSpeed();
        double acceleration = packet.getAcceleration();
        Direction direction = packet.getDirection();
        packet.setX(packet.getX() + direction.getX() * speed);
        packet.setY(packet.getY() + direction.getY() * speed);
        if (packet.getWire().getWireType() == WireType.SQUARE) {
            packet.setSpeed(packet.getSpeed() + acceleration);
        }
    }

    public void updatePackets(GameState gameState, GameController gameController) {
        List<TrianglePacket> trianglePackets = gameState.getTrianglePackets();
        List<SquarePacket> squarePackets = gameState.getSquarePackets();

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
