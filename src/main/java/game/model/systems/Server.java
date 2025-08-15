package game.model.systems;

import game.model.packets.Packet;
import game.model.ports.Port;
import game.model.ports.PortType;

import java.util.ArrayList;

public final class Server extends GeneralSystem {
    public Server(double x, double y) {
        super(x, y);
    }

    public boolean canSendPacket() {
        return !pendingPackets.isEmpty();
    }

    public boolean canAcceptPacket() {
        return true;
    }
}
