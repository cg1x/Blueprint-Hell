package game.model.systems;

import game.model.packets.Packet;
import game.model.ports.Port;

import java.util.ArrayList;

public class Transferor extends GeneralSystem {
    public Transferor(double x, double y) {
        super(x, y);
    }

    public boolean canSendPacket() {
        return !pendingPackets.isEmpty();
    }

    public boolean canAcceptPacket() {
        return pendingPackets.size() < 5;
    }
}
