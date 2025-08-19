package game.service.movement;

import game.model.Wire;
import game.model.WireType;
import game.model.packets.TrianglePacket;
import game.service.WireService;

import static game.controller.Constants.PACKET_SIZE;

public class TrianglePacketMovementService implements PacketMovementService<TrianglePacket> {
    private final WireService wireService;

    public TrianglePacketMovementService(WireService wireService) {
        this.wireService = wireService;
    }

    @Override
    public void movePacket(TrianglePacket packet) {
        if (packet.isMovingForward()) {
            movePacketForward(packet);
        } else {
            movePacketBackward(packet);
        }
    }

    private void movePacketForward(TrianglePacket packet) {
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

    private void movePacketBackward(TrianglePacket packet) {
        Wire wire = packet.getWire();
        packet.decrementT(packet.getSpeed() / wire.getLength());
        if (packet.getT() < 0) {
            packet.setT(0);
        }
        packet.setX(wireService.getPositionX(wire, packet.getT()));
        packet.setY(wireService.getPositionY(wire, packet.getT()) - PACKET_SIZE/2);
        if (packet.getWire().getWireType() == WireType.SQUARE) {
            packet.incrementSpeed();
        }
    }
}
