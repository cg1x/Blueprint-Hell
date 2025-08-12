package game.service;

import game.model.Wire;
import game.model.packets.SquarePacket;
import game.model.packets.TrianglePacket;
import game.model.WireType;

import static game.controller.Constants.PACKET_SIZE;

public class MovementService {
    private WireService wireService;

    public MovementService(WireService wireService) {
        this.wireService = wireService;
    }

    public void movePacket(SquarePacket packet) {
        Wire wire = packet.getWire();
        packet.incrementT(packet.getSpeed() / wire.getLength());
        if (packet.getT() > 1) {
            packet.setT(1);
        }
        packet.setX(wireService.getPositionX(wire, packet.getT()) - PACKET_SIZE/2);
        packet.setY(wireService.getPositionY(wire, packet.getT()) - PACKET_SIZE/2);
    }

    public void movePacket(TrianglePacket packet) {
        Wire wire = packet.getWire();
        packet.incrementT(packet.getSpeed() / wire.getLength());
        if (packet.getT() > 1) {
            packet.setT(1);
        }
        packet.setX(wireService.getPositionX(wire, packet.getT()));
        packet.setY(wireService.getPositionY(wire, packet.getT()) - PACKET_SIZE/2);
        if (packet.getWire().getWireType() == WireType.SQUARE) {
            packet.incrementSpeed();
        }
    }
}
