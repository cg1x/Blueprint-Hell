package game.service.movement;

import game.model.packets.Packet;

public interface PacketMovementService<T extends Packet> {
    void movePacket(T packet);
}
