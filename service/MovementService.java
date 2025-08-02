package game.service;

import game.model.packets.SquarePacket;
import game.model.packets.TrianglePacket;
import game.model.WireType;
import game.model.Direction;

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
}
