package game.service.system;

import game.model.GameState;
import game.model.packets.Packet;
import game.model.packets.SecretPacket;
import game.model.ports.Port;
import game.model.systems.Spy;
import game.service.PacketService;

import java.util.List;
import java.util.Random;

public class SpyService extends GeneralSystemService {
    private final Random random;
    private final PortFinder portFinder;

    public SpyService(GameState gameState) {
        super(gameState);
        random = new Random();
        portFinder = new PortFinder();
    }

    public void handlePacketReached(Packet packet, PacketService packetService) {
        super.handlePacketReached(packet, packetService);
        if (packet instanceof SecretPacket) {
            packetService.handlePacketLost(packet);
        } else {
            Spy system = findRandomSpySystem();
            handlePacketDelivered(system, packet, packetService);
        }
    }

    private Spy findRandomSpySystem() {
        List<Spy> outputSpySystems = gameState.getOutputSpySystems();
        int randomIndex = random.nextInt(outputSpySystems.size());
        return outputSpySystems.get(randomIndex);
    }

    private void handlePacketDelivered(Spy system, Packet packet, PacketService packetService) {
        Port availablePort = portFinder.findAvailablePort(system, packet);
        if (availablePort != null) {
            assignPacketToPort(packet, availablePort);
        } else {
            system.addPendingPacket(packet);
            packet.setOnWire(false);
        }
    }

}
