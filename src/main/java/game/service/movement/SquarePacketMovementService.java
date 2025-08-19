package game.service.movement;

import game.model.Wire;
import game.model.packets.SquarePacket;
import game.service.WireService;

import static game.controller.Constants.PACKET_SIZE;

public class SquarePacketMovementService implements PacketMovementService<SquarePacket> {
    private final WireService wireService;

    public SquarePacketMovementService(WireService wireService) {
        this.wireService = wireService;
    }

    @Override
    public void movePacket(SquarePacket packet) {
        if (packet.isMovingForward()) {
            movePacketForward(packet);
        } else {
            movePacketBackward(packet);
        }
    }

    private void movePacketForward(SquarePacket packet) {
        Wire wire = packet.getWire();
        packet.incrementT(packet.getSpeed() / wire.getLength());
        if (packet.getT() > 1) {
            packet.setT(1);
        }
        packet.setX(wireService.getPositionX(wire, packet.getT()) - PACKET_SIZE/2);
        packet.setY(wireService.getPositionY(wire, packet.getT()) - PACKET_SIZE/2);
    }

    private void movePacketBackward(SquarePacket packet) {
        Wire wire = packet.getWire();
        packet.decrementT(packet.getSpeed() / wire.getLength());
        if (packet.getT() < 0) {
            packet.setT(0);
        }
        packet.setX(wireService.getPositionX(wire, packet.getT()) - PACKET_SIZE/2);
        packet.setY(wireService.getPositionY(wire, packet.getT()) - PACKET_SIZE/2);
    }
}
