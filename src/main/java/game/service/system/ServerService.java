package game.service.system;

import game.model.GameState;
import game.model.GameStats;
import game.model.packets.Packet;
import game.model.ports.Port;
import game.model.systems.Server;
import game.service.PacketService;

public class ServerService extends GeneralSystemService {
    public ServerService(GameState gameState) {
        super(gameState);
    }

    public void startSendingPackets(Server system) {
        for (Port port : system.getOutputPorts()) {
            sendNewPacketTo(port);
        }
    }

    public void handlePacketReached(Packet packet, PacketService packetService) {
        super.handlePacketReached(packet, packetService);
        GameStats gameStats = gameState.getGameStats();
        gameStats.incrementSuccessfulPackets();
        gameStats.decrementInNetworkPackets();
        packetService.removePacket(packet);
    }
}
