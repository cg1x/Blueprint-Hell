package game.service.system;

import game.model.GameState;
import game.model.packets.Packet;
import game.model.ports.Port;
import game.model.systems.Ddos;
import game.model.systems.Transferor;
import game.service.PacketService;

import java.util.Random;

public class DdosService extends GeneralSystemService {
    private Random random;
    private PortFinder portFinder;

    public DdosService(GameState gameState) {
        super(gameState);
        random = new Random();
        portFinder = new PortFinder();
    }

    @Override
    public void handlePacketReached(Packet packet, PacketService packetService) {
        super.handlePacketReached(packet, packetService);
        sabotagePacket(packet, packetService);
        Ddos system = (Ddos) packet.getWire().getEndPort().getSystem();
        Port availablePort = portFinder.findUnsuitablePort(system, packet);
        if (availablePort != null) {
            assignPacketToPort(packet, availablePort);
        } else {
            system.addPendingPacket(packet);
            packet.setOnWire(false);
        }
    }

    private void sabotagePacket(Packet packet, PacketService packetService) {
        int randomNumber = random.nextInt(2);
        if (randomNumber == 0) {
            packet.setTrojan(true);
        }
        if (packet.getHealth() == packet.getInitialHealth()) {
            packetService.reduceHealth(packet);
        }
    }
}
