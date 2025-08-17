package game.service.system;

import game.model.GameState;
import game.model.packets.Packet;
import game.model.ports.Port;
import game.model.systems.Transferor;
import game.service.PacketService;

public class TransferorService extends GeneralSystemService {
    private PortFinder portFinder;

    public TransferorService(GameState gameState) {
        super(gameState);
        portFinder = new PortFinder();
    }

    public void handlePacketReached(Packet packet, PacketService packetService) {
        super.handlePacketReached(packet, packetService);
        Transferor system = (Transferor) packet.getWire().getEndPort().getSystem();
        Port availablePort = portFinder.findAvailablePort(system, packet);
        if (availablePort != null) {
            assignPacketToPort(packet, availablePort);
        } else {
            system.addPendingPacket(packet);
            packet.setOnWire(false);
        }
    }

}
