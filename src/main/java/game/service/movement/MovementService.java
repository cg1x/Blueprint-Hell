package game.service.movement;

import game.model.packets.BitPacket;
import game.model.packets.Packet;
import game.model.packets.SquarePacket;
import game.model.packets.TrianglePacket;
import game.service.WireService;

import java.util.HashMap;
import java.util.Map;

public class MovementService {
    private final Map<Class<? extends Packet>, PacketMovementService<? extends Packet>> movementServices;

    public MovementService(WireService wireService) {
        this.movementServices = new HashMap<>();
        this.movementServices.put(SquarePacket.class, new SquarePacketMovementService(wireService));
        this.movementServices.put(TrianglePacket.class, new TrianglePacketMovementService(wireService));
        this.movementServices.put(BitPacket.class, new BitPacketMovementService(wireService));
    }

    @SuppressWarnings("unchecked")
    public void movePacket(Packet packet) {
        PacketMovementService<Packet> service = (PacketMovementService<Packet>) movementServices.get(packet.getClass());
        service.movePacket(packet);
    }

    public boolean hasPacketReachedSystem(Packet packet) {
        if (packet.isMovingForward()) {
            return packet.getT() == 1;
        } else {
            return packet.getT() == 0;
        }
    }
}
